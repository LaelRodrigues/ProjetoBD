package model;

import java.sql.Date;

public class Produto {
	
	private Integer codigo;
	private String nome;
	private String descricao;
	private Date d_validade;
	private Float preco;
	private String forneCnpj;
	
	public Produto() {
		this.codigo = null;
		this.nome = null;
		this.descricao = null;
		this.d_validade = null;
		this.preco = null;
		this.forneCnpj = null;
		
	}
	
	public Produto(Integer codigo, String nome, String descricao, Date d_validade, Float preco, String forneCnpj) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.d_validade = d_validade;
		this.preco = preco;
		this.forneCnpj = forneCnpj;
	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
