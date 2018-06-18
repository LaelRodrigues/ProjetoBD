package interfaceDAO;

import java.util.List;

import model.Fornecedor;

public interface IFornecedor {
	
	 public void atualizar(Fornecedor forne);
	 public void inserir(Fornecedor forne);
	 public Fornecedor buscar(String cnpj);
	 public void remover(Fornecedor forne);
	 public List<Fornecedor> listarFornecedores();
}