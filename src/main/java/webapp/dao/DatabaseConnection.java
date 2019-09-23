package webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static DatabaseConnection instance;
	private Connection connection;

	private String hostName = "loyaltyone.database.windows.net"; // update me
	private String dbName = "userdata "; // update me
	private String user = "jackson"; // update me
	private String password = "pink09@@"; // update me
	private String url = String.format(
			"jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
					+ "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
			hostName, dbName, user, password);

	private DatabaseConnection() throws SQLException {
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			this.connection = DriverManager.getConnection(url);
			String schema = connection.getSchema();
	           System.out.println("Successful Created connection" + schema);

		} catch (Exception ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnection();
		} else if ((instance.getConnection()==null)||(instance.getConnection().isClosed())) {
			instance = new DatabaseConnection();
		}

		return instance;
	}
}
