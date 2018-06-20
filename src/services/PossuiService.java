package services;

import dao.Em_estoqueJDBC;
import dao.PedidoJDBC;
import dao.PossuiJDBC;
import model.Em_estoque;
import model.Possui;

public class PossuiService {

	private static PossuiJDBC possuiJdbc = new PossuiJDBC();

	public static void cadastrar(Possui novoP) throws Exception {
		if (novoP.getQuantidade() < 0) {
			throw new Exception("Quantidade invalida");
		} else {
			possuiJdbc.inserir(novoP);
			possuiJdbc.commit();
		}
	}

	public static void buscar() {

	}
}
