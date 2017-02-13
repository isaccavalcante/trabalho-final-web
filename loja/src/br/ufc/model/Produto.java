package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="PRODUTO")
public class Produto {
	
	@Id
	@Column(name="PROD_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="REFERENCIA")
	private String referencia;
	
	@Column(name="QUANTIDADE")
	private Long quantidade;
		
	@Column(name="PRECO_COMPRA")
	private Double precoCompra;
	
	@Column(name="PRECO_VENDA")
	private Double precoVenda;
	
	@Column(name="LOCAL")
	private String local;

	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="FORNECEDOR")
	private String fornecedor;
	
	
	/*
	//MANY TO MANY
	@ManyToMany(mappedBy="produtos")
	private List<Atendimento> atendimentos;
	//MANY TO MANY
	*/
	
	//MANY TO MANY	---	Vários produtos estão em vários atendimentos	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="ATENDIMENTO_PRODUTOS",
	joinColumns={@JoinColumn(name="PROD_ID")},
	inverseJoinColumns=@JoinColumn(name="ATE_ID",referencedColumnName="ATE_ID"))
	private List<Atendimento> atendimentos;
	//MANY TO MANY
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
