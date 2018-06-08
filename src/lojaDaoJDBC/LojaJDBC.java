package lojaDaoJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.ConFactory;
import entidade.Loja;
import interfaceDAO.ILoja;

public class LojaJDBC implements ILoja {
	
	private String URL;
	private String NOME;
	private String SENHA;
	
	private Connection con;  
	private Statement comando;
	
	public LojaJDBC(String server, String user, String password) throws SQLException {
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}

	@Override
	public void atualizar(Loja loja) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE loja SET ");
        buffer.append(returnFieldValuesBD(loja));
        buffer.append(" WHERE cnpj=");
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
    		System.out.println("estou aqui");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Loja buscar(int cnpj) {
		
		Loja loja = new Loja();

		try {
			conectar();
            String sql = "SELECT * FROM loja WHERE cnpjLoja=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	loja.setCnpj(rs.getInt("cnpjLoja"));
    				loja.setNome(rs.getString("nome"));
    				loja.setEmail(rs.getString("email"));
    				loja.setCepLoja(rs.getInt("cepLoja"));
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
	public void remover(Loja employee) {
	}

	@Override
	public List<Loja> listarLojas() {
		return null;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjLoja, nome, email, cepLoja";
    }
	
	protected String returnFieldValuesBD(Loja loja) {
		
		String cnpj_l = Integer.toString(loja.getCnpj());
	 	String cep = Integer.toString(loja.getCepLoja());
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpj=");
        buffer.append(retornarValorStringBD(cnpj_l));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(loja.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(loja.getEmail()));
        buffer.append(", cep=");
        buffer.append(cep);

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Loja loja) {
		 	String cnpj = Integer.toString(loja.getCnpj());
		 	String cep = Integer.toString(loja.getCepLoja());
		 	return
		        retornarValorStringBD(cnpj)
		        + ", "
		        + retornarValorStringBD(loja.getNome())
		        + ", "
		        + retornarValorStringBD(loja.getEmail())
		        + ", "
		        + cep;
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
