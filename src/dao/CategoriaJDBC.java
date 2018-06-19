package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import conexao.ConFactory;
import interfaceDAO.ICategoria;
import model.Categoria;
import model.Possui;

public class CategoriaJDBC extends GenericDao implements ICategoria {
	
	public CategoriaJDBC() {
		super();
	}
	
	@Override
	public void atualizar(Categoria categoria) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Categoria SET ");
        buffer.append(returnFieldValuesBD(categoria));
        buffer.append(" WHERE codigoProd=");
        buffer.append(categoria.getCodigoProd());
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
	public void inserir(Categoria categoria) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Categoria (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(categoria));
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
	public Categoria buscar(Integer codigoProd) {
		
		Categoria categoria = new Categoria();

		try {
			conectar();
            String sql = "SELECT * FROM Categoria WHERE codigoProd=" + codigoProd;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	categoria.setCodigoProd(rs.getInt("codigoProd"));
    				categoria.setCategoria(rs.getString("categoria"));
    				System.out.println(categoria.getCodigoProd());
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return categoria;
	}

	@Override
	public void remover(Categoria categoria) {
		
    	String sql ="DELETE FROM Categoria WHERE codigoProd=" + categoria.getCodigoProd() + ";";
    	System.out.println(categoria.getCodigoProd());
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
	public List<Categoria> listarCategoriasProdutos() {
		synchronized (this) {
            ResultSet rs = null;
            
	        List<Categoria> categorias = new Vector<Categoria>();
	        try {
	        	conectar();
				
	            try {
	                rs = comando.executeQuery("SELECT * FROM Categoria");
	                while (rs.next()) {
	                	Categoria categoria = new Categoria();
	                	categoria.setCategoria(rs.getString("categoria"));
	    				categoria.setCodigoProd(rs.getInt("codigoProd"));
	                    categorias.add(categoria);
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
	        return categorias;
        }
	}
	
	protected String retornarCamposBD() {
    	return "codigoProd, categoria";
    }
	
	protected String returnFieldValuesBD(Categoria categoria) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("codigoProd=");
        buffer.append(categoria.getCodigoProd());
        buffer.append(", categoria=");
        buffer.append(retornarValorStringBD(categoria.getCategoria()));

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Categoria categoria) {
		 	
		 	return
		        (categoria.getCodigoProd())
		        + ", "
		        + retornarValorStringBD(categoria.getCategoria());
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
