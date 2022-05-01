/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinecabsystem.dbQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import onlinecabsystem.dbConnection.DbUtil;

/**
 *
 * @author Devil
 */
public class BookCab {
    Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	
	public BookCab() {};
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DbUtil.getInstance().getConnection();
		return conn;
	}
	
	public String checkCabAvailability(String city){
            String temp="";
            String sql = "select Cabid from cablist where City=? and Avail=?";
            try{
                
                connection = getConnection();
		ptmt = connection.prepareStatement(sql);
                ptmt.setString(1, city);
                ptmt.setInt(2, 1);
                resultSet = ptmt.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("Cabid");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect ID");
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Oops! Database issue.");
                
            }finally{
                try{
                    resultSet.close();
                    ptmt.close();
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
           return temp; 
        }
        
	public void addBooking(String city, String startLoc, String endLoc, int dist, String emailId) {
		//check availability
                String AvailCabid=checkCabAvailability(city);
                
		if(AvailCabid.equals("")){
                     JOptionPane.showMessageDialog(null,"No cabs available!");
                }
                else{
                    //finding booking id 
                     int count=0;
                    String sql = "SELECT COUNT(*) FROM bookings";
                    try{
                
                            connection = getConnection();
                            ptmt = connection.prepareStatement(sql);
                
                            resultSet = ptmt.executeQuery();
                            if (resultSet.next()){
                                System.out.println(resultSet.getInt(1)+1);
                                count=(resultSet.getInt(1)+1);
                                }
                        //Inserting in bookings table    
                        String queryString = "INSERT INTO bookings(BookingId, CabId, Email,StartLoc , EndLoc, Distance, BillAmount) VALUES(?,?,?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
                        
                        ptmt.setInt(1, count);
			ptmt.setString(2, AvailCabid);
                        ptmt.setString(3, emailId);
			ptmt.setString(4, startLoc);
			ptmt.setString(5, endLoc);
			ptmt.setInt(6, dist);
			ptmt.setInt(7, (15*dist));
			ptmt.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Cab Booked successfully!\n Total charges: "+(15*dist)+"\n Cab Number: "+AvailCabid);
                        
                        //Making availability of Cabid used as 0 as cab booked successfully
                        Cab cabObj=new Cab();
                        cabObj.changeCabStatus(AvailCabid, 0);
                        
                        }catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"Oops! Database issue.");
                         e.printStackTrace();
                        }
                    finally {
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
	public Vector<String> findWithBookingId(int bookingId){
            String sql="select * from bookings where BookingId=?";
            Vector<String> bookingTransaction= new Vector<String>();
            try{
            
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
                ptmt.setInt(1, bookingId);
                resultSet = ptmt.executeQuery();
                if (resultSet.next()){
                    
                      bookingTransaction.add (resultSet.getString("CabId"));
                      bookingTransaction.add(resultSet.getString("StartLoc"));
                      bookingTransaction.add(resultSet.getString("EndLoc"));
                      bookingTransaction.add(Integer.toString(resultSet.getInt("Distance")));
                      bookingTransaction.add(Integer.toString(resultSet.getInt("BillAmount")));
                      bookingTransaction.add (resultSet.getString("Email"));
                      
                      return bookingTransaction;
                     
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong Booking Id");
                }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } finally{
                try{
                    resultSet.close();
                    ptmt.close();
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            return bookingTransaction;
        } 
	
}
