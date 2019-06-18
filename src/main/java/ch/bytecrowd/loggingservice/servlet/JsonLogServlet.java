package ch.bytecrowd.loggingservice.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.bytecrowd.loggingservice.util.SQLTransformer;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

@WebServlet("/JsonLogServlet")
public class JsonLogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final GsonBuilder GSON_Builder = new GsonBuilder();
	private static final Logger SQL_LOG = Logger.getLogger("SQL_LOGGER");
	private static final Logger LOG = Logger.getLogger(JsonLogServlet.class);
	
	private Gson gson = GSON_Builder.create();
	
	public JsonLogServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try (ServletInputStream jsonStream = request.getInputStream();
			InputStreamReader jsonReader = new InputStreamReader(jsonStream);) {
			LinkedTreeMap<String, Map<String, Object>> fromJson = gson.fromJson(jsonReader, LinkedTreeMap.class);
			String jsonToMySqlInsert = SQLTransformer.jsonToMySqlInsert(fromJson);
			SQL_LOG.info(jsonToMySqlInsert);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}