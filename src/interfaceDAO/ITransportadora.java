package interfaceDAO;

import java.util.List;

import model.Transportadora;;

public interface ITransportadora {
	
	 public void atualizar(Transportadora trasp);
	 public void inserir(Transportadora trasp);
	 public Transportadora buscar(String cnpj);
	 public void remover(Transportadora trasp);
	 public List<Transportadora> listarTransportadoras();
}