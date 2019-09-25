package webapp.dao;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import webapp.model.UserDetails;

public class UserServiceTest extends Mockito {

	private UserService userService;
	private DatabaseConnection databaseConnection;
	List<UserDetails> models = new ArrayList<UserDetails>();
	Connection connection;
	Statement statement;

	@Before
	public void setUp() {
		userService = new UserService();
		databaseConnection = mock(DatabaseConnection.class);
		connection = mock(Connection.class);
		statement = mock(Statement.class);

	}

	@Test
	public void testObjectNotNull() throws Exception {
		Assert.assertNotNull(userService);
		Assert.assertNotNull(databaseConnection);
	}

	
}
