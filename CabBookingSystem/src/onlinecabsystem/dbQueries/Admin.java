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
	
   
    public boolean loginAdmin(String id, String pin){
        try {
            String queryString = "select * from admins where Id=? and Pin=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, id);
            ptmt.setString(2, pin);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null, "Login success!");
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Credentials!");
                return false;
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
        
        return false;
    }	
    
    
    public void UpdatePassword(String id,String pin,String cpin)
    {
         
        
          try{
              String sql="select* from admins where Id=?";
               connection = getConnection();
//              conn=getConnection();
            ptmt=connection.prepareStatement(sql);
            
            ptmt.setString(1, id);
            resultSet=ptmt.executeQuery();
            
            if (resultSet.next()){
                
                String v1=id;
                String v2=pin;
                String v3=cpin;
                
                if (v3.equals(v2)){
                    sql="update admins set Pin='"+v3+"' where Id='"+v1+"'";
                    ptmt=connection.prepareStatement(sql);
                    ptmt.execute();
                    
                    JOptionPane.showMessageDialog(null, "Pin changed successfully!");
                    
                    
//                    NewPintxtField.setText("");
//                    ConfirmPintxtField.setText("");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Both Passwords don't match!");
//                    NewPintxtField.setText("");
//                    ConfirmPintxtField.setText("");
                }
             
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Credential!");
            }
                
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Oops! Database issue.");
        }finally{
            try{
                resultSet.close();
                ptmt.close();
            }catch(Exception e){
            
            }
        }
        
    }
}
