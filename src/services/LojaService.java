package services;

import dao.LojaJDBC;
import model.Loja;

public class LojaService {

	private static LojaJDBC lojaDao = new LojaJDBC();

	public static void cadastrar(Loja novo) throws Exception{
		
		
		if(novo.getCnpj().length() < 18){
			System.out.println("1");
			throw new Exception("cnpj invalido ou muito curto");
		}
		if (novo.getNome().length() < 2) {
			System.out.println("2");
			throw new Exception("Nome invalido");
		}

		if (novo.getEmail().length() < 3) {
			System.out.println("3");
			throw new Exception("Email invalido");
		}
		if (novo.getCepLoja().length() < 0) {
			System.out.println("4");
			throw new Exception("Cep invalido");
		}

		System.out.println("inserido " + novo.getCnpj() + " " + novo.getNome() + " " + novo.getEmail() + " "
				+ novo.getCepLoja());
		lojaDao.inserir(novo);
		lojaDao.commit();

	}
}

