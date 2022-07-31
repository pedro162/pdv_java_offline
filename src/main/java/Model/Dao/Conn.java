/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dao;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author pedro
 */
public final class Conn {
    protected static Connection connection;
    
    private Conn(){}
        
    public static Connection open(String name){
       try{           
            String urlConnection = "jdbc:mysql://localhost:3306/barbearia?user=root&password=";
            if(connection == null){
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection(urlConnection);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return connection;
    }
}
