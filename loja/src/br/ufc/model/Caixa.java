package br.ufc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CAIXA")
public class Caixa {
	
	@Id
	@Column(name="CAIXA_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="SALDO_TOTAL")
	private Double saldoTotal;
	
	@Column(name="DATA")
	private String data;
	
	@Column(name="TOTAL_ENTRADA")
	private Double totalEntrada;
	
	@Column(name="TOTAL_SAIDA")
	private Double totalSaida;

	@Column(name="SALDO_DIARIO")
	private Double saldoDiario;
	
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	@Column(name="DESCRICAO_SAIDA")
	private List<String> descricaoSaida;
	
	@ElementCollection(targetClass=Double.class, fetch=FetchType.EAGER)
	@Column(name="SAIDAS")
	private List<Double> saidas;
	
	@ElementCollection(targetClass=Double.class, fetch=FetchType.EAGER)
	@Column(name="ENTRADAS")
	private List<Double> entradas;
	
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	@Column(name="DESCRICAO_ENTRADA")
	private List<String> descricaoEntrada;
	
	public Double getSaldoDiario() {
		return saldoDiario;
	}

	public void setSaldoDiario(Double saldoDiario) {
		this.saldoDiario = saldoDiario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getTotalEntrada() {
		return totalEntrada;
	}

	public void setTotalEntrada(Double totalEntrada) {
		this.totalEntrada = totalEntrada;
	}

	public Double getTotalSaida() {
		return totalSaida;
	}

	public void setTotalSaida(Double totalSaida) {
		this.totalSaida = totalSaida;
	}

	public Double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public List<String> getDescricaoSaida() {
		return descricaoSaida;
	}

	public void setDescricaoSaida(List<String> descricaoSaida) {
		this.descricaoSaida = descricaoSaida;
	}

	public List<String> getDescricaoEntrada() {
		return descricaoEntrada;
	}

	public void setDescricaoEntrada(List<String> descricaoEntrada) {
		this.descricaoEntrada = descricaoEntrada;
	}

	public List<Double> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<Double> saidas) {
		this.saidas = saidas;
	}

	public List<Double> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Double> entradas) {
		this.entradas = entradas;
	}
	
	
	
	
	 
}
