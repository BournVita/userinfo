package webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.model.UserData;

@WebServlet(urlPatterns = "/helloworld.do")

public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/front.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String create_usr = System.getProperty("user.name");
		LocalDateTime ldt = LocalDateTime.now();
		
		String usrmessage = request.getParameter("message");
		request.setAttribute("message", usrmessage);
		request.setAttribute("user", create_usr);
		request.setAttribute("timestamp", ldt);
		App app=new App();
		UserData data = app.getuserdata();
		request.setAttribute("userdata", data);		
		request.getRequestDispatcher("/WEB-INF/pages/front.jsp").forward(request, response);

	}
	
	
}
