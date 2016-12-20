package br.ufc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Atendimento;

@Repository
@Transactional
public class AtendimentoDAOHibernate implements IAtendimentoDAO{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void inserir(Atendimento atendimento) {
		manager.persist(atendimento);
	}

	@Override
	public void editar(Atendimento atendimento) {
		manager.merge(atendimento);
		
	}

	
}
