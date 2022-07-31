/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuario;

import Model.Pessoa.Pessoa;
import java.util.Date;

/**
 *
 * @author pedro
 */
public class Usuario extends Pessoa {
    
    protected String senha;
    protected String nivelAcesso;
    protected String usuario;;
   
    public Usuario(int id, String nome, String senha, String nivelAcesso){
        super(id, nome);
        this.usuario=nome;
        this.senha=senha;
        this.nivelAcesso=nivelAcesso;//
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public Usuario(int id, String nome){
        super(id, nome);
        this.nome=nome;
        //this.senha=senha;
    }
    
    public void setSenha(String senha){
        this.senha=senha;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public void setNivelAcesso(String nivelAcesso){
        this.nivelAcesso= nivelAcesso;
                
    }
    
    public String getNivelAcesso(){
        return this.nivelAcesso;
    }
    
    
    
    
}
