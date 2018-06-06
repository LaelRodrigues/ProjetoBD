package interfaceDAO;

import java.util.List;

import entidade.Loja;

public interface ILoja {
	
	 public void atualizar(Loja loja);
	 public void inserir(Loja loja);
	 public Loja buscar(int cnpj);
	 public void remover(Loja employee);
	 public List<Loja> listarLojas();
}
