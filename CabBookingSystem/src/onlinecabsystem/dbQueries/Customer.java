package onlinecabsystem.dbQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import onlinecabsystem.dbConnection.DbUtil;

public class Customer {
	
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public Customer() {};
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DbUtil.getInstance().getConnection();
		return conn;
	}
	
public void addCustomer(String currUserName, String currEmail, String currPhone, String currPin ) {
		
		try {
			String queryString = "INSERT INTO Customer(Username,Email,Phone,Pin) VALUES(?,?, ?, ?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, currUserName);
			ptmt.setString(2, currEmail);
                        ptmt.setString(3, currPhone);
                        ptmt.setString(4, currPin);
			ptmt.executeUpdate();
			System.out.println("Data Added Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
}
