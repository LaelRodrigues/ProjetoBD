package interfaceDAO;

import java.util.List;

import model.Em_estoque;

public interface IEm_estoque {
	
	public void inserir(Em_estoque estoque);
	public List<Em_estoque> listarProdutosEstoque();
}
