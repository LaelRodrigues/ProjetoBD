package services;

import dao.UserDao;
import model.User;

public class UserServices {

	UserDao userDao = new UserDao(); 
	
	public UserDao getDao() {
		return userDao;
	}
	
	public Boolean login(String usuario, String senha) {
		User remote;
		remote = userDao.buscar(usuario);
		return senha == remote.getSenha();
	}

}
