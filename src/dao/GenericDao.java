package dao;

import java.sql.Connection;
import java.sql.Statement;

public class GenericDao {

	protected String URL;
	protected String NOME;
	protected String SENHA;
	
	protected Connection con;  
	protected Statement comando;
	
	public GenericDao(String server, String user, String password){
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}
}
