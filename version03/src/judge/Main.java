package judge;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
//		testC();
//		testJava();
//		testCPP();
//		testPy2();
		testPy3();
	}
	
	static void testM() throws Exception {
		String oper = "Java";
//		String tag = "778899";
		String psOper = "bash /home/zxwtry/ps.sh " + oper;
		Process p = Runtime.getRuntime().exec(psOper);
		p.waitFor();
		Scanner scanner = new Scanner(p.getErrorStream());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
		System.out.println("================");
		scanner = new Scanner(p.getInputStream());
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		scanner.close();
	}
	
	static void testPy3() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/oj/";
		
		Scanner scanner = new Scanner(new File("/home/zxwtry/tmp/py3.py"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String res = RealJudge.solvePython3POJ(solutionString, 1000, "default", 2000.0, 30000, "778899");
		System.out.println(res);
	
	}
	
	static void testPy2() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/oj/";
		
		Scanner scanner = new Scanner(new File("/home/zxwtry/tmp/py2.py"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String res = RealJudge.solvePython2POJ(solutionString, 1000, "default", 2000.0, 30000, "778899");
		System.out.println(res);
	
	}
	
	
	static void testCPP() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/oj/";
		
		Scanner scanner = new Scanner(new File("/home/zxwtry/tmp/cpp.cpp"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String res = RealJudge.solveCppPOJ(solutionString, 1000, "default", 2000.0, 30000, "778899");
		System.out.println(res);
	
	}
	
	static void testC() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/oj/";
		
		Scanner scanner = new Scanner(new File("/home/zxwtry/tmp/c.c"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String res = RealJudge.solveCPOJ(solutionString, 1000, "default", 2000.0, 30000, "778899");
		System.out.println(res);
	
	}
	
	static void testJava() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/oj/";
		
		Scanner scanner = new Scanner(new File("/home/zxwtry/tmp/Main.java"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String judgeResultC = RealJudge.solveJavaPOJ(solutionString, 1000, "default", 2000.0, 30000, "778899");
		System.out.println(judgeResultC);
	
	}
	
	static void test() throws Exception {

		RealJudge.ojpathPlacePath = "/home/zxwtry/ojpath";
		Scanner scanner = new Scanner(new File(RealJudge.ojpathPlacePath + "/c.c"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String judgeResultC = RealJudge.solveCPOJ(solutionString, 1000, "default", 20.0, 30000, "");
		System.out.println(judgeResultC);
	
	}
	
}
