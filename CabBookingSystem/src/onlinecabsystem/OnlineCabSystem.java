/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinecabsystem;
import java.sql.DriverManager;
import onlinecabsystem.dbQueries.Customer;
import onlinecabsystem.dbConnection.DbUtil;
import java.sql.Connection;
import onlinecabsystem.frontend.LoginPage;
/**
 *
 * @author Devil
 */
public class OnlineCabSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("System running!");
        try{
            Connection conn;
        conn = DbUtil.getInstance().getConnection();
        if(conn!=null){
            System.out.println("connection established");
        }
        }catch(Exception e){
            System.out.println(e);
        }
        LoginPage myApp=new LoginPage();
        myApp.setVisible(true);        
    }
    
}
