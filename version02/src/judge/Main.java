package judge;

import java.io.File;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		RealJudge.ojpathPlacePath = "/home/zxwtry/ojpath";
		Scanner scanner = new Scanner(new File(RealJudge.ojpathPlacePath + "/c.c"));
		String solutionString = "";
		while (scanner.hasNextLine()) {
			solutionString += (scanner.nextLine() + "\n");
		}
		scanner.close();
		String judgeResultC = RealJudge.solveCPOJ(solutionString, 1000, "default", 20.0, 30000);
		System.out.println(judgeResultC);
	}
}
