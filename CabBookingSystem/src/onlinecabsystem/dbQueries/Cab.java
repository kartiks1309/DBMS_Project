/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinecabsystem.dbQueries;

/**
 *
 * @author Devil
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import onlinecabsystem.dbConnection.DbUtil;

public class Cab{
		
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	
	public Cab() {};
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DbUtil.getInstance().getConnection();
		return conn;
	}
	
	public void addCab(String currDrivername, String currDriverphone, String currCity, boolean currAvail) {
		
		try {
			String queryString = "INSERT INTO cablist( Drivername, Driverphone, City, Avail) VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, currDrivername);
			ptmt.setString(2, currDriverphone);
			ptmt.setString(3, currCity);
                        ptmt.setBoolean(4, currAvail);
			ptmt.executeUpdate();
			System.out.println("Cab Added Successfully");
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
	
	
		
		
		public void changeCabStatus(int cabId,boolean toChange) {
			
		
		try {
			String queryString = "update cablist set Avail = ? where cabId=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);

			ptmt.setBoolean(1, toChange);
			ptmt.setInt(2, cabId);
			
			int rowsUpdated = ptmt.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing cab status has been updated successfully!");
			}
			
	
			
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

