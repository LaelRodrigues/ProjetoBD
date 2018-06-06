package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {
	
	   private static final String MySQLDriver = "com.mysql.jdbc.Driver";  
	  
	   public static Connection conexao(String url, String nome, String senha) 
			   throws ClassNotFoundException, SQLException {        
	         	Class.forName(MySQLDriver);  
	         	return DriverManager.getConnection(url, nome, senha); 
	   }
}
