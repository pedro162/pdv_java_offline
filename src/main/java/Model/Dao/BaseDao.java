/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pedro
 */
public class BaseDao {
    protected String table;
    
    protected void setTable(String table){
        this.table = table;
    }
    
    protected String getTable(){
        return this.table;
    }
    
    public ResultSet select(String []args, ArrayList<Map> filters) throws SQLException{
        String colunas = "";
        if(args.length > 0){
            
            colunas = this.buildColumns(args);
            if(colunas.length() == 0){
                colunas = " * ";
            }
            
        }else{
            colunas = " * ";
        }
        
        String filtros = "";    
        if(!filters.isEmpty()){  
            filtros = this.buildFilters(filters);
            if(filtros.length() > 0){
                filtros = " WHERE "+filtros;
            }            
        }
        
        String sql = "SELECT "+colunas+" FROM "+this.table+" "+filtros+" ";
        System.out.println("Sql: "+sql);
         
        Connection conn = Transaction.get();        
        PreparedStatement statement = conn.prepareStatement(sql);
        if(!filters.isEmpty()){
            for(int i=0; !(i == filters.size()); i++){
                if(filters.get(i).containsKey("comparado") && filters.get(i).containsKey("operador") && filters.get(i).containsKey("comparando")){
                   statement.setString((i+1), (String) filters.get(i).get("comparando"));
                }
            }
            
        }         
       
        statement.execute();        
        ResultSet result =  statement.getResultSet();
        return result;
    }
        
    public int create() throws SQLException{
        String columnNames[] = new String[] { "id" };
         
        String sql = "INSERT INTO usuarios (id, usuario, senha) VALUES (default, ?, ?);";
        
        Connection conn = Transaction.get();
        
        PreparedStatement statement = conn.prepareStatement(sql, columnNames);
        statement.setString(1, "teste");
        statement.setString(2, "123");
        
        int primkey = 0;
        if(statement.executeUpdate() > 0){
            java.sql.ResultSet generatedKeys = statement.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                primkey = generatedKeys.getInt(1);
            }
        }
        
        //final com.mysql.jdbc.PreparedStatement psFinal = (com.mysql.jdbc.PreparedStatement) statement;
        
                
        //int result = (int) psFinal.getLastInsertID();
        
        return primkey;
    }
    
    public int update(ArrayList<Map> colums, ArrayList<Map> filters) throws SQLException{
        String columnNames[] = new String[] { "id" };
         
        String campos = "";
        if(!colums.isEmpty()){  
            campos = this.buildFildsToUpdate(colums);          
        }
        
        String filtros = "";    
        if(!filters.isEmpty()){  
            filtros = this.buildFilters(filters);
            if(filtros.length() > 0){
                filtros = " WHERE "+filtros;
            }         
            
        }
        
        String sql = "UPDATE "+this.table+" SET ("+campos+") "+filtros+" ";
        System.out.println("Sql: "+sql);
        
        
        Connection conn = Transaction.get();
        
        PreparedStatement statement = conn.prepareStatement(sql);
        int contdor = 0;
        
        if(!colums.isEmpty()){
            for(int i=0; !(i == colums.size()); i++){
                if(colums.get(i).containsKey("comparado") && colums.get(i).containsKey("operador") && colums.get(i).containsKey("comparando")){
                   contdor ++;
                   statement.setString((contdor), (String) colums.get(i).get("comparando"));
                   
                }
            }
        }
        
        if(!filters.isEmpty()){
            for(int i=0; !(i == filters.size()); i++){
                if(filters.get(i).containsKey("comparado") && filters.get(i).containsKey("operador") && filters.get(i).containsKey("comparando")){
                   contdor ++;
                   statement.setString((contdor), (String) filters.get(i).get("comparando"));
                }
            }
        }
                
        int primkey = 0;
        if(statement.executeUpdate() > 0){
            java.sql.ResultSet generatedKeys = statement.getGeneratedKeys();
            if ( generatedKeys.next() ) {
                primkey = generatedKeys.getInt(1);
            }
        }
                
        return primkey;
    }
    
    public boolean delete(){
        boolean result = true;
        
        return result;
    }
    
    
    protected String buildColumns(String []args){
         String colunas = "";
        if(args.length > 0){
            for(int i=0; !(i == args.length); i++){
                colunas += args[i]+",";
            }
            colunas = colunas.substring(0, colunas.length() - 1);
            
        }
        
        return colunas;
    }
    
     protected String buildFilters(ArrayList<Map>filters){
        
        String filtros = "";
        
        if(!filters.isEmpty()){
            String lastOperador = "";
            for(int i=0; !(i == filters.size()); i++){
                if(filters.get(i).containsKey("comparado") && filters.get(i).containsKey("operador") && filters.get(i).containsKey("comparando")){
                    
                    filtros += " "+((String) filters.get(i).get("comparado") + (String) filters.get(i).get("operador") + " ? ");
                    if(filters.get(i).containsKey("next_operator")){
                        filtros += " "+((String) filters.get(i).get("next_operator"));
                        lastOperador = ((String) filters.get(i).get("next_operator"));
                    }else{
                        filtros += " AND";
                        lastOperador = " AND";
                    }
                   
                }
                
            }
            if(filtros.length() > 0){
                filtros = filtros.substring(0, filtros.length() - lastOperador.length());
            }
            
        }
         System.out.println("Filtros: "+filtros);
        return filtros;
    }
     
    protected String buildFildsToUpdate(ArrayList<Map>filters){
        
        String filtros = "";
        
        if(!filters.isEmpty()){
            String lastOperador = "";
            for(int i=0; !(i == filters.size()); i++){
                if(filters.get(i).containsKey("comparado") && filters.get(i).containsKey("operador") && filters.get(i).containsKey("comparando")){
                    
                    filtros += " "+((String) filters.get(i).get("comparado") + (String) filters.get(i).get("operador") + " ? ");
                    if(filters.get(i).containsKey("next_operator")){
                        filtros += " "+((String) filters.get(i).get("next_operator"));
                        lastOperador = ((String) filters.get(i).get("next_operator"));
                    }else{
                        filtros += " ,";
                        lastOperador = " ,";
                    }
                   
                }
                
            }
            if(filtros.length() > 0){
                filtros = filtros.substring(0, filtros.length() - lastOperador.length());
            }
            
        }
         System.out.println("Filtros: "+filtros);
        return filtros;
    }
    
    public ArrayList<Object> getResult(ResultSet result) throws SQLException{
        
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        
        ArrayList<Object> data = new ArrayList<>();
        while (result.next()) {
            Object[] values = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                values[i - 1] = result.getObject(i);
            }
            data.add(values);
        }
        
        return data;

        
    }
    
}
