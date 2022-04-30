package onlinecabsystem.dbQueries;

import java.sql.*;
import java.util.Vector;
import javax.swing.*;


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
            String queryString = "INSERT INTO customer(Username,Email,Phone,Pin) VALUES(?,?, ?, ?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, currUserName);
            ptmt.setString(2, currEmail);
            ptmt.setString(3, currPhone);
            ptmt.setString(4, currPin);
            ptmt.executeUpdate();
             JOptionPane.showMessageDialog(null,"User Added Successfully.");
           
	} catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Oops! Database issue.");
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
    public void loginUser(String email, String pin){
        try {
            String queryString = "select * from customer where Email=? and Pin=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, email);
            ptmt.setString(2, pin);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "Login success!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Credentials!");
            }
            
	} catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Oops! Database issue.");
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
    
    public void UpdatePassword(String Email,String newpin,String cpin)
    {
        
        try{
             String sql="select * from customer where Email=?";
             connection = getConnection();
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,Email);
            resultSet=ptmt.executeQuery();
            
            if (resultSet.next()){
                
                String v1=Email;
                String v2=newpin;
                String v3=cpin;
                
                if (v3.equals(v2)){
                    sql="update customer set Pin='"+v3+"' where Email='"+v1+"'";
                    ptmt=connection.prepareStatement(sql);
                    ptmt.execute();
                    
                    JOptionPane.showMessageDialog(null, "Pin changed successfully!");
                    
                    
                   
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Both Pins don't match!");
                   
                }
             
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Oops Database issue!");
            }
                
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                resultSet.close();
                ptmt.close();
            }catch(Exception e){
              
                JOptionPane.showMessageDialog(null, "Oops Database issue!");
            }
        }
    }
    
    public Vector<String> ViewUserProfile(String Email)
    {
        Vector<String> vec = new Vector<String>();
        try{
             
             String sql="select * from customer where Email=?";
             connection = getConnection();
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,Email);
            resultSet=ptmt.executeQuery();
            
            if (resultSet.next()){
                
                String v1=Email;
                String v2=resultSet.getString("Username");
                String v3=resultSet.getString("Phone");
                vec.add(v2);
                vec.add(v1);
                vec.add(v3);
                return vec;
                
                
             
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Oops Database issue!");
            }
                
            
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Oops! Database issue.");
        }finally{
            try{
                resultSet.close();
                ptmt.close();
            }catch(Exception e){
              
                JOptionPane.showMessageDialog(null, "Oops Database issue!");
            }
        }
        return vec;
    }
}
