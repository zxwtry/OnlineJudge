package judge;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorMemoryThread extends Thread {
	
	private static final Logger logger = Logger.getLogger("MonitorMemoryThread");
	
	String tag;
	String oper;
	int memLimit;
	CountDownLatch latch;
	
	public MonitorMemoryThread(String oper, String tag, int memLimitKB, CountDownLatch latch) {
		this.oper = oper;
		this.tag = tag;
		///proc/[pid]/statm文件保存的是page个数
		//一个page 是 4k
		this.memLimit = memLimitKB / 4;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		//保证在judge脚本执行前，内存监控线程已经启动
		latch.countDown();
		try {
			//查找pid  ps -x | grep oper  然后再每行找tag
			String pid = getPID();
			if (pid == null) {
				logger.info(tag + oper + "pid == null");
				return;
			}
			//定时查看/proc/[pid]/statm文件，将文件读入字节数组
			//该文件只有一行，其中需要的数据（线程占用内存）在第二列
			logger.info(tag + oper + "pid: " + pid);
			monitorPID(pid);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	void monitorPID(String pid) throws Exception {
		byte[] bs = new byte[1024];
		while (true) {
			File file = new File("/proc/" + pid + "/statm");
			if (! file.exists()) {
				System.out.println("file not exist");
				logger.info(tag + oper + "file not exist");
				break;
			}
			FileInputStream fis = new FileInputStream(file);
			int bi = 0;
			//读入文件
			while (bi < bs.length) {
				int size = fis.read(bs, bi, bs.length - bi);
				if (size < 1) {
					break;
				}
				bi += size;
			}
			fis.close();
			byte blank = ' ', byte0 = '0';
			int start = 0;
			int mem = 0;
			//跳过第一列数据
			for (; start < bi; start ++) {
				if (bs[start] == blank) {
					start ++;
					break;
				}
			}
			//读取第二列数据
			for (; start < bi; start ++) {
				byte b = bs[start];
				if (b == blank) {
					break;
				}
				mem = mem * 10 + b - byte0;
			}
			if (mem >= memLimit) {
				Runtime.getRuntime().exec("kill -9 " + pid);
				logger.info(tag + oper + "kill " + pid);
				break;
			}
			Thread.sleep(50);
		}
	}
	
	String getPID() throws Exception {
		int cnt = 0;
		String pid = null;
		while (pid == null) {
			String psOper = "bash /home/zxwtry/ps.sh " + oper;
			Process p = Runtime.getRuntime().exec(psOper);
			p.waitFor();
			Scanner scer = new Scanner(p.getErrorStream());
			while (scer.hasNextLine()) {
				String line = scer.nextLine();
				if (line.indexOf(tag) != -1) {
					int start = 0;
					while (start < line.length() && line.charAt(start) == ' ') {
						start ++;
					}
					int end = start;
					while (end < line.length() && line.charAt(end) != ' ') {
						end ++;
					}
					pid = line.substring(start, end);
				}
			}
			scer.close();
			Scanner scin = new Scanner(p.getInputStream());
			while (scin.hasNextLine()) {
				String line = scin.nextLine();
				if (line.indexOf(tag) != -1) {
					int start = 0;
					while (start < line.length() && line.charAt(start) == ' ') {
						start ++;
					}
					int end = start;
					while (end < line.length() && line.charAt(end) != ' ') {
						end ++;
					}
					pid = line.substring(start, end);
				}
			}
			scin.close();
			Thread.sleep(10);
			cnt ++;
			if (cnt > 10) {
				logger.info(tag + oper + "程序运行时间短，已经结束了");
				break;
			}
		}
		return pid;
	}
	
}
