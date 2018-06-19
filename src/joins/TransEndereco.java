package joins;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import dao.GenericDao;

public class TransEndereco extends GenericDao {
	
	private String cnpjTrans;
	private String nome;
	private String email;
	private String cepTrans;
	private String uf;
	private String cidade;
	private String bairro;
	private String logradouro;
	
	public TransEndereco() {}
	
	public TransEndereco(String cnpjTrans, String nome, String email, String cepTrans, String uf, String cidade,
			String bairro, String logradouro, String telefone) {
		super();
		this.cnpjTrans = cepTrans;
		this.nome = nome;
		this.email = email;
		this.cepTrans = cepTrans;
		this.uf = uf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
	}
	
	public List<TransEndereco> listarTransEnderecos() {
		synchronized (this) {
        ResultSet rs = null;
            
        List<TransEndereco> lista = new Vector<TransEndereco>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT cnpjTrans, nome, email, cepTrans, "
	                		+ "uf, cidade, bairro, logradouro "
	                		+ "FROM Trasportadora Endereco"
	                		+ "Where cepTrans=cep;" );
	                while (rs.next()) {
	                	TransEndereco transJoin = new TransEndereco();
	                	transJoin.setCnpjTrans(rs.getString("cnpjTrans"));
	                	transJoin.setNome(rs.getString("nome"));
	                	transJoin.setEmail(rs.getString("email"));
	                	transJoin.setCepTrans(rs.getString("cepTrans"));
	                	transJoin.setUf(rs.getString("uf"));
	                	transJoin.setCidade(rs.getString("cidade"));
	                	transJoin.setBairro(rs.getString("bairro"));
	                	transJoin.setLogradouro(logradouro);
	                    lista.add(transJoin);
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

	public String getCnpjTrans() {
		return cnpjTrans;
	}

	public void setCnpjTrans(String cnpjTrans) {
		this.cnpjTrans = cnpjTrans;
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

	public String getCepTrans() {
		return cepTrans;
	}

	public void setCepTrans(String cepTrans) {
		this.cepTrans = cepTrans;
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
