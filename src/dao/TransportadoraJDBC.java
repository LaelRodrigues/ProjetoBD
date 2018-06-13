package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.ITransportadora;
import model.Transportadora;

public class TransportadoraJDBC implements ITransportadora {
	
	private String URL;
	private String NOME;
	private String SENHA;
	
	private Connection con;  
	private Statement comando;
	
	public TransportadoraJDBC(String server, String user, String password) throws SQLException {
		this.URL = server;
		this.NOME = user;
		this.SENHA = password;
	}

	@Override
	public void atualizar(Transportadora transportadora) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE transportadora SET ");
        buffer.append(returnFieldValuesBD(transportadora));
        buffer.append(" WHERE cnpj=");
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
    		System.out.println("estou aqui");
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
            String sql = "SELECT * FROM transportadora WHERE cnpjTransportadora=" + cnpj;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	transportadora.setCnpj(rs.getString("cnpjTransportadora"));
                	transportadora.setNome(rs.getString("nome"));
                	transportadora.setEmail(rs.getString("email"));
                	transportadora.setCepTransportadora(rs.getString("cepTransportadora"));
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
		if(transportadora.getCnpj()=="") {
			transportadora.setCnpj("\"\"");
			System.out.println(transportadora.getCnpj());
		}
    	String sql ="DELETE FROM Transportadora WHERE cnpjTransportadora=" + transportadora.getCnpj() + ";";
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
		return null;
	}
	
	protected String retornarCamposBD() {
    	return "cnpjTransportadora, nome, email, cepTransportadora";
    }
	
	protected String returnFieldValuesBD(Transportadora transportadora) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("cnpj=");
        buffer.append(retornarValorStringBD(transportadora.getCnpj()));
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(transportadora.getNome()));
        buffer.append(", email=");
        buffer.append(retornarValorStringBD(transportadora.getEmail()));
        buffer.append(", cep=");
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