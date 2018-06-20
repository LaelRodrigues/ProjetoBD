package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.IUser;
import model.User;

public class UserDao extends GenericDao implements IUser {

	public UserDao() {
		super();
	}

	@Override
	public void atualizar(User user) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE User SET ");
		buffer.append(returnFieldValuesBD(user));
		buffer.append(" WHERE nome=");
		buffer.append(user.getNome());
		String sql = buffer.toString();

		try {
			conectar();
			comando.execute(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(User user) {

		try {
			conectar();

			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO User (");
			buffer.append(this.retornarCamposBD());
			buffer.append(") VALUES (");
			buffer.append(this.retornarValoresBD(user));
			buffer.append(")");
			String sql = buffer.toString();

			// comando = con.createStatement();
			// comando.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// System.out.println(" id = " + ps.getGeneratedKeys().getInt(1));

			/*
			 * try { int id =
			 * con.createStatement().getGeneratedKeys().getInt(1);
			 * System.out.println(" id xxx " + id); } catch (SQLException e1) {
			 * e1.printStackTrace(); }
			 */

			comando.execute(sql);
			// System.out.println("estou aqui");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User buscar(String nome) {
		// TODO Auto-generated method stub
		User user = new User();

		try {
			conectar();
			String sql = "SELECT * FROM User WHERE nome = '" + nome + "'";
			ResultSet rs = comando.executeQuery(sql);
			if (rs.next()) {
				// user.setCnpj(rs.getString("cnpjLoja"));
				user.setNome(rs.getString("nome"));
				user.setSenha(rs.getString("senha"));
				user.setCargo(rs.getString("cargo"));
				// user.setEmail(rs.getString("email"));
				// user.setCepLoja(rs.getString("cepLoja"));
				System.out.println(user.getNome());
			}

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void remover(User user) {
		String sql = "DELETE FROM User WHERE idUser=" + user.getId() + ";";
		System.out.println(user.getId());
		try {
			conectar();
			comando.execute(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String retornarCamposBD() {
		return "nome, senha, cargo";
	}

	protected String returnFieldValuesBD(User user) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(", nome=");
		buffer.append(retornarValorStringBD(user.getNome()));
		buffer.append(", senha=");
		buffer.append(retornarValorStringBD(user.getSenha()));
		buffer.append(", cargo=");
		buffer.append(retornarValorStringBD(user.getCargo()));

		return buffer.toString();
	}

	protected String retornarValoresBD(User user) {

		return retornarValorStringBD(user.getNome()) + ", " + retornarValorStringBD(user.getSenha()) + ", "
				+ retornarValorStringBD(user.getCargo());
	}

	protected String retornarValorStringBD(String valor) {
		if (valor != null && !"".equals(valor)) {
			valor = "'" + valor + "'";
		} else {
			valor = "'" + "'";
		}
		return valor;
	}

	protected String retornarValorStringBD(Double valor) {
		String vAux;
		if (valor != null) {
			vAux = "'" + valor + "'";
		} else {
			vAux = "'" + "'";
		}
		return vAux;
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
