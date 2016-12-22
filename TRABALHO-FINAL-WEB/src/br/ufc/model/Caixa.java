package br.ufc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="CAIXA")
public class Caixa {
	
	@Id
	@Column(name="CAIXA_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="DATA")
	private String data;
	
	
	


	@Column(name="TOTAL_ENTRADA")
	private Double totalEntrada;
	
	@Column(name="TOTAL_SAIDA")
	private Double totalSaida;

	@Column(name="SALDO")
	private Double saldo;
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
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
	
	
	 
}
