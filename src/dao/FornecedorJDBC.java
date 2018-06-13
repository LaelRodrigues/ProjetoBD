package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.IFornecedor;
import model.Fornecedor;

public class FornecedorJDBC implements IFornecedor {
	
	private String URL;
	private String NOME;
	private String SENHA;
	
	private Connection con;  
	private Statement comando;
	
	public FornecedorJDBC(String server, String user, String password) throws SQLException {
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}

	@Override
	public void atualizar(Fornecedor fornecedor) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE loja SET ");
        buffer.append(returnFieldValuesBD(fornecedor));
        buffer.append(" WHERE cnpj=");
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
        buffer.append("INSERT INTO Loja (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(fornecedor));
        buffer.append(")");
        String sql = buffer.toString();

    	try {
			conectar();
    		comando.execute(sql);
    		System.out.println("estou aqui");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Fornecedor buscar(String cnpj) {
		
		Fornecedor loja = new Fornecedor();

		try {
			conectar();
            String sql = "SELECT * FROM loja WHERE cnpjLoja=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	loja.setCnpj(rs.getString("cnpjLoja"));
    				loja.setNome(rs.getString("nome"));
    				loja.setEmail(rs.getString("email"));
    				loja.setCepFornecedor(rs.getString("cepLoja"));
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
	public void remover(Fornecedor fornecedor) {
		if(fornecedor.getCnpj()=="") {
			fornecedor.setCnpj("\"\"");
			System.out.println(fornecedor.getCnpj());
		}
    	String sql ="DELETE FROM Loja WHERE cnpjLoja=" + fornecedor.getCnpj() + ";";
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
    	return "cnpjLoja, nome, email, cepLoja";
    }
	
	protected String returnFieldValuesBD(Fornecedor fornecedor) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpj=");
        buffer.append(retornarValorStringBD(fornecedor.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(fornecedor.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(fornecedor.getEmail()));
        buffer.append(", cep=");
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