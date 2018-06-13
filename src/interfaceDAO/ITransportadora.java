package interfaceDAO;

import java.util.List;

import model.Transportadora;;

public interface ITransportadora {
	
	 public void atualizar(Transportadora loja);
	 public void inserir(Transportadora loja);
	 public Transportadora buscar(String cnpj);
	 public void remover(Transportadora employee);
	 public List<Transportadora> listarTransportadoras();
}