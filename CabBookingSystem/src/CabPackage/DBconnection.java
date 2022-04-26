/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CabPackage;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Kartik
 */
public class DBconnection {
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cabs_db";
            Connection conn = DriverManager.getConnection(url,"root","_ksmysql_");
            System.out.println("Connection success!");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}

