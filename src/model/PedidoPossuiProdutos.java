package model;

import java.sql.Date;

public class PedidoPossuiProdutos {
	
	private Integer idPedido;
	private Integer codigo;
	private Integer quantidade;
	private String cnpjLoja;
	private String cnpjForne;
	private String cnpjTrans;
	private String nomeProd;
	private String descricao;
	private Date d_validade;
	private Float preco;
	private String forneCnpj;
	
	public PedidoPossuiProdutos(Integer idPedido, Integer codigo, Integer quantidade, String cnpjLoja, String cnpjForne,
			String cnpjTrans, String nomeProd, String descricao, Date d_validade, Float preco, String forneCnpj) {
		super();
		this.idPedido = idPedido;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.cnpjLoja = cnpjLoja;
		this.cnpjForne = cnpjForne;
		this.cnpjTrans = cnpjTrans;
		this.nomeProd = nomeProd;
		this.descricao = descricao;
		this.d_validade = d_validade;
		this.preco = preco;
		this.forneCnpj = forneCnpj;
	}
	
	public PedidoPossuiProdutos() {
		super();
		this.idPedido = null;
		this.codigo = null;
		this.quantidade = null;
		this.cnpjLoja = null;
		this.cnpjForne = null;
		this.cnpjTrans = null;
		this.nomeProd = null;
		this.descricao = null;
		this.d_validade = null;
		this.preco = null;
		this.forneCnpj = null;
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

	public String getNomeProd() {
		return nomeProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getD_validade() {
		return d_validade;
	}

	public void setD_validade(Date d_validade) {
		this.d_validade = d_validade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getForneCnpj() {
		return forneCnpj;
	}

	public void setForneCnpj(String forneCnpj) {
		this.forneCnpj = forneCnpj;
	}
	
	
}
