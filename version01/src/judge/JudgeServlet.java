package judge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Judge
 */
public class JudgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JudgeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get not support here");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getSession().getServletContext().getRealPath("");
	    if (RealJudge.ojpathPlacePath.length() == 0) {
	    	RealJudge.ojpathPlacePath = path;
	    }
	    PrintWriter printWriter = response.getWriter();
		String langSelectId = request.getParameter("langSelectId").toLowerCase();
	    String source = request.getParameter("source");
	    String problemId = request.getParameter("problemId");
	    int problemIdInt = Integer.parseInt(problemId);
	    try {
	    	switch (langSelectId) {
			case "java":
				String judgeResult = RealJudge.solveJavaPOJ(source, problemIdInt, "default", 20.0, 30000);
				printWriter.append(judgeResult.substring(1));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			printWriter.append("judge Error<br/>");
			printWriter.append(e.getMessage());
			e.printStackTrace();
		}
	    printWriter.flush();
	}

}
