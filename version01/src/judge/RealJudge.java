package judge;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class RealJudge {
	static String ojpathPlacePath = "";
    //处理输入的Java程序
    //solutionString	是输入的Java程序的String
    //problemId			是问题ID，如1000,3266这样
    //设置返回字符串的第一个字符，作为标记：'0':AC '1':WA '2':CE '3':RE '4':TLE '5':MLE  '6':未知
    static String solveJavaPOJ(String solutionString, int problemId, String userName, double timeLimitS, int memoryLimitKB) throws Exception {
    	String finalBaseDir = ojpathPlacePath + "/shell/";
    	String finalBaseDataDir = ojpathPlacePath + "/data/poj/";
    	//第一部分：创建一个临时目录
    	String tempPathString = "";
    	File tempPathFile = null;
    	while (true) {
	    	tempPathString = "P" + problemId + "" + (int)(Math.random() * 100000);
	    	tempPathFile = new File(finalBaseDir + tempPathString);
	    	if (! tempPathFile.exists()) break;
    	}
    	tempPathFile.mkdirs();
    	
    	//第二部分：复制一份judgeJava.sh到tempPathString
    	Scanner scSh = new Scanner(new File(finalBaseDir + "judgeJava.sh"));
    	PrintWriter pwSh = new PrintWriter(new File(finalBaseDir + tempPathString + "/judgeJava.sh"));
    	while(scSh.hasNextLine())
    		pwSh.write(scSh.nextLine() + "\n");
    	pwSh.flush();
    	scSh.close();
    	pwSh.close();
    	
    	
    	//第三部分：将solutionString写入文件Main.java
    	PrintWriter pwMain = new PrintWriter(new File(finalBaseDir + tempPathString +  "/Main.java"));
    	pwMain.write(solutionString);
    	pwMain.flush();
    	pwMain.close();
    	
    	
    	//第四部分：执行并获取执行结果
    	String forReturn = "";
    	Process pro = Runtime.getRuntime().exec("timeout " + String.format("%f", timeLimitS) +" /bin/sh " + finalBaseDir 
    			+ tempPathString + "/" + "judgeJava.sh " + finalBaseDataDir +problemId + " " +finalBaseDir+tempPathString + " -Xmx" + memoryLimitKB + "k");
    	//pro.waitFor();
    	int sign = pro.waitFor();
    	if (sign == 124) {
    		forReturn = "4Time Limit Exceed";	//TLE
    	} else {
	    	//得到执行结果，可能是 AC或WA或CE或RE
	    	boolean isAC = true;
	    	String output = getMessageFromString(pro.getInputStream());
	    	String[] outputStrings = output.split("\n");
	    	int outputStringsIndex = 0;
	    	Scanner scOutput = new Scanner(new File(finalBaseDataDir + problemId + ".output"));
	    	while (scOutput.hasNextLine()) {
	    		String scLine = scOutput.nextLine().trim();
	    		if (outputStringsIndex >= outputStrings.length) {
	    			isAC = false;
	    			break;
	    		}
	    		if (! scLine.equals(outputStrings[outputStringsIndex ++].trim())) {
	    			isAC = false;
	    		}
	    	}
	    	scOutput.close();
	    	isAC &= outputStrings.length == outputStringsIndex;
	    	if (isAC) {
	    		forReturn = "0Accept";	//AC
	    	} else {
	    		String error = getMessageFromString(pro.getErrorStream());
	    		if (error.length() != 0) {
	    			if (error.startsWith("Exception in thread \"main\" java.lang.OutOfMemoryError: Java heap space")) {	//MLE
	    				forReturn = "5Memory Limit Exceed";
	    			} else if (error.startsWith("Main.java")) {		//CE
	    				forReturn = "2Compile Error " + error;
	    			} else if (error.startsWith("Exception")) {		//RE
	    				forReturn = "3Runtime Error" + error;
	    			} else {
	    				forReturn = "6Unknow Error" + error + "\n" + output;	//未知
	    			}
		    	} else {
		    		forReturn = "1Wrong Answer";			//WA
		    	}
	    	}
    	}
    	//最后部分：删除tempPathString
    	Runtime.getRuntime().exec("rm -rf " + finalBaseDir + tempPathString);
    	return forReturn;
    }
    private static String getMessageFromString(InputStream is) {
    	StringBuilder st = new StringBuilder();
    	BufferedReader br = null;
    	try {
    		br = new BufferedReader(new InputStreamReader(is));
            while (true) {
            	String line = br.readLine();
            	if (line == null) break;
            	st.append(line + "\n");
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return st.toString();
	}
}