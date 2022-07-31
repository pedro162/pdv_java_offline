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
public class Conexao {
    private static Connection connection = null;
    
    public static Connection getConnection(String drive)
    {
        try{
            
            String urlConnection = "";
                     
            if(drive.equals("sqlserver")){
                
            }else{
                urlConnection = "jdbc:mysql://localhost:3306/barbearia?user=root&password=";
            }
            
        
            //"jdbc:mysql://localhost:3306/barbearia?user=root&password=";
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(urlConnection); 
           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return connection;
    }
}
