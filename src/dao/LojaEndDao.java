package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import model.LojaXEndereco;

public class LojaEndDao extends GenericDao {
	

	
	public List<LojaXEndereco> listarLojasEnderecos() {
            
        List<LojaXEndereco> lista = new Vector<LojaXEndereco>();
	        try {
	        	conectar();
				
	        	ResultSet rs = comando.executeQuery("SELECT" + getCamposBD() + "Where cepLoja=cep;");
	                while (rs.next()) {
	                	LojaXEndereco lojaJoin = new LojaXEndereco();
	                	lojaJoin.setCnpjLoja(rs.getString("cnpjLoja"));
	    				lojaJoin.setNome(rs.getString("nome"));
	    				lojaJoin.setEmail(rs.getString("email"));
	    				lojaJoin.setCepLoja(rs.getString("cepLoja"));
	    				lojaJoin.setUf(rs.getString("uf"));
	    				lojaJoin.setCidade(rs.getString("cidade"));
	    				lojaJoin.setBairro(rs.getString("bairro"));
	                    lojaJoin.setLogradouro(rs.getString("logradouro"));
	                    lista.add(lojaJoin);
	                }
	            
	        } catch (SQLException SQLe) {
	            SQLe.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        return lista;
        
	}
	
	private String getCamposBD() {
		return "cnpjLoja, nome, email, cepLoja, uf, cidade, bairro, logradouro";
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
