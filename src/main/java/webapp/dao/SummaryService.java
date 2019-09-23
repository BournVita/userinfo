package webapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import webapp.model.UserSummary;

public class SummaryService {

	public SummaryService() {
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
				if(resultSet.getDate("UPDATE_DATE")!=null) {
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
		ps.setString(1,System.getProperty("user.name"));  
		ps.setString(2,userSummary.getUsr_comment());  
		ps.setDate(3,convertToDate(userSummary.getCreate_date()));  
		ps.setDate(4,convertToDate(userSummary.getUpdate_date()));  

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
