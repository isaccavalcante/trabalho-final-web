package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name="DESCRICAO")
	private String descricao;
		
	@Column(name="PRECO")
	private Double preco;
	
	@Column(name="LOCAL")
	private String local;

	/*
	//MANY TO MANY
	@ManyToMany(mappedBy="produtos")
	private List<Atendimento> atendimentos;
	//MANY TO MANY
	*/
	
	//MANY TO MANY	---	Vários produtos estão em vários atendimentos	
	@ManyToMany
	@JoinTable(name="ATENDIMENTO_PRODUTOS",
	joinColumns=@JoinColumn(name="PROD_ID",referencedColumnName="PROD_ID"),
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
}
