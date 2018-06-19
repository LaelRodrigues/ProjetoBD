package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import interfaceDAO.IPossui;
import model.Possui;

public class PossuiJDBC extends GenericDao implements IPossui {
	
	public PossuiJDBC(){
		super();
	}

	@Override
	public void inserir(Possui possui) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Possui (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(possui));
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
	public void remover(Possui possui) {
    	
		String sql ="DELETE FROM Possui WHERE idPedido=" + possui.getIdPedido() + ";";
    	System.out.println(possui.getIdPedido());
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
	public List<Possui> listarPossui(){
		synchronized (this) {
            ResultSet rs = null;
            
	        List<Possui> produtosPedidos = new Vector<Possui>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT * FROM Possui");
	                while (rs.next()) {
	                	Possui possui = new Possui();
	                	possui.setCodigo(rs.getInt("idPedido"));
	    				possui.setCodigo(rs.getInt("codigo"));
	    				possui.setQuantidade(rs.getInt("quantidade"));
	                    produtosPedidos.add(possui);
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
	        return produtosPedidos;
        }
	}

	
	protected String retornarCamposBD() {
    	return "idPedido, codigo, quantidade";
    }
	
	protected String returnFieldValuesBD(Possui possui) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("idPedido=");
        buffer.append(possui.getIdPedido());
        buffer.append(", codigo=");
        buffer.append(possui.getCodigo());
        buffer.append(", quantidade=");
        buffer.append(possui.getQuantidade());
 

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Possui possui) { 	
	 	
		 return
	        possui.getIdPedido()
	        + ", "
	        + possui.getCodigo()
	        + ", "
	        + possui.getQuantidade();
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
