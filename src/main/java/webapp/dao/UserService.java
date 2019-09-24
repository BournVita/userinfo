package webapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import webapp.model.UserDetails;
import webapp.model.UserSummary;

public class UserService {

	public UserService() {
	}

	public Map<String, UserDetails>  getAll() throws Exception {
		DatabaseConnection db;
		db = DatabaseConnection.getInstance();
		List<UserSummary> users = new ArrayList<UserSummary>();
		Connection connection = db.getConnection();
		Map<String, UserDetails> details = new LinkedHashMap<String, UserDetails>();

		String selectSql = "select us.usr_id,us.usr_comment,us.create_date,us.update_date,ud.city_alti,ud.city_name,ud.city_temp,ud.city_longi from dbo.usrsummary us, dbo.usrdetails ud where us.usr_id=ud.usr_id and us.usr_comment = ud.usr_comment";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectSql)) {

			// Print results from select statement
			while (resultSet.next()) {
				UserDetails ud = new UserDetails();
				ud.setUsr_id(resultSet.getString("USR_ID"));
				ud.setCity_alti(resultSet.getString("CITY_ALTI"));
				ud.setCity_longi(resultSet.getString("CITY_LONGI"));
				ud.setCity_temp(resultSet.getString("CITY_TEMP"));
				ud.setCity_name(resultSet.getString("CITY_NAME"));
				ud.setCreate_date(convertToString(resultSet.getDate("CREATE_DATE")));
				if (resultSet.getDate("UPDATE_DATE") != null) {
					ud.setUpdate_date(convertToString(resultSet.getDate("UPDATE_DATE")));
				}
				details.put(resultSet.getString("USR_COMMENT"), ud);
			}

			connection.close();
		}
		return details;

	}

	public List<UserSummary> getAllSummary() throws Exception {
		DatabaseConnection db;
		db = DatabaseConnection.getInstance();
		List<UserSummary> users = new ArrayList<UserSummary>();
		Connection connection = db.getConnection();
		String selectSql = "select * from dbo.usrsummary";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectSql)) {

			// Print results from select statement

			while (resultSet.next()) {
				UserSummary ud = new UserSummary();
				ud.setUsr_comment(resultSet.getString("USR_COMMENT"));
				ud.setUsr_id(resultSet.getString("USR_ID"));
				ud.setCreate_date(convertToString(resultSet.getDate("CREATE_DATE")));
				if (resultSet.getDate("UPDATE_DATE") != null) {
					ud.setUpdate_date(convertToString(resultSet.getDate("UPDATE_DATE")));
				}
				users.add(ud);
			}
			connection.close();
		}
		return users;

	}

	public int addSummary(UserSummary userSummary) throws Exception {
		DatabaseConnection db;
		db = DatabaseConnection.getInstance();
		List<UserSummary> users = new ArrayList<UserSummary>();
		Connection connection = db.getConnection();
		String sql = "INSERT INTO usrsummary (usr_id,usr_comment,create_date,update_date) VALUES (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, System.getProperty("user.name"));
		ps.setString(2, userSummary.getUsr_comment());
		ps.setDate(3, convertToDate(userSummary.getCreate_date()));
		ps.setDate(4, convertToDate(userSummary.getUpdate_date()));

		int i = ps.executeUpdate();

		System.out.println(i + " records inserted");

		connection.close();

		return i;

	}

	public List<UserDetails> getAllDetails() throws Exception {
		DatabaseConnection db;
		db = DatabaseConnection.getInstance();
		List<UserDetails> users = new ArrayList<UserDetails>();
		Connection connection = db.getConnection();
		String selectSql = "select * from dbo.usrdetails";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectSql)) {

			// Print results from select statement

			while (resultSet.next()) {
				UserDetails ud = new UserDetails();
				ud.setUsr_id(resultSet.getString("USR_ID"));
				ud.setCity_alti(resultSet.getString("CITY_ALTI"));
				ud.setCity_longi(resultSet.getString("CITY_LONGI"));
				ud.setCity_temp(resultSet.getString("CITY_TEMP"));
				ud.setCity_name(resultSet.getString("CITY_NAME"));

				ud.setCreate_date(convertToString(resultSet.getDate("CREATE_DATE")));
				if (resultSet.getDate("UPDATE_DATE") != null) {
					ud.setUpdate_date(convertToString(resultSet.getDate("UPDATE_DATE")));
				}
				users.add(ud);
			}
			connection.close();
		}
		return users;

	}

	public int addDetails(UserDetails userDetails) throws Exception {
		DatabaseConnection db;
		db = DatabaseConnection.getInstance();
		Connection connection = db.getConnection();
		String sql = "INSERT INTO usrdetails (usr_id,usr_comment,city_name,city_alti,city_longi,city_temp,"
				+ "create_date,update_date) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, System.getProperty("user.name"));
		ps.setString(2, userDetails.getUsr_comment());
		ps.setString(3, userDetails.getCity_name());
		ps.setString(4, userDetails.getCity_alti());
		ps.setString(5, userDetails.getCity_longi());
		ps.setString(6, userDetails.getCity_temp());
		ps.setDate(7, convertToDate(userDetails.getCreate_date()));
		ps.setDate(8, convertToDate(userDetails.getUpdate_date()));

		int i = ps.executeUpdate();

		System.out.println(i + " records inserted");

		connection.close();

		return i;

	}

	private Date convertToDate(String strDate) throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, yyyy");
		java.util.Date date = sdf1.parse(strDate);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		return sqlStartDate;

	}

	private String convertToString(Date date) throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, yyyy");
		String str = sdf1.format(date);
		return str;

	}
}
