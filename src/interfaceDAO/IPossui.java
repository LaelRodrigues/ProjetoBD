package interfaceDAO;

import java.util.List;

import model.Possui;

public interface IPossui {
	
	public void inserir(Possui pedido);
	public void remover(Possui pedido);
	public List<Possui> listarPossui();

}
