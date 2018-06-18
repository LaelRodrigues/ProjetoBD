package interfaceDAO;

import java.util.List;

import model.User;

public interface IUser {
	
	 public void atualizar(User user);
	 public void inserir(User user);
	 public void remover(User user);
	 public List<User> listarUsuarios();
}