package interfaceDAO;

import java.util.List;

import model.Pedido;

public interface IPedido {
	
	public void atualizar(Pedido pedido);
	public void inserir(Pedido pedido);
	public Pedido buscar(Integer idPedido);
	public void remover(Pedido pedido);
	public List<Pedido> listarPedidos();
}
