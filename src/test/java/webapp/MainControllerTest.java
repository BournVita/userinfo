package webapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import webapp.dao.UserService;
import webapp.model.UserDetails;

public class MainControllerTest extends Mockito {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserService userService;
	private MainController controller;
	Map<String, UserDetails> models = new HashMap<String, UserDetails>();

	@Before
	public void setUp() {
		controller = new MainController();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		userService = mock(UserService.class);
		controller.setService(userService);

	}

	 @Test
	public void testServletValidValues() throws Exception {
		UserDetails ud = new UserDetails();
		ud.setCity_alti("12");
		ud.setCity_longi("12");
		ud.setCity_name("12");
		ud.setCity_temp("12");
		ud.setUsr_id("smith");
		models.put("comment", ud);
		StringWriter writer = new StringWriter();
		BDDMockito.given(response.getWriter()).willReturn(new PrintWriter(writer));

		when(userService.getAll()).thenReturn(models);
		controller.doGet(request, response);
		verify(response).setContentType("application/json");
		System.out.println("STRING " + writer.toString());
		System.out.println("STRING " + response.getContentType());

		Assert.assertNotNull(writer.toString());
		Assert.assertTrue(writer.toString().contains("{"));
	}

	 @Test
	public void testServletValidValuesException() throws Exception {
		UserDetails ud = new UserDetails();
		ud.setCity_alti("12");
		ud.setCity_longi("12");
		ud.setCity_name("12");
		ud.setCity_temp("12");
		ud.setUsr_id("smith");
		models.put("comment", ud);
		StringWriter writer = new StringWriter();
		BDDMockito.given(response.getWriter()).willReturn(new PrintWriter(writer));

		when(userService.getAll()).thenThrow(new Exception());
		controller.doGet(request, response);
		System.out.println("STRING " + response);

		verify(response).sendError(500);

	}

	@Test
	public void testServletInvalidValues() throws Exception {
		UserDetails ud = new UserDetails();
		ud.setCity_alti("12");
		ud.setCity_longi("12");
		ud.setCity_name("12");
		ud.setCity_temp("12");
		ud.setUsr_id("smith");
		models.put("comment", ud);
		Mockito.when(request.getMethod()).thenReturn("HEAD");
		when(request.getPathInfo()).thenReturn("abc");

		controller.doGet(request, response);
		System.out.println("STRING " + response.getStatus());

		Assert.assertNotNull(response);
	}
}
