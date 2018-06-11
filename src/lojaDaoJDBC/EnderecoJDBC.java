package lojaDaoJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.ConFactory;
import entidade.Endereco;
import interfaceDAO.IEndereco;

public class EnderecoJDBC implements IEndereco {
	
	private String URL;
	private String NOME;
	private String SENHA;
	
	private Connection con;  
	private Statement comando;
	
	public EnderecoJDBC(String server, String user, String password) throws SQLException {
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}

	@Override
	public void atualizar(Endereco endereco) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Endereco SET ");
        buffer.append(returnFieldValuesBD(endereco));
        buffer.append(" WHERE cep=");
        buffer.append(endereco.getCep());
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
	public void inserir(Endereco endereco) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Endereco (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(endereco));
        buffer.append(")");
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
	public Endereco buscar(String cep) {
		
		Endereco endereco = new Endereco();

		try {
			conectar();
            String sql = "SELECT * FROM Endereco WHERE cep=" + cep;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	endereco.setCep(rs.getString("cep"));
    				endereco.setUf(rs.getString("uf"));
    				endereco.setCidade(rs.getString("cidade"));
    				endereco.setBairro(rs.getString("bairro"));
    				endereco.setLogradouro(rs.getString("logradouro"));
    				System.out.println(returnFieldValuesBD(endereco));
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return endereco;
	}

	@Override
	public void remover(Endereco endereco) {
		if(endereco.getCep()=="") {
			endereco.setCep("\"\"");
			System.out.println(endereco.getCep());
		}
    	String sql_2 ="DELETE FROM Endereco WHERE cep=" + endereco.getCep() + ";";
    	System.out.println(endereco.getCep());
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

	public List<Endereco> listarEnderecos() {
		return null;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjLoja, nome, email, cepLoja";
    }
	
	protected String returnFieldValuesBD(Endereco endereco) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cep=");
        buffer.append(retornarValorStringBD(endereco.getCep()));
        buffer.append(", uf=");
        buffer.append(retornarValorStringBD(endereco.getUf()));
        buffer.append(", cidade=");
        buffer.append(retornarValorStringBD(endereco.getCidade()));
        buffer.append(", bairro=");
        buffer.append(retornarValorStringBD(endereco.getBairro()));
        buffer.append(", logradouro=");
        buffer.append(endereco.getLogradouro());

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Endereco endereco) {
		 	
		 	return
		        retornarValorStringBD(endereco.getCep())
		        + ", "
		        + retornarValorStringBD(endereco.getUf())
		        + ", "
		        + retornarValorStringBD(endereco.getCidade())
		        + ", "
		        + retornarValorStringBD(endereco.getBairro())
		        + ", "
		        + endereco.getLogradouro();
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
