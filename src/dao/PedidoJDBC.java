package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConFactory;
import interfaceDAO.IPedido;
import model.LojaXEmEstoqueXProduto;
import model.Pedido;

public class PedidoJDBC extends GenericDao implements IPedido {

	public PedidoJDBC() {
		super();
	}

	@Override
	public void atualizar(Pedido pedido) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE Pedido SET ");
		buffer.append(returnFieldValuesBD(pedido));
		buffer.append(" WHERE idPedido=");
		buffer.append(pedido.getIdPedido());
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
	public void inserir(Pedido pedido) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO Pedido (");
		buffer.append(this.retornarCamposBD());
		buffer.append(") VALUES (");
		buffer.append(this.retornarValoresBD(pedido));
		buffer.append(")");
		String sql = buffer.toString();

		try {
			conectar();
			comando.execute(sql);
			System.out.println("estou aqui");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Pedido> listarPedidos() {

		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			conectar();

			ResultSet rs = comando.executeQuery("SELECT * FROM Pedido");
			while (rs.next()) {
				Pedido produtoJoinLoja = new Pedido();
				produtoJoinLoja.setIdPedido(Integer.parseInt(rs.getString("idPedido")));
				produtoJoinLoja.setCnpjTrans(rs.getString("cnpjTrans"));
				produtoJoinLoja.setCnpjLoja(rs.getString("cnpjLoja"));
				produtoJoinLoja.setCnpjForne(rs.getString("cnpjForne"));

				lista.add(produtoJoinLoja);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		}
		return lista;
	}

	@Override
	public Pedido buscar(Integer idPedido) {

		Pedido pedido = new Pedido();

		try {
			conectar();
			String sql = "SELECT * FROM Pedido WHERE idPedido=" + idPedido;
			ResultSet rs = comando.executeQuery(sql);
			if (rs.next()) {
				pedido.setIdPedido(rs.getInt("idPedido"));
				pedido.setCnpjLoja(rs.getString("cnpjLoja"));
				pedido.setCnpjForne(rs.getString("cnpjForne"));
				pedido.setCnpjTrans(rs.getString("cnpjTrans"));
				System.out.println(pedido.getIdPedido());
			}

		} catch (SQLException SQLe) {
			SQLe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public void remover(Pedido pedido) {

		String sql = "DELETE FROM Pedido WHERE idPedido=" + pedido.getIdPedido() + ";";
		System.out.println(pedido.getIdPedido());
		try {
			conectar();
			comando.execute(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected String retornarCamposBD() {
		return "cnpjLoja, cnpjForne, cnpjTrans";
	}

	protected String returnFieldValuesBD(Pedido pedido) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("idPedido=");
		buffer.append(pedido.getIdPedido());
		buffer.append(", cnpjLoja=");
		buffer.append(retornarValorStringBD(pedido.getCnpjLoja()));
		buffer.append(", cnpjForne=");
		buffer.append(retornarValorStringBD(pedido.getCnpjForne()));
		buffer.append(", cnpjTrans=");
		buffer.append(retornarValorStringBD(pedido.getCnpjTrans()));

		return buffer.toString();
	}

	protected String retornarValoresBD(Pedido pedido) {

		return retornarValorStringBD(pedido.getCnpjLoja()) + ", " + retornarValorStringBD(pedido.getCnpjForne()) + ", "
				+ retornarValorStringBD(pedido.getCnpjTrans());
	}

	private String retornarValorStringBD(String valor) {
		if (valor != null && !"".equals(valor)) {
			valor = "'" + valor + "'";
		} else {
			valor = "'" + "'";
		}
		return valor;
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
