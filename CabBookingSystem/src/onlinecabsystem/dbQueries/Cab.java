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
import java.util.*;
import javax.swing.*;



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
	
	public void addCab(String currCabid, String currDrivername, String currDriverphone, String currCity, int currAvail) {
		
		try {
			String queryString = "INSERT INTO cablist(Cabid, Drivername, Driverphone, City, Avail) VALUES(?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
                        ptmt.setString(1, currCabid);
			ptmt.setString(2, currDrivername);
			ptmt.setString(3, currDriverphone);
			ptmt.setString(4, currCity);
                        ptmt.setInt(5, currAvail);
			ptmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Cab Added Successfully");
		} catch (SQLException e) {
                         JOptionPane.showMessageDialog(null,"Oops! Database issue.");
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
	
	
		
		
		public void changeCabStatus(String cabId,int toChange) {
			
		
		try {
			String queryString = "update cablist set Avail = ? where cabId=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);

			ptmt.setInt(1, toChange);
			ptmt.setString(2, cabId);
			
			int rowsUpdated = ptmt.executeUpdate();
			if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null,"Cab status has been updated successfully!");
			}
                        else{
                            JOptionPane.showMessageDialog(null,"Wrong Cab Id !");
                        }
			
	
			
		} catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Oops! Database issue.");
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

                
            public Vector<String> viewCab(String cabid){

            Vector<String> vec = new Vector<String>();
            String sql = "select * from cablist where Cabid=?";
            try{
                
                connection = getConnection();
		ptmt = connection.prepareStatement(sql);
                ptmt.setString(1, cabid);
                resultSet = ptmt.executeQuery();
                if (resultSet.next()){
                    
                    vec.add(resultSet.getString("Drivername"));
                    vec.add(resultSet.getString("Driverphone"));
                    vec.add(resultSet.getString("City"));
                    vec.add(resultSet.getString("Avail"));
                    return vec;
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Oops! Database issue.");
                JOptionPane.showMessageDialog(null, "Incorrect ID");
            }finally{
                try{
                    resultSet.close();
                    ptmt.close();
                    connection.close();
                }catch(SQLException e){
                    
                }
            }
            return vec;
        }


	
	
	
}

