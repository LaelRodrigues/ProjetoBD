package services;

import java.util.ArrayList;
import java.util.List;

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

	public static List<String> getListInt() {

		List<Produto> produtos = new ArrayList<Produto>();
		produtos = produtoDao.listarProduto();

		List<String> codigoForn = new ArrayList<String>();

		for (int i = 0; i < produtos.size(); i++) {
			codigoForn.add("" + produtos.get(i).getCodigo());
		}

		return codigoForn;
	}
	
	public static List<Produto> getList(){
		return produtoDao.listarProduto();
	}

	public static Produto getProduto(int codigo) {
		return produtoDao.buscar(codigo);
		
	}

	public static void atualizar(Produto novo) {
		produtoDao.atualizar(novo);
		try {
			produtoDao.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
