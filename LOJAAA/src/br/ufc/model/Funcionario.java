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

@Entity(name="FUNCIONARIO")
public class Funcionario {
	
	@Id
	@Column(name="FUN_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long funId;
	
	@Column(name="NOME")
	private String nome;

	@Column(name="SOBRENOME")
	private String sobrenome;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="CARGO")
	private String cargo;
	
	@Column(name="SALARIO")
	private Double salario;
	
	
	// ONE TO MANY --- Um funcionario está em vários atendimentos
	@OneToMany(cascade=CascadeType.ALL, mappedBy="funcionario", targetEntity=Atendimento.class,fetch=FetchType.EAGER)
	private Collection<Atendimento> atendimentos;
	// ONE TO MANY --- Um funcionario está em vários atendimentos
	

	public Long getFunId() {
		return funId;
	}

	public void setFunId(Long funId) {
		this.funId = funId;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	
	

}
