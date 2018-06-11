package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.ILoja;
import model.Loja;

public class LojaJDBC implements ILoja {
	
	private String URL;
	private String NOME;
	private String SENHA;
	
	private Connection con;  
	private Statement comando;
	
	public LojaJDBC(String server, String user, String password) throws SQLException {
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}

	@Override
	public void atualizar(Loja loja) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE loja SET ");
        buffer.append(returnFieldValuesBD(loja));
        buffer.append(" WHERE cnpj=");
        buffer.append(loja.getCnpj());
        String sql = buffer.toString();
        
    	try {
			conectar();
    		comando.execute(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void inserir(Loja loja) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Loja (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(loja));
        buffer.append(")");
        String sql = buffer.toString();

    	try {
			conectar();
    		comando.execute(sql);
    		System.out.println("estou aqui");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Loja buscar(String cnpj) {
		
		Loja loja = new Loja();

		try {
			conectar();
            String sql = "SELECT * FROM loja WHERE cnpjLoja=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	loja.setCnpj(rs.getString("cnpjLoja"));
    				loja.setNome(rs.getString("nome"));
    				loja.setEmail(rs.getString("email"));
    				loja.setCepLoja(rs.getString("cepLoja"));
    				System.out.println(loja.getNome());
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return loja;
	}

	@Override
	public void remover(Loja loja) {
		if(loja.getCnpj()=="") {
			loja.setCnpj("\"\"");
			System.out.println(loja.getCnpj());
		}
    	String sql_2 ="DELETE FROM Loja WHERE cnpjLoja=" + loja.getCnpj() + ";";
    	System.out.println(loja.getCnpj());
    	try {
			conectar();
    		//comando.execute(sql_1);
    		comando.execute(sql_2);
    		//comando.execute(sql_3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Loja> listarLojas() {
		return null;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjLoja, nome, email, cepLoja";
    }
	
	protected String returnFieldValuesBD(Loja loja) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpj=");
        buffer.append(retornarValorStringBD(loja.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(loja.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(loja.getEmail()));
        buffer.append(", cep=");
        buffer.append(loja.getCepLoja());

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Loja loja) {
		 	
		 	return
		        retornarValorStringBD(loja.getCnpj())
		        + ", "
		        + retornarValorStringBD(loja.getNome())
		        + ", "
		        + retornarValorStringBD(loja.getEmail())
		        + ", "
		        + loja.getCepLoja();
	    }
	    
	    private String retornarValorStringBD(String valor) {
	        if (valor != null && !"".equals(valor)) {
	            valor = "'" + valor + "'";
	        } else {
	            valor = "'"+"'";
	        }
	        return valor;
	    }
	
	private void conectar() throws ClassNotFoundException, SQLException  {
        con = ConFactory.conexao(URL, NOME, SENHA);
        con.setAutoCommit(false);
        comando = con.createStatement();  
        System.out.println("Conectado!");     
	}	  
	
	public void commit() throws Exception {
    	try {
			this.commitTransaction();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
    
	public void cancelTransaction() throws Exception {
	    if (con == null) {
	        throw new Exception("There is no opened connection");
	    }
	    try {
	        con.rollback();
	    } finally {
	        con.close();
	        con = null;
	    }
	}

	public void commitTransaction() throws Exception {
	    if (con == null) {
	        throw new Exception("There is no opened connection");
	    }
	    try {
	        con.commit();
	    } finally {
	        con.close();
	        con = null;
	    }
	}
	

}
