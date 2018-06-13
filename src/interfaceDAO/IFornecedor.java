package interfaceDAO;

import java.util.List;

import model.Fornecedor;

public interface IFornecedor {
	
	 public void atualizar(Fornecedor loja);
	 public void inserir(Fornecedor loja);
	 public Fornecedor buscar(String cnpj);
	 public void remover(Fornecedor employee);
	 public List<Fornecedor> listarFornecedores();
}