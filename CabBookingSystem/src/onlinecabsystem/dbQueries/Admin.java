package onlinecabsystem.dbQueries;

import java.sql.*;
import javax.swing.*;


import onlinecabsystem.dbConnection.DbUtil;

public class Admin {
	
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
	
    public Admin() {};
	
    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DbUtil.getInstance().getConnection();
        return conn;
    }
	
   
    public void loginAdmin(String id, String pin){
        try {
            String queryString = "select * from admins where Id=? and Pin=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, id);
            ptmt.setString(2, pin);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "Login success!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Credentials!");
            }
            
	} catch (SQLException e) {
            e.printStackTrace();
	} finally {
            try {
                if (ptmt != null) ptmt.close();
		if (connection != null) connection.close();
            } catch (SQLException e) {
		e.printStackTrace();
            } catch (Exception e) {
		e.printStackTrace();
            }
	}
    }	
}
