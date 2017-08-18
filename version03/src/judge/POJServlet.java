package judge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class POJServlet
 */
public class POJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String pojHtml = null;
	private static HashMap<String, String> problemMap = null;
	private static String pojPIDHtml = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public POJServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkStatic(request);
		String pid = request.getParameter("pid");
		PrintWriter printWriter = response.getWriter();
		if (pid == null) {
			printWriter.append(pojHtml);
		} else {
			//System.out.println(pojPIDHtml);
			String pojPID = String.format(pojPIDHtml, problemMap.get(pid), pid);
			printWriter.append(pojPID);
			//printWriter.flush();
		}
		printWriter.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkStatic(request);
		doGet(request, response);
	}
	
	private void checkStatic(HttpServletRequest request) throws FileNotFoundException {
	    String path = request.getSession().getServletContext().getRealPath("");
	    if (RealJudge.ojpathPlacePath.length() == 0) {
	    	RealJudge.ojpathPlacePath = path;
	    }
	    if (pojHtml == null) {
	        Scanner scanner = new Scanner(new File(path +  File.separator +"html" + File.separator + "poj.html"));
	        StringBuilder stringBuilder = new StringBuilder();
	        while (scanner.hasNextLine()) {
	            stringBuilder.append(scanner.nextLine());
	            stringBuilder.append("\n");
	        }
	        pojHtml = stringBuilder.toString();
	        scanner.close();
	    }
	    if (problemMap == null) {
	    	problemMap = new HashMap<String, String>();
			problemMap.put("1000", "A+B Problem<br/>poj.org/problem?id=1000");
			problemMap.put("1001", "Exponentiation<br/>poj.org/problem?id=1001");
			problemMap.put("1002", "487-3279<br/>poj.org/problem?id=1002");
			problemMap.put("1003", "Hangover<br/>poj.org/problem?id=1003");
			problemMap.put("1004", "Financial Management<br/>poj.org/problem?id=1004");
	    }
	    if (pojPIDHtml == null) {
	    	Scanner scanner = new Scanner(new File(path +  File.separator +"html" + File.separator + "OJHtmlVer1.html"));
	        StringBuilder stringBuilder = new StringBuilder();
	        while (scanner.hasNextLine()) {
	            stringBuilder.append(scanner.nextLine());
	            stringBuilder.append("\n");
	        }
	        pojPIDHtml = stringBuilder.toString();
	        scanner.close();
	    }
	}

}