package br.ufc.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Atendimento;
import br.ufc.model.Produto;

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
		
		List<Produto> newList = new ArrayList<Produto>(new LinkedHashSet<Produto>(atendimento.getProdutos()));
		atendimento.setProdutos(newList);
		
		for (Produto p:atendimento.getProdutos()){
			System.out.println("OIUHOIUNJLIK:::" +p.getNome());
			
		}
		
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

	@Override
	public Atendimento recuperar(Long id) {
		String hql = "select a from ATENDIMENTO as a where a.id = :var_id";
		Query query = manager.createQuery(hql, Atendimento.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Atendimento> atendimentos = query.getResultList();
		if (atendimentos!=null && atendimentos.size()>0){
			return atendimentos.get(0);
		}
		return null;

	}

	@Override
	public Integer obterLinhas(Long id) {
		String hql = "select a from ATENDIMENTO as a where a.id = :var_id";
		Query query = manager.createQuery(hql, Atendimento.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Atendimento> atendimentos = query.getResultList();
		if (atendimentos!=null && atendimentos.size()>0){
			System.out.println(atendimentos.get(0).getProdutos().size());
			return atendimentos.get(0).getProdutos().size();
		}
		return 0;
	}

	@Override
	public Long obterUltimoId() {
		String hql = "select a from ATENDIMENTO as a";
		Query query = manager.createQuery(hql, Atendimento.class);
		@SuppressWarnings("unchecked")
		List<Atendimento> atendimentos = query.getResultList();
		if (atendimentos!=null && atendimentos.size()>0){
			return (long) atendimentos.size()+1;
		}
		
		return 0L;
	}

}
