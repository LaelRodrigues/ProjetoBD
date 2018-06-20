package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.IProduto;
import model.Produto;

public class ProdutoJDBC extends GenericDao implements IProduto {
	
	public ProdutoJDBC() {
		super();
	}

	@Override
	public void atualizar(Produto produto) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE Produto SET ");
        buffer.append(returnFieldValuesBD(produto));
        buffer.append(" WHERE codigo=");
        buffer.append(produto.getCodigo());
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
	public void inserir(Produto produto) {
		StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO Produto (");
        buffer.append(this.retornarCamposBD());
        buffer.append(") VALUES (");
        buffer.append(this.retornarValoresBD(produto));
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
	public Produto buscar(Integer codigo) {
		
		Produto produto = new Produto();

		try {
			conectar();
            String sql = "SELECT * FROM Produto WHERE codigo=" + codigo;
                ResultSet rs = comando.executeQuery(sql);
                if (rs.next()) {
                	produto.setCodigo(rs.getInt("codigo"));
    				produto.setNome(rs.getString("nome"));
    				produto.setDescricao(rs.getString("descricao"));
    				produto.setD_validade(rs.getDate("d_validade"));
    				produto.setForneCnpj(rs.getString("forneCnpj"));
    				System.out.println(produto.getNome());
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return produto;
	}

	@Override
	public void remover(Produto produto) {
	
    	String sql ="DELETE FROM Produto WHERE codigo=" + produto.getCodigo()  + ";";
    	System.out.println(produto.getCodigo());
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
	public List<Produto> listarProduto() {

		List <Produto> listaProduto = new ArrayList<Produto>();

		Produto lojas ;
		try {
			conectar();
            String sql = "SELECT * FROM Produto";
                ResultSet rs = comando.executeQuery(sql);
                
                while(rs.next()) {
                	lojas = new Produto();
                	lojas.setCodigo(Integer.parseInt(rs.getString("codigo")));
                	lojas.setNome(rs.getString("nome"));
                	lojas.setDescricao(rs.getString("descricao"));
                	//lojas.setD_validade(rs.getString("cepForne"));
                	lojas.setPreco(Float.parseFloat(rs.getString("preco")));
                	lojas.setForneCnpj(rs.getString("forneCnpj"));
                	listaProduto.add(lojas);
                }
            
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaProduto;
	}
	
	protected String retornarCamposBD() {
    	return "codigo, nome, descricao, d_validade, preco, forneCnpj";
    }
	
	protected String returnFieldValuesBD(Produto produto) {
		
        StringBuffer buffer = new StringBuffer();
        buffer.append("codigo=");
        buffer.append(produto.getCodigo());
        buffer.append(", nome=");
        buffer.append(retornarValorStringBD(produto.getNome()));
        buffer.append(", descricao=");
        buffer.append(retornarValorStringBD(produto.getDescricao()));
        buffer.append(", d_validade=");
        buffer.append(retornarValorStringBD(produto.getD_validade().toString()));
        buffer.append(", preco=");
        buffer.append(produto.getPreco());
        buffer.append(", forneCnpj=");
        buffer.append(retornarValorStringBD(produto.getForneCnpj()));

        return buffer.toString();
    }
	
	 protected String retornarValoresBD(Produto produto) {
		 	
		 	return
		        produto.getCodigo()
		        + ", "
		        + retornarValorStringBD(produto.getNome())
		        + ", "
		        + retornarValorStringBD(produto.getDescricao())
		        + ", "
		        + retornarValorStringBD(produto.getD_validade().toString())
		        + ", "
		        + produto.getPreco()
		        + ", "
		        + retornarValorStringBD(produto.getForneCnpj());
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
