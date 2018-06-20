package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import model.TranspXEndereco;

public class TransEnderecoDao extends GenericDao {

	public List<TranspXEndereco> listarTransEnderecos() {

		List<TranspXEndereco> lista = new ArrayList<TranspXEndereco>();
		try {
			conectar();

			ResultSet rs = comando.executeQuery("SELECT "+ getCamposBD() + "FROM Trasportadora Endereco" + "Where cepTrans=cep;");
			while (rs.next()) {
				TranspXEndereco transJoin = new TranspXEndereco();
				transJoin.setCnpjTrans(rs.getString("cnpjTrans"));
				transJoin.setNome(rs.getString("nome"));
				transJoin.setEmail(rs.getString("email"));
				transJoin.setCepTrans(rs.getString("cepTrans"));
				transJoin.setUf(rs.getString("uf"));
				transJoin.setCidade(rs.getString("cidade"));
				transJoin.setBairro(rs.getString("bairro"));
				transJoin.setLogradouro(rs.getString("logradouro"));
				lista.add(transJoin);
			}

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	private String getCamposBD() {
		return "cnpjTrans, nome, email, cepTrans, uf, cidade, bairro, logradouro ";
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
