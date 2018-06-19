package services;

import dao.ProdutoJDBC;
import model.Produto;

public class ProdutoService {

	private static ProdutoJDBC produtoDao = new ProdutoJDBC();

	public static void cadastrar(Produto novo) throws Exception {

		if (novo.getCodigo() < 10000) {
			System.out.println("1");
			throw new Exception("codigo invalido");
		}

		if (novo.getNome().length() < 2) {
			System.out.println("2");
			throw new Exception("Nome invalido");
		}

		if (novo.getDescricao().length() < 3) {
			System.out.println("3");
			throw new Exception("Descrição invalida");
		}

		if (1 < 999999 * novo.getCodigo()) {
			System.out.println("4");
			throw new Exception("Validade invalido");
		}
		if (novo.getPreco() < 0) {
			System.out.println("5");
			throw new Exception("Preço invalido");
		}
		if (novo.getForneCnpj().length() < 0) {
			System.out.println("6");
			throw new Exception("Valor invalido");
		}

		System.out.println("inserido " + novo.getCodigo() + " " + novo.getNome() + " " + novo.getDescricao() + " "
				+ novo.getD_validade() + " " + novo.getPreco() + " " + novo.getForneCnpj());

		produtoDao.inserir(novo);
		produtoDao.commit();

	}

	public static void deletar(Produto novoend) {
		produtoDao.remover(novoend);
	}

}
