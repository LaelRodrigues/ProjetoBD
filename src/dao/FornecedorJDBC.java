package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.IFornecedor;
import model.Fornecedor;

public class FornecedorJDBC extends GenericDao implements IFornecedor {
	
	public FornecedorJDBC() {
	}

	@Override
	public void atualizar(Fornecedor fornecedor) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Fornecedor SET ");
        buffer.append(returnFieldValuesBD(fornecedor));
        buffer.append(" WHERE cnpjForne=");
        buffer.append(fornecedor.getCnpj());
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
	public void inserir(Fornecedor fornecedor) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Fornecedor (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(fornecedor));
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
	public Fornecedor buscar(String cnpj) {
		
		Fornecedor fornecedor = new Fornecedor();

		try {
			conectar();
            String sql = "SELECT * FROM Fornecedor WHERE cnpjForne=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	fornecedor.setCnpj(rs.getString("cnpjForne"));
                	fornecedor.setNome(rs.getString("nome"));
                	fornecedor.setEmail(rs.getString("email"));
                	fornecedor.setCepFornecedor(rs.getString("cepForne"));
    				System.out.println(fornecedor.getNome());
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return fornecedor;
	}

	@Override
	public void remover(Fornecedor fornecedor) {
		if(fornecedor.getCnpj()=="") {
			fornecedor.setCnpj("\"\"");
			System.out.println(fornecedor.getCnpj());
		}
    	String sql ="DELETE FROM Fornecedor WHERE cnpjForne=" + fornecedor.getCnpj() + ";";
    	System.out.println(fornecedor.getCnpj());
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
	public List<Fornecedor> listarFornecedores() {
		return null;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjForne, nome, email, cepForne";
    }
	
	protected String returnFieldValuesBD(Fornecedor fornecedor) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpjForne=");
        buffer.append(retornarValorStringBD(fornecedor.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(fornecedor.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(fornecedor.getEmail()));
        buffer.append(", cepForne=");
        buffer.append(retornarValorStringBD(fornecedor.getcepFornecedor()));

        return buffer.toString();
    }
	
	protected String retornarValoresBD(Fornecedor fornecedor) {
		 	
		 	return
		        retornarValorStringBD(fornecedor.getCnpj())
		        + ", "
		        + retornarValorStringBD(fornecedor.getNome())
		        + ", "
		        + retornarValorStringBD(fornecedor.getEmail())
		        + ", "
		        + retornarValorStringBD(fornecedor.getcepFornecedor());
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