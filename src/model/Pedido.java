package model;

public class Pedido {
	
	private Integer idPedido;
	private String cnpjLoja;
	private String cnpjForne;
	private String cnpjTrans;
	
	public Pedido() {
		this.idPedido = null;
		this.cnpjLoja = null;
		this.cnpjForne = null;
		this.cnpjTrans = null;
	}
	
	public Pedido(String cnpjLoja, String cnpjForne, String cnpjTrans) {
		super();
		this.cnpjLoja = cnpjLoja;
		this.cnpjForne = cnpjForne;
		this.cnpjTrans = cnpjTrans;
	}

	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getCnpjLoja() {
		return cnpjLoja;
	}
	public void setCnpjLoja(String cnpjLoja) {
		this.cnpjLoja = cnpjLoja;
	}
	public String getCnpjForne() {
		return cnpjForne;
	}
	public void setCnpjForne(String cnpjForne) {
		this.cnpjForne = cnpjForne;
	}
	public String getCnpjTrans() {
		return cnpjTrans;
	}
	public void setCnpjTrans(String cnpjTrans) {
		this.cnpjTrans = cnpjTrans;
	}
}
