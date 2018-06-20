package services;

import java.util.List;

import dao.Em_estoqueJDBC;
import dao.PedidoJDBC;
import model.Em_estoque;
import model.Pedido;

public class PedidoService {

	private static PedidoJDBC pedidoJdbc = new PedidoJDBC();

	public static void cadastrar(Pedido novoP) throws Exception {
		if (novoP.getCnpjLoja().equals(null)) {
			throw new Exception("CNPJ loja invalido");
		} else {
			pedidoJdbc.inserir(novoP);
			pedidoJdbc.commit();
		}
	}

	public static void atualizar(Pedido novoP) throws Exception {
		if (novoP.getCnpjLoja().equals(null)) {
			throw new Exception("CNPJ loja invalido");
		} else {
			pedidoJdbc.atualizar(novoP);
			pedidoJdbc.commit();
		}
	}

	public static List<Pedido> buscar() {
		return pedidoJdbc.listarPedidos();
	}
	
	public static Integer buscarId() {
		return pedidoJdbc.listarPedidos().get(pedidoJdbc.listarPedidos().size()-1).getIdPedido();
	}
}
