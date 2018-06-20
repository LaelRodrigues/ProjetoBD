package services;

import java.util.List;

import dao.LojaEmEstoqueProdutoDao;
import dao.PedidoPossuiProdutosDao;
import model.LojaXEmEstoqueXProduto;
import model.PedidoPossuiProdutos;

public class PedidoPossuiProdutoService {
	private static PedidoPossuiProdutosDao listaPedidos = new PedidoPossuiProdutosDao();
	
	public static List<PedidoPossuiProdutos> buscarId(Integer id) {
		return listaPedidos.buscar(id);
	}

}
