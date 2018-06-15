package model;

public class Categoria {
	 
	private Integer codigoProd;
	private String categoria;
	
	public Categoria() {
		this.codigoProd = null;
		this.categoria = null;
	}
	
	public Categoria(Integer codigoProd, String categoria) {
		super();
		this.codigoProd = codigoProd;
		this.categoria = categoria;
	}


	public Integer getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(Integer codigoProd) {
		this.codigoProd = codigoProd;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
