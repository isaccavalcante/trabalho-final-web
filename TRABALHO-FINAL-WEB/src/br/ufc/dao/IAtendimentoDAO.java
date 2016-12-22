package br.ufc.dao;

import java.util.Collection;
import java.util.List;

import br.ufc.model.Atendimento;

public interface IAtendimentoDAO {

	public void inserir(Atendimento atendimento);

	public void editar(Atendimento atendimento);

	public List<Atendimento> ordenarAtendimentos(List<Atendimento> atendimentos);

	
	
}
