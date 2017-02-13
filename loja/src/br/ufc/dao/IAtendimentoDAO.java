package br.ufc.dao;

import java.util.List;

import br.ufc.model.Atendimento;

public interface IAtendimentoDAO {

	public void inserir(Atendimento atendimento);

	public void editar(Atendimento atendimento);

	public List<Atendimento> ordenarAtendimentos(List<Atendimento> atendimentos);

	public Atendimento recuperar(Long id);

	public Integer obterLinhas(Long id);

	public Long obterUltimoId();

}
