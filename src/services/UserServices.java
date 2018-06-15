package services;

import dao.UserDao;
import model.User;

public class UserServices {

	static UserDao userDao = new UserDao(); 
	
	public static UserDao getDao() {
		return userDao;
	}
	
	public static Boolean login(String usuario, String senha) {
		User remote;
		remote = userDao.buscar(usuario);
		return senha == remote.getSenha();
	}

}
