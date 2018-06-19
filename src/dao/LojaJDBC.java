package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.ILoja;
import model.Loja;

public class LojaJDBC extends GenericDao implements ILoja {
	
	public LojaJDBC(){
		super();
	}

	@Override
	public void atualizar(Loja loja) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Loja SET ");
        buffer.append(returnFieldValuesBD(loja));
        buffer.append(" WHERE cnpjLoja=");
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
            String sql = "SELECT * FROM Loja WHERE cnpjLoja=" + cnpj;
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
    	String sql ="DELETE FROM Loja WHERE cnpjLoja=" + loja.getCnpj() + ";";
    	System.out.println(loja.getCnpj());
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
	public List<Loja> listarLojas() {

		List<Loja> listaLojas = new ArrayList<Loja>();
		Loja loja;
		
		try{
			conectar();
			String sql = "SELECT * FROM Loja";
            ResultSet rs = comando.executeQuery(sql);
            
            while(rs.next()) {
            	loja = new Loja();
            	loja.setCnpj(rs.getString("cnpjLoja"));
            	loja.setNome(rs.getString("nome"));
            	loja.setEmail(rs.getString("email"));
            	loja.setCepLoja(rs.getString("cepLoja"));
            	listaLojas.add(loja);
            }
		} catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaLojas;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjLoja, nome, email, cepLoja";
    }
	
	protected String returnFieldValuesBD(Loja loja) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpjLoja=");
        buffer.append(retornarValorStringBD(loja.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(loja.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(loja.getEmail()));
        buffer.append(", cepLoja=");
        buffer.append(retornarValorStringBD(loja.getCepLoja()));

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
		        + retornarValorStringBD(loja.getCepLoja());
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
