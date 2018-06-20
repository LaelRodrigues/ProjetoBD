package joins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import dao.GenericDao;

public class LojaEndereco extends GenericDao {
	
	private String cnpjLoja;
	private String nome;
	private String email;
	private String cepLoja;
	private String uf;
	private String cidade;
	private String bairro;
	private String logradouro;
	
	public LojaEndereco() {}
	
	public LojaEndereco(String cnpjLoja, String nome, String email, String cepLoja, String uf, String cidade,
			String bairro, String logradouro, String telefone) {
		super();
		this.cnpjLoja = cnpjLoja;
		this.nome = nome;
		this.email = email;
		this.cepLoja = cepLoja;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
	}
	
	public List<LojaEndereco> listarLojasEnderecos() {
		synchronized (this) {
        ResultSet rs = null;
            
        List<LojaEndereco> lista = new Vector<LojaEndereco>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT cnpjLoja, nome, email, cepLoja, "
	                		+ "uf, cidade, bairro, logradouro "
	                		+ "FROM Loja Endereco "
	                		+ "Where cepLoja=cep;");
	                while (rs.next()) {
	                	LojaEndereco lojaJoin = new LojaEndereco();
	                	lojaJoin.setCnpjLoja(rs.getString("cnpjLoja"));
	    				lojaJoin.setNome(rs.getString("nome"));
	    				lojaJoin.setEmail(rs.getString("email"));
	    				lojaJoin.setCepLoja(rs.getString("cepLoja"));
	    				lojaJoin.setUf(rs.getString("uf"));
	    				lojaJoin.setCidade(rs.getString("cidade"));
	    				lojaJoin.setBairro(rs.getString("bairro"));
	                    lojaJoin.setLogradouro(logradouro);
	                    lista.add(lojaJoin);
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

	public String getCnpjLoja() {
		return cnpjLoja;
	}

	public void setCnpjLoja(String cnpjLoja) {
		this.cnpjLoja = cnpjLoja;
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

	public String getCepLoja() {
		return cepLoja;
	}

	public void setCepLoja(String cepLoja) {
		this.cepLoja = cepLoja;
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
