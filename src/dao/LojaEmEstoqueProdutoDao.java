package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import model.LojaXEmEstoqueXProduto;;

public class LojaEmEstoqueProdutoDao extends GenericDao {

	public List<LojaXEmEstoqueXProduto> listarQtdProdutosLojas() {

		List<LojaXEmEstoqueXProduto> lista = new ArrayList<LojaXEmEstoqueXProduto>();
		try {
			conectar();

			ResultSet rs = comando
					.executeQuery("SELECT" + retornarCamposBD() + "FROM Loja, Em_estoque, Produto Where cnpjLoja=cnpjL AND codigo=codigoP;");
			while (rs.next()) {
				LojaXEmEstoqueXProduto produtoJoinLoja = new LojaXEmEstoqueXProduto();
				produtoJoinLoja.setCnpj(rs.getString("cnpjLoja"));
				produtoJoinLoja.setNome(rs.getString("nomeLoja"));
				produtoJoinLoja.setEmail(rs.getString("email"));
				produtoJoinLoja.setCepLoja(rs.getString("cepLoja"));
				produtoJoinLoja.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
				produtoJoinLoja.setCodigo(Integer.parseInt(rs.getString("codigo")));
				produtoJoinLoja.setNomeProd(rs.getString("nomeProd"));
				produtoJoinLoja.setDescricao(rs.getString("descricao"));
				produtoJoinLoja.setD_validade(null);
				produtoJoinLoja.setPreco(Float.parseFloat(rs.getString("preco")));
				produtoJoinLoja.setForneCnpj(rs.getString("ForneCnpj"));
				produtoJoinLoja.setNomeProd(rs.getString("nomeProd"));
				
				lista.add(produtoJoinLoja);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException SQLe) {
			System.out.println("Ta dando erro aqui, infelizmente");
			SQLe.printStackTrace();
		}
		return lista;

	}

	public List<LojaXEmEstoqueXProduto> buscar(String cnpj) {
		List<LojaXEmEstoqueXProduto> lojaEstoqueProdutoTotal = listarQtdProdutosLojas();
		List<LojaXEmEstoqueXProduto> lojaEstoqueProduto = new ArrayList<>();
		for(int i=0; i<lojaEstoqueProdutoTotal.size(); i++) {
			if(lojaEstoqueProdutoTotal.get(i).getCnpj().equals(cnpj)) { // se o cnpj procurado for igual ao cnpj do campo I do 
																			//vetor ele insere no novo vetor os valores referentes ao campo I
				lojaEstoqueProduto.add(lojaEstoqueProdutoTotal.get(i));
			}
		}
		return lojaEstoqueProduto;
	}
	
	protected String retornarCamposBD() {
		return " cnpjLoja, Loja.nome AS nomeLoja, email, cepLoja, quantidade, codigo, Produto.nome AS nomeProd, descricao, d_validade, preco, forneCnpj ";
	}

	private void conectar() throws ClassNotFoundException, SQLException {
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
