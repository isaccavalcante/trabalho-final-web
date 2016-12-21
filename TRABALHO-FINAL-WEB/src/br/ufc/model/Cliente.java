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

@Entity(name="CLIENTE")
public class Cliente {
	
	@Id
	@Column(name="CLI_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cliId;
	
	@Column(name="RG")
	private Long rg;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="SOBRENOME")
	private String sobrenome;
	
	@Column(name="ENDERECO")
	private String endereco;
	
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="TELEFONE")
	private String telefone;
	
	@Column(name="DIVIDA")
	private Double divida;
	
	// ONE TO MANY --- Um cliente está em vários atendimentos
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cliente", targetEntity=Atendimento.class,fetch=FetchType.EAGER)
	private Collection<Atendimento> atendimentos;
	// ONE TO MANY --- Um cliente está em vários atendimentos

	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public Long getRg() {
		return rg;
	}

	public void setRg(Long rg) {
		this.rg = rg;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getDivida() {
		return divida;
	}

	public void setDivida(Double divida) {
		this.divida = divida;
	}

	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	

}
