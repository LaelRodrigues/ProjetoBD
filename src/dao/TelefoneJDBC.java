package dao;

import java.sql.SQLException;

import conexao.ConFactory;
import interfaceDAO.ITelefone;
import model.Telefone;

public class TelefoneJDBC extends GenericDao implements ITelefone {
	
	public TelefoneJDBC(){
		super();
	}

	@Override
	public void atualizar(Telefone telefone) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Telefone SET ");
        buffer.append(returnFieldValuesBD(telefone));
        buffer.append(" WHERE telefone=");
        buffer.append(telefone.getTelefone());
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
	public void inserir(Telefone telefone) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Telefone (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(telefone));
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
	public void remover(Telefone telefone) {
    	String sql ="DELETE FROM Telefone WHERE telefone=" + telefone.getTelefone() + ";";
    	System.out.println(telefone.getTelefone());
    	try {
			conectar();
    		comando.execute(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected String retornarCamposBD() {
    	return "telefone, cnpj";
    }
	
	protected String returnFieldValuesBD(Telefone telefone) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("telefone=");
        buffer.append(retornarValorStringBD(telefone.getTelefone()));
        buffer.append(", cnpj=");
        buffer.append(retornarValorStringBD(telefone.getCnpj()));

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Telefone telefone) {
		 	
		 	return
		        retornarValorStringBD(telefone.getTelefone())
		        + ", "
		        + retornarValorStringBD(telefone.getCnpj());
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
