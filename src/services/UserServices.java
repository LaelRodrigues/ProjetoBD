package services;

import dao.UserDao;
import model.User;

public class UserServices {

	private static UserDao userDao = new UserDao();

	public static Boolean login(String usuario, String senha) {
		User remote;
		remote = userDao.buscar(usuario);
		return senha.equals(remote.getSenha());
	}

	public static void cadastrar(User novo) throws Exception {

		if (novo.getNome().length() == 0) {
			System.out.println("1");
			throw new Exception("nome invalido");
		}

		if (novo.getSenha().length() < 3) {
			System.out.println("2");
			throw new Exception("senha invalida ou muito curta");
		}

		if (novo.getCargo() == null) {
			System.out.println("3");
			throw new Exception("cargo invalido");
		}

		else if (novo.getNome().length() > 0 && novo.getSenha().length() >= 3 && novo.getCargo() != null) {
			System.out.println("result = " + userDao.buscar(novo.getNome()).getNome());
			if (userDao.buscar(novo.getNome()).getNome() == null) {
				System.out.println("inserido " + novo.getNome() + " " + novo.getSenha() + " " + novo.getCargo());
				userDao.inserir(novo);
				userDao.commit();
			} else{
				System.out.println("usuario ja cadastrado");
				throw new Exception("usuario ja cadastrado");
					
			}
		}

	}

}
