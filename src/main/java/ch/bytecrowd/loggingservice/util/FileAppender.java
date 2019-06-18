package ch.bytecrowd.loggingservice.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.log4j.Logger;

public final class FileAppender {
	
	private static final Logger LOG = Logger.getLogger(FileAppender.class);

	public static final void appendLineToFile(String absolutePath, String line) throws IOException {
		line += "\r\n";
		try {
			Files.write(Paths.get(absolutePath), line.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void appendFileContent(StringBuilder html, String absolutePath) {
		try {
			final byte[] bytes = Files.readAllBytes(Paths.get(absolutePath));
			html.append(new String(bytes));
		} catch (Exception e) {
			html.append("ein Fehler ist aufgetreten: ")
			.append(e.getMessage());
			LOG.error(e.getMessage(), e);
		}
	}
	
	public static void apendDirectoryListeningAsHtmlLink(StringBuilder html, File logDir) {
		for (File f : logDir.listFiles()) {
			html.append("<a href=\"LogReaderServlet?logFile=");
			html.append(f.getName());
			html.append("\">");
			html.append(f.getName());
			html.append("</a><br>");
		}
	}
}
