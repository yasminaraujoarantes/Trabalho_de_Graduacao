package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="usuario")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    protected int id;
	
	@Column(name="nome")
    protected String nome;
    
	@Column(name="email")
    protected String email;
    
	@Column(name="senha")
    protected String senha;
	
	@Column(name="idade")
    protected int idade;
 
    public Usuario() {
    }
 
    public Usuario(int id) {
        this.id = id;
    }
 
    public Usuario(int id, String nome, String email, String senha, int idade) {
    	this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.id = id;
    }
     
    public Usuario(String nome, String email, String senha, int idade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getIdade() {
        return idade;
    }
 
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
