package services;

import java.util.List;

import dao.LojaEmEstoqueProdutoDao;
import model.LojaXEmEstoqueXProduto;

public class LojaEstoqueProdutoService {
	private static LojaEmEstoqueProdutoDao estoqueLoja = new LojaEmEstoqueProdutoDao();
	
	public static List<LojaXEmEstoqueXProduto> buscarcnpj(String cnpj) throws Exception {
		if(cnpj.length() > 18)
			return estoqueLoja.buscar(cnpj);
		else
			throw new Exception("cnpj vazio");
		
	}

}
