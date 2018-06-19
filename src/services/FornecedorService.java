package services;

import java.util.ArrayList;
import java.util.List;

import dao.FornecedorJDBC;
import model.Fornecedor;

public class FornecedorService {

	private static FornecedorJDBC fornecedorDao = new FornecedorJDBC();

	public static FornecedorJDBC getDao() {
		return fornecedorDao;
	}

	public static void cadastrar(Fornecedor forne) throws Exception {
		System.out.println(forne.getCnpj() + " " + forne.getNome() + " " + forne.getEmail() +" "+ forne.getcepFornecedor());

		if (forne.getCnpj().length() < 18) {
			System.out.println("1");
			throw new Exception("cnpj invalido ou muito curto");
		}

		if (forne.getNome().length() < 3) {
			System.out.println("2");
			throw new Exception("nome invalido ou muito curto");
		}
		
		if (forne.getEmail().length() < 3) {
			System.out.println("3");
			throw new Exception("Email invalido ou muito curto");
		}
		
		if (forne.getcepFornecedor().length() < 9) {
			System.out.println("4");
			throw new Exception("senha invalida ou muito curta");
		}

		fornecedorDao.inserir(forne);
		fornecedorDao.commit();

	}
	
	public static Fornecedor buscarcnpj(String cnpj) throws Exception {
		if(cnpj.length() > 18)
			return fornecedorDao.buscar(cnpj);
		else
			throw new Exception("cnpj vazio");
		
	} 
	
	public static void removerCnpj(String cnpj) throws Exception {
		fornecedorDao.remover(buscarcnpj(cnpj));
		try {
			fornecedorDao.commit();
		} catch (Exception e) {
			System.out.println("erro da remo��o");
		}
		
	}
	
	public static List<String> getList(){
		
		List <Fornecedor> fornecedores = new ArrayList<Fornecedor>(); 
		fornecedores = fornecedorDao.listarFornecedores(); 
		
		List <String> cnpjForn = new ArrayList<String>();
		
		for(int i = 0; i < fornecedores.size(); i++){
			cnpjForn.add(fornecedores.get(i).getCnpj());
		}
		
		return cnpjForn;
	}

}
