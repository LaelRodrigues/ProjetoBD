package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import interfaceDAO.ITransportadora;
import model.Transportadora;

public class TransportadoraJDBC extends GenericDao implements ITransportadora {
	
	public TransportadoraJDBC() throws SQLException {
		super();
	}

	@Override
	public void atualizar(Transportadora transportadora) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Transportadora SET ");
        buffer.append(returnFieldValuesBD(transportadora));
        buffer.append(" WHERE cnpjTrans=");
        buffer.append(transportadora.getCnpj());
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
	public void inserir(Transportadora transportadora) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Transportadora (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(transportadora));
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
	public Transportadora buscar(String cnpj) {
		
		Transportadora transportadora = new Transportadora();

		try {
			conectar();
            String sql = "SELECT * FROM Transportadora WHERE cnpjTrans=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	transportadora.setCnpj(rs.getString("cnpjTrans"));
                	transportadora.setNome(rs.getString("nome"));
                	transportadora.setEmail(rs.getString("email"));
                	transportadora.setCepTransportadora(rs.getString("cepTrans"));
    				System.out.println(transportadora.getNome());
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return transportadora;
	}

	@Override
	public void remover(Transportadora transportadora) {
    	
		String sql ="DELETE FROM Transportadora WHERE cnpjTrans=" + transportadora.getCnpj() + ";";
    	System.out.println(transportadora.getCnpj());
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
	public List<Transportadora> listarTransportadoras() {
		synchronized (this) {
        ResultSet rs = null;
            
        List<Transportadora> trasps = new Vector<Transportadora>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT * FROM Transportadora");
	                while (rs.next()) {
	                	Transportadora trasp = new Transportadora();
	                	trasp.setCnpj(rs.getString("cnpjTrans"));
	    				trasp.setNome(rs.getString("nome"));
	    				trasp.setEmail(rs.getString("email"));
	    				trasp.setCepTransportadora(rs.getString("cepTrans"));
	                    trasps.add(trasp);
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
	        return trasps;
        }
	}
	
	protected String retornarCamposBD() {
    	return "cnpjTrans, nome, email, cepTrans";
    }
	
	protected String returnFieldValuesBD(Transportadora transportadora) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpjTrans=");
        buffer.append(retornarValorStringBD(transportadora.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(transportadora.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(transportadora.getEmail()));
        buffer.append(", cepTrans=");
        buffer.append(retornarValorStringBD(transportadora.getcepTransportadora()));

        return buffer.toString();
    }
	
	protected String retornarValoresBD(Transportadora transportadora) {
		 	
		 	return
		        retornarValorStringBD(transportadora.getCnpj())
		        + ", "
		        + retornarValorStringBD(transportadora.getNome())
		        + ", "
		        + retornarValorStringBD(transportadora.getEmail())
		        + ", "
		        + retornarValorStringBD(transportadora.getcepTransportadora());
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