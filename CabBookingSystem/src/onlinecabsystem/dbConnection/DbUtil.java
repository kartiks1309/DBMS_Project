/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinecabsystem.dbConnection;

/**
 *
 * @author Devil
 */
import java.sql.*;




public class DbUtil {
	
	String driverClassName = "com.mysql.cj.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/cabs_db";
	String dbUser = "root";

	String dbPwd = "srishtid";

	


	private static DbUtil dbUtil = null;

	private DbUtil() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
                System.out.println("connection established");
		return conn;
	}

	public static DbUtil getInstance() {
		if (dbUtil == null) {
			dbUtil = new DbUtil();
		}
		return dbUtil;
	}
}


