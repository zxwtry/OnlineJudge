import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

public class A {
	public static void main(String[] args) {
		//count();
        //System.out.println("完全词频统计完成！");
        //String str="科教处女干事情愿去支教";
        //for (String string : parseAnLine(str))
        //    System.out.println(string);
//        String var1 = "下雨天留客天天留我不留";
        String var1 = "停车坐爱枫林晚";
        for (String string : parseAnLine(var1))
            System.out.println(string);
	}
	private static final String SOURCE_PATH = "/home/run/wcdata/source/";
	private static final String RESULT = "result";
	static HashMap<String, CiCou> map = new HashMap<String, CiCou>();
	static List<CiCou> lists = new LinkedList<CiCou>();
	static void count() {
		File baseDir = new File(SOURCE_PATH);
        String[] strings = baseDir.list();
        for (String string : strings) {
        	readAnTie(string);
        	solveName(string);
        }
        saveResultToDisk();
	}
	private static void readAnTie(String tie_Name) {
    	File file = new File(SOURCE_PATH + tie_Name);
    	if (!file.exists()) {
    		System.out.println("wo cao 文件不存在");
    		return;
    	}
    	BufferedReader bufferedReader = null;
    	try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = bufferedReader.readLine();
			while(line != null) {
				solveOneLine(line);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    }
	private static void solveOneLine(String line) {
		line = line.toLowerCase();
		int index = line.indexOf("楼");
		if (index != -1)
			line = line.substring(index);
		List<String> strings = parseAnLine(line);
		for (String string : strings) {
			if (map.containsKey(string)) {
				map.get(string).add();
			} else {
				CiCou ciCou = new CiCou(string);
				lists.add(ciCou);
				map.put(string, ciCou);
			}
		}
	}
    private static void solveName(String string) {
    	String[] parts = string.split("_");
    	if (parts.length == 2) {
    		solveOneLine(parts[1]);
    	}
	}
    private static void saveResultToDisk() {
    	File file = new File(RESULT);
    	if (file.exists()) {
    		file.delete();
    	}
    	PrintWriter printWriter = null;
    	try {
			file.createNewFile();
			printWriter = new PrintWriter(file);
	        Collections.sort(lists);
            int cou = 0;
	        for (CiCou ciCou : lists) {
	        	String string = String.format("第%4d条词语: %s\t 次数: %d\r\n", ++cou, ciCou.ci, ciCou.cou);
	        	printWriter.write(string);
	        }
	        printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				try {
					printWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    }
	static class CiCou implements Comparable<CiCou>{
		String ci;
		int cou;
		public CiCou(String ci) {
			this.ci = ci;
			cou = 1;
		}
		public void add() {
			cou ++;
		}
		@Override
		public int compareTo(CiCou o) {
			return o.cou - this.cou;
		}
	}
	static List<String> parseAnLine(String string) {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		List<SegToken> list = segmenter.process(string, SegMode.INDEX);
		List<String> strings = new LinkedList<String>();
		for (SegToken segToken : list) {
			strings.add(segToken.word);
		}
		return strings;
	}
}
