package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import model.FornecedorXEndereco;

public class FornEndDao extends GenericDao {

	public List<FornecedorXEndereco> listarForneEnderecos() {

		List<FornecedorXEndereco> lista = new ArrayList<FornecedorXEndereco>();
		try {
			conectar();

			ResultSet rs = comando
					.executeQuery("SELECT" + retornarCamposBD() + "FROM Fornecedor Endereco Where cepForne=cep;");
			while (rs.next()) {
				FornecedorXEndereco forneJoin = new FornecedorXEndereco();
				forneJoin.setCnpjForne(rs.getString("cnpjForne"));
				forneJoin.setNome(rs.getString("nome"));
				forneJoin.setEmail(rs.getString("email"));
				forneJoin.setCepForne(rs.getString("cepForne"));
				forneJoin.setUf(rs.getString("uf"));
				forneJoin.setCidade(rs.getString("cidade"));
				forneJoin.setBairro(rs.getString("bairro"));
				forneJoin.setLogradouro(rs.getString("logradouro"));
				lista.add(forneJoin);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		}
		return lista;

	}

	protected String retornarCamposBD() {
		return "cnpjForne, nome, email, cepForne, uf, cidade, bairro, logradouro";
	}

	private void conectar() throws ClassNotFoundException, SQLException {
		con = ConFactory.conexao(URL, NOME, SENHA);
		con.setAutoCommit(false);
		comando = con.createStatement();
		System.out.println("Conectado!");
	}

	public void commit() throws Exception {
		try {
			this.commitTransaction();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void cancelTransaction() throws Exception {
		if (con == null) {
			throw new Exception("There is no opened connection");
		}
		try {
			con.rollback();
		} finally {
			con.close();
			con = null;
		}
	}

	public void commitTransaction() throws Exception {
		if (con == null) {
			throw new Exception("There is no opened connection");
		}
		try {
			con.commit();
		} finally {
			con.close();
			con = null;
		}
	}
}
