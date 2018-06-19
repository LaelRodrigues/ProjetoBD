package interfaceDAO;

import java.util.List;

import model.Telefone;

public interface ITelefone {
	
	public void atualizar(Telefone tefone);
	public void inserir(Telefone telefone);
	public void remover(Telefone telefone);
	public List<Telefone> listarTelefones(); 
}
