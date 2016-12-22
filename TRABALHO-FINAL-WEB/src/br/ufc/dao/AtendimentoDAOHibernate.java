package br.ufc.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<Atendimento> ordenarAtendimentos(List<Atendimento> atendimentos) {






		Collections.sort(atendimentos, new Comparator<Atendimento>(){
			public int compare(Atendimento a1, Atendimento a2) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date data1 = format.parse(a1.getData());
					Date data2 = format.parse(a2.getData());
					return data1.compareTo(data2);


				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});	 
		return atendimentos;
	}



}
