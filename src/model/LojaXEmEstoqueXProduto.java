package model;

import java.sql.Date;

public class LojaXEmEstoqueXProduto {
	
	private String cnpj;
	private String nome;
	private String email;
	private String cepLoja;
	private Integer quantidade;
	private Integer codigo;
	private String nomeProd;
	private String descricao;
	private Date d_validade;
	private Float preco;
	private String forneCnpj;
	
	public LojaXEmEstoqueXProduto(String cnpj, String nome, String email, String cepLoja, Integer quantidade,
			Integer codigo, String nomeProd, String descricao, Date d_validade, Float preco, String forneCnpj) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.cepLoja = cepLoja;
		this.quantidade = quantidade;
		this.codigo = codigo;
		this.nomeProd = nomeProd;
		this.descricao = descricao;
		this.d_validade = d_validade;
		this.preco = preco;
		this.forneCnpj = forneCnpj;
	}

	public LojaXEmEstoqueXProduto() {
		this.cnpj = null;
		this.nome = null;
		this.email = null;
		this.cepLoja = null;
		this.quantidade = null;
		this.codigo = null;
		this.nomeProd = null;
		this.descricao = null;
		this.d_validade = null;
		this.preco = null;
		this.forneCnpj = null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCepLoja() {
		return cepLoja;
	}
	public void setCepLoja(String cepLoja) {
		this.cepLoja = cepLoja;
	}
	
	
	
}
