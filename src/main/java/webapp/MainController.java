package webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webapp.Models.Model;
import webapp.dao.SummaryService;
import webapp.model.UserSummary;

@WebServlet("/summary/*")
public class MainController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson _gson = null;

	private HashMap<String, Model> _modelsDb = new HashMap<>();
	SummaryService service = new SummaryService();

	// a utility method to send object
	// as JSON response
	private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {

		System.out.println("Object to json" + obj);
		response.setContentType("application/json");
		_gson = new Gson();


		String res = _gson.toJson(obj);

		PrintWriter out = response.getWriter();

		out.print(res);
		out.flush();
	}

	// Get models
	// GET/summary/
	// GET/summary/id
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, UserSummary>  models;
		String pathInfo = request.getPathInfo();

		if (pathInfo == null || pathInfo.equals("/")) {

			try {
				models = service.getAllSummary();
				sendAsJson(response, models);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

		}

		String[] splits = pathInfo.split("/");

		if (splits.length != 2) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String modelId = splits[1];

		/*
		 * if (!models.containsKey(modelId)) {
		 * 
		 * response.sendError(HttpServletResponse.SC_NOT_FOUND); return; }
		 * 
		 * sendAsJson(response, _modelsDb.get(modelId));
		 */
		return;
	}

	// Adds new model in DB
	// POST/summary/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		_gson = new Gson();

		if (pathInfo == null || pathInfo.equals("/")) {

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			String payload = buffer.toString();
			System.out.println("payload" + payload);

			UserSummary model=new UserSummary();
			try {
				 model = _gson.fromJson(payload, UserSummary.class);

				service.addSummary(model);
				sendAsJson(response, model);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
}
