package interfaceDAO;

import java.util.List;

import model.Endereco;

public interface IEndereco {
	
	 public void atualizar(Endereco endereco);
	 public void inserir(Endereco endereco);
	 public Endereco buscar(String cep);
	 public void remover(Endereco enderco);
	 public List<Endereco> listarEnderecos();
}
