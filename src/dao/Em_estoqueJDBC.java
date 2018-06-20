package dao;

import java.sql.SQLException;

import conexao.ConFactory;
import interfaceDAO.IEm_estoque;
import model.Em_estoque;

public class Em_estoqueJDBC extends GenericDao implements IEm_estoque{
	
	public Em_estoqueJDBC(){
		super();
	}

	@Override
	public void inserir(Em_estoque estoque) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Em_estoque (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(estoque));
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
	
	protected String retornarCamposBD() {
    	return "cnpjL, codigoP, quantidade";
    }
	
	protected String returnFieldValuesBD(Em_estoque estoque) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpjL=");
        buffer.append(retornarValorStringBD(estoque.getCnpjL()));
        buffer.append(", codigoP=");
        buffer.append(estoque.getCodicoP());
        buffer.append(", quantidade=");
        buffer.append(estoque.getQuantidade());
 

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Em_estoque estoque) { 	
	 	
		 return
	        retornarValorStringBD(estoque.getCnpjL())
	        + ", "
	        + estoque.getCodicoP()
	        + ", "
	        + estoque.getQuantidade();
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
