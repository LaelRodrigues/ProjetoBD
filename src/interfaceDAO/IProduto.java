package interfaceDAO;

import java.util.List;

import model.Produto;

public interface IProduto {
	 public void atualizar(Produto produto);
	 public void inserir(Produto produto);
	 public Produto buscar(Integer codigo);
	 public void remover(Produto produto);
	 public List<Produto> listarProduto();
}
