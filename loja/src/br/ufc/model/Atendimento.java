package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="ATENDIMENTO")
public class Atendimento {

	@Id
	@Column(name="ATE_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ateId;

	@Column(name="DATA")
	private String data;

	//MANY TO ONE   --- CLIENTE ---	
	@ManyToOne(optional=true)
	@JoinColumn(name="CLI_ID",referencedColumnName="CLI_ID")
	private Cliente cliente;
	//MANY TO ONE


	//MANY TO ONE   --- FUNCIONARIO ---	
	@ManyToOne(optional=true)
	@JoinColumn(name="FUN_ID",referencedColumnName="FUN_ID")
	private Funcionario funcionario;
	//MANY TO ONE

	
	//MANY TO MANY
	@ManyToMany(mappedBy="atendimentos", fetch=FetchType.EAGER)
	private List<Produto> produtos;
	//MANY TO MANY
	
	/*
	//MANY TO MANY	---	Vários produtos estão em vários atendimentos	
	@ManyToMany
	@JoinTable(name="PRODUTO_ATENDIMENTOS",
	joinColumns=@JoinColumn(name="ATE_ID",referencedColumnName="ATE_ID"),
	inverseJoinColumns=@JoinColumn(name="PROD_ID",referencedColumnName="PROD_ID"))
	private List<Produto> produtos;
	//MANY TO MANY
	*/

	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="MOTO")
	private String moto;

	@Column(name="PLACA")
	private String placa;
	
	@Column(name="VALOR_TOTAL")
	private Double valorTotal;
	
	@Column(name="VALOR_PAGO")
	private Double valorPago;
	
	@Column(name="DEBITO")
	private Double debito;
	

	public Long getAteId() {
		return ateId;
	}

	public void setAteId(Long ateId) {
		this.ateId = ateId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getMoto() {
		return moto;
	}

	public void setMoto(String moto) {
		this.moto = moto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getDebito() {
		return debito;
	}

	public void setDebito(Double debito) {
		this.debito = debito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
