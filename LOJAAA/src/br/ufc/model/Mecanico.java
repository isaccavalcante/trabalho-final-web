package br.ufc.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="MECANICO")
public class Mecanico {

	@Id
	@Column(name="MEC_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long mecId;

	@Column(name="NOME")
	private String nome;

	@Column(name="SOBRENOME")
	private String sobrenome;

	@Column(name="CPF")
	private String cpf;

	@Column(name="SALARIO")
	private Double salario;


	// ONE TO MANY --- Um mecanico está em vários atendimentos
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="mecanico", targetEntity=Atendimento.class,fetch=FetchType.LAZY)
	//private Collection<Atendimento> atendimentos;

	public Long getMecId() {
		return mecId;
	}

	public void setMecId(Long mecId) {
		this.mecId = mecId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	/*/
	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	/*/


}
