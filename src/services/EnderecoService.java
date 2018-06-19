package services;

import dao.EnderecoJDBC;
import model.Endereco;

public class EnderecoService {

	private static EnderecoJDBC enderecoDao;
	
	
	public static void cadastrar(Endereco novo) throws Exception {
		
		
		 System.out.println(novo.getCep() + " " + novo.getUf() + " " + novo.getCidade() + " " + novo.getBairro() + " " + novo.getLogradouro());

		if (novo.getCep().length() < 8) {
			System.out.println("1");
			throw new Exception("cep invalido");
		}

		if (novo.getUf().length() < 2) {
			System.out.println("2");
			throw new Exception("UF invalida");
		}

		if (novo.getCidade().length() < 3) {
			System.out.println("3");
			throw new Exception("cargo invalido");
		}
		
		if (novo.getBairro().length() < 3) {
			System.out.println("4");
			throw new Exception("Bairro invalido");
		}
		if (novo.getLogradouro().length() < 3) {
			System.out.println("5");
			throw new Exception("logradouro invalido");
		}
		

		else if (novo.getCep().length() > 0 && novo.getUf().length() >= 3 && novo.getCidade().length() >=  3 && novo.getBairro().length() >=  3 && novo.getLogradouro().length() >=  3) {
			if (enderecoDao.buscar(novo.getCep()).getCep() == null) {
				System.out.println("inserido " + novo.getCep() + " " + novo.getUf() + " " + novo.getCidade() + " " + novo.getBairro() + " " + novo.getLogradouro());
				enderecoDao.inserir(novo);
				enderecoDao.commit();
			} else{
				System.out.println("Endereço ja cadastrado");
				throw new Exception("Endereço ja cadastrado");
					
			}
		}
	}

}
