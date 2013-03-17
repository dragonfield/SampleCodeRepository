package jp.dragon.field;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SampleServlet extends HttpServlet {
	private static Log log_ = LogFactory.getLog(SampleServlet.class);
	
	public void init() throws ServletException {
	}
	
	public void destroy() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = res.getWriter();
		writer.println("<html>");
		writer.println("  <head>");
		writer.println("    <title>XML Test Servlet</title>");
		writer.println("  </head>");
		writer.println("  <body>");
		writer.println("  </body>");
		writer.println("</html>");
	}
}
