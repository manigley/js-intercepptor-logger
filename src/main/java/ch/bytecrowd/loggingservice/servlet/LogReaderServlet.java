package ch.bytecrowd.loggingservice.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.bytecrowd.loggingservice.util.FileAppender;

@WebServlet("/LogReaderServlet")
public class LogReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String tomcatLogDir = System.getProperty("catalina.base") + "/logs/";
	
	public LogReaderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder html = new StringBuilder();
		String logFile = request.getParameter("logFile");
		if (logFile != null) {
			appendLogFileContent(response, html, logFile);
		} else {
			appendLogFilesDir(html);
		}
		response.getOutputStream().write(html.toString().getBytes());
	}

	public void appendLogFileContent(HttpServletResponse response, StringBuilder html, String logFile) {
		response.setContentType("text/plain;charset=UTF-8");
		FileAppender.appendFileContent(html, tomcatLogDir + System.getProperty("file.separator") + logFile);
	}

	public void appendLogFilesDir(StringBuilder html) {
		File logDir = new File(tomcatLogDir);
		html.append("<html><head><meta charset=\"UTF-8\"></head><body>");
		FileAppender.apendDirectoryListeningAsHtmlLink(html, logDir);
		html.append("</body></html>");
	}
}
