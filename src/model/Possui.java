package model;

public class Possui {
	
	private Integer idPedido;
	private Integer codigo;
	private Integer quantidade;
	
	public Possui() {
		this.idPedido = null;
		this.codigo = null;
		this.quantidade = null;
	}
	
	public Possui(Integer idPedido, Integer codigo, Integer quantidade) {
		super();
		this.idPedido = idPedido;
		this.codigo = codigo;
		this.quantidade = quantidade;
	}

	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
