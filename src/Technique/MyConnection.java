/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AH Info
 */
public class MyConnection {
    private String url="jdbc:mysql://localhost:3306/covoituragej";
    private String login="root";
    private String password="";
    private Connection con;
    private static MyConnection instance;
    
    private MyConnection(){
         try {
            con = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return con;
    }
    
    public static MyConnection getInstance(){
       if (instance == null)
        {
            instance = new MyConnection();
        }
        return instance;
        
    }
    
    
    
}
