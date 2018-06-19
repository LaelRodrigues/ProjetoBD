package joins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import dao.GenericDao;

public class ForneEndereco extends GenericDao {
	
	private String cnpjForne;
	private String nome;
	private String email;
	private String cepForne;
	private String uf;
	private String cidade;
	private String bairro;
	private String logradouro;
	
	public ForneEndereco() {}
	
	public ForneEndereco(String cnpjForne, String nome, String email, String cepForne, String uf, String cidade,
			String bairro, String logradouro, String telefone) {
		super();
		this.cnpjForne = cnpjForne;
		this.nome = nome;
		this.email = email;
		this.cepForne = cepForne;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
	}
	
	public List<ForneEndereco> listarForneEnderecos() {
		synchronized (this) {
        ResultSet rs = null;
            
        List<ForneEndereco> lista = new Vector<ForneEndereco>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT cnpjForne, nome, email, cepForne, "
	                		+ "uf, cidade, bairro, logradouro "
	                		+ "FROM Fornecedor Endereco"
	                		+ "Where cepForne=cep;");
	                while (rs.next()) {
	                	ForneEndereco forneJoin = new ForneEndereco();
	                	forneJoin.setCnpjForne(rs.getString("cnpjForne"));
	                	forneJoin.setNome(rs.getString("nome"));
	                	forneJoin.setEmail(rs.getString("email"));
	                	forneJoin.setCepForne(rs.getString("cepForne"));
	                	forneJoin.setUf(rs.getString("uf"));
	                	forneJoin.setCidade(rs.getString("cidade"));
	                	forneJoin.setBairro(rs.getString("bairro"));
	                	forneJoin.setLogradouro(logradouro);
	                    lista.add(forneJoin);
	                }
	            } finally {
        			if (rs != null) {
        				try {
        					rs.close();
        				} catch (SQLException sqlEx) { 
        				} 
        				rs = null;
        			}
        			if (comando != null) {
        				try {
        					comando.close();
        				} catch (SQLException sqlEx) { 
        				}
        				comando = null;
        			}
	            }
	        } catch (SQLException SQLe) {
	            SQLe.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        return lista;
        }
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

	public String getCnpjForne() {
		return cnpjForne;
	}

	public void setCnpjForne(String cnpjForne) {
		this.cnpjForne = cnpjForne;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCepForne() {
		return cepForne;
	}

	public void setCepForne(String cepForne) {
		this.cepForne = cepForne;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
}
