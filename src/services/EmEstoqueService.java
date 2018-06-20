package services;

import dao.Em_estoqueJDBC;
import model.Em_estoque;

public class EmEstoqueService {
	
	private static Em_estoqueJDBC emEstoque = new Em_estoqueJDBC();
	
	public static void cadastrar(Em_estoque novoEstoque) throws Exception {
		if(novoEstoque.getQuantidade() < 0) {
			throw new Exception("Quantidade Inválida");
		}
		else {
			emEstoque.inserir(novoEstoque);
			emEstoque.commit();
		}
	}
}
