package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import model.LojaXEmEstoqueXProduto;
import model.PedidoPossuiProdutos;;

public class PedidoPossuiProdutosDao extends GenericDao {

	public List<PedidoPossuiProdutos> listarQtdProdutosLojas() {

		List<PedidoPossuiProdutos> lista = new ArrayList<PedidoPossuiProdutos>();
		try {
			conectar();

			ResultSet rs = comando
					.executeQuery("SELECT" + retornarCamposBD() + "FROM pedido, possui, produto where possui.codigo = produto.codigo and possui.idPedido = pedido.idPedido;");
			while (rs.next()) {
				PedidoPossuiProdutos produtoJoinPossuiJoinPedido = new PedidoPossuiProdutos();
				produtoJoinPossuiJoinPedido.setIdPedido(Integer.parseInt(rs.getString("idPedido")));
				produtoJoinPossuiJoinPedido.setCnpjForne(rs.getString("cnpjForne"));
				produtoJoinPossuiJoinPedido.setCnpjLoja(rs.getString("cnpjLoja"));
				produtoJoinPossuiJoinPedido.setCnpjTrans(rs.getString("cnpjTrans"));
				produtoJoinPossuiJoinPedido.setCodigo(Integer.parseInt(rs.getString("codigoProduto")));
				produtoJoinPossuiJoinPedido.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
				produtoJoinPossuiJoinPedido.setD_validade(null);
				produtoJoinPossuiJoinPedido.setDescricao(rs.getString("descricao"));
				produtoJoinPossuiJoinPedido.setNomeProd(rs.getString("nomeProd"));
				produtoJoinPossuiJoinPedido.setPreco(Float.parseFloat(rs.getString("preco")));
				produtoJoinPossuiJoinPedido.setForneCnpj(rs.getString("ForneCnpj"));
				
				lista.add(produtoJoinPossuiJoinPedido);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		}
		return lista;

	}

	public List<PedidoPossuiProdutos> buscar(Integer id) {
		List<PedidoPossuiProdutos> todosPedidosListados = listarQtdProdutosLojas(); // lista com todos os pedidos ordenados por id
		List<PedidoPossuiProdutos> listaProdutosPedido = new ArrayList<>(); // lista de produtos do pedido selecionado
		for(int i=0; i<todosPedidosListados.size(); i++) {
			if(todosPedidosListados.get(i).getIdPedido() == id) { // se o id procurado for igual ao id passado 
																			//ele insere no novo vetor os valores referentes ao campo I
				listaProdutosPedido.add(todosPedidosListados.get(i));
			}
		}
		return listaProdutosPedido;
	}
	
	protected String retornarCamposBD() {
		return " pedido.idPedido, pedido.cnpjForne, pedido.cnpjLoja, pedido.cnpjTrans, possui.codigo AS codigoProduto, possui.quantidade, produto.d_validade, produto.descricao, produto.nome, produto.preco, produto.ForneCnpj ";
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
