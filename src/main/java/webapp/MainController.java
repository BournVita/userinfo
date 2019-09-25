package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import webapp.dao.UserService;
import webapp.model.UserDetails;

@WebServlet("/users/*")
public class MainController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gson _gson = null;
	private UserService service = null;

	public Gson get_gson() {
		return _gson;
	}

	public void set_gson(Gson _gson) {
		this._gson = _gson;
	}

	public UserService getService() {
		return service;
	}

	public MainController() {
		service = new UserService();
		setService(service);
	}

	public void setService(UserService service) {
		this.service = service;
		this._gson = new Gson();
	}


	// a utility method to send object
	// as JSON response
	private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {

		System.out.println("Object to json" + obj);
		response.setContentType("application/json");

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

		Map<String, UserDetails> models;
		String pathInfo = request.getPathInfo();
		System.out.println("Object to pathInfo" + pathInfo);

		if (pathInfo == null || pathInfo.equals("/")) {

			try {
				models = service.getAll();
				sendAsJson(response, models);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}

		}
		return;
	}

}
