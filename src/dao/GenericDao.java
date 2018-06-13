package dao;

import java.sql.Connection;
import java.sql.Statement;

import util.DaoInfo;

public class GenericDao {

	protected String URL;
	protected String NOME;
	protected String SENHA;
	
	protected Connection con;  
	protected Statement comando;
	
	public GenericDao(){
		this.URL = DaoInfo.getURL();
		this.NOME = DaoInfo.getNOME();
		this.SENHA = DaoInfo.getSENHA();
	}
	
}
