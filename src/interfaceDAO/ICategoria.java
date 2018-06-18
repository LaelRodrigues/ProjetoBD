package interfaceDAO;

import java.util.List;

import model.Categoria;;

public interface ICategoria {
	
	 public void atualizar(Categoria categoria);
	 public void inserir(Categoria categoria);
	 public Categoria buscar(Integer codigoProd);
	 public void remover(Categoria categoria);
	 public List<Categoria> listarCategoriasProdutos();
}
