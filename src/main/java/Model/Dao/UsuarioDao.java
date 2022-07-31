/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Dao;

import Model.Usuario.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pedro
 */
public class UsuarioDao extends BaseDao{
    protected Usuario usuario;
    
    public UsuarioDao(Usuario user){
        this.setTable("usuarios");
        this.usuario = user;
    }
    
    public boolean autenticar() throws SQLException{
        
        Map<String, String> filter = new HashMap<>();
        filter.put("comparado", "usuario");
        filter.put("operador", "=");
        filter.put("comparando", usuario.getUsuario());
        
        Map<String, String> filterPassword = new HashMap<>();
        filterPassword.put("comparado", "senha");
        filterPassword.put("operador", "=");
        filterPassword.put("comparando", usuario.getSenha());
        
        ArrayList<Map> filterList = new ArrayList<>();
        filterList.add(filter);
        filterList.add(filterPassword);
        
        String[] colunas = {"id", "usuario", "senha"};
        this.select(colunas, filterList);
    
        ResultSet result = this.select(colunas, filterList);
        System.out.println("Result");
        System.out.println(result);
        return result.next();
    }
    
    public boolean deslogar(){
        
        return true;
    }
    
    public Usuario getUsuario(int id){
        Usuario user = new Usuario(1, "peddro");
        
        
        return user;        
    }
    
    
}
