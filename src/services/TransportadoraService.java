package services;

import dao.TransportadoraJDBC;
import model.Transportadora;

public class TransportadoraService {

	private static TransportadoraJDBC transportDao = new TransportadoraJDBC();

	public static void cadastrar(Transportadora novo) throws Exception {

			if (novo.getCnpj().length() < 18) {
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

			if (novo.getcepTransportadora().length() < 9) {
				System.out.println("4");
				throw new Exception("Cep invalido");
			}

			System.out.println("inserido " + novo.getCnpj() + " " + novo.getNome() + " " + novo.getEmail() + " "
					+ novo.getcepTransportadora());

			transportDao.inserir(novo);
			transportDao.commit();

		}

	public static void deletar(Transportadora novoend) {
		transportDao.remover(novoend);
	}

}
