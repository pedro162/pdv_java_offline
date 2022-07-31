/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dao;

import java.sql.Connection;
import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
final public class Transaction {
    private static Connection conn;
    private static String defaultDriver = "mysql";
    
    private Transaction(){}
    
    public static void open(String drive) throws SQLException{
        if(Transaction.conn == null){
            if(drive == null){
                drive = Transaction.defaultDriver;
            }
            Transaction.conn = Conexao.getConnection(drive);
            Transaction.conn.setAutoCommit(false);//--- Desativo o auto commit aqui, uma forma de trabalhar com transações
            Transaction.conn.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
        }
    }
    
    
    public static Connection get(){
        return Transaction.conn;//Retorna a conexao ativa
    }
    
    public static boolean rollback() throws SQLException{
        if(Transaction.conn != null){
            Transaction.conn.rollback();
            Transaction.conn = null;
        }
        return true;
    }
    
    public static boolean close() throws SQLException{
        if(Transaction.conn != null){
            Transaction.conn.commit();//Aplica as operações realizadas
            Transaction.conn = null;
        }
        return true;
    }
    
    
    
}
