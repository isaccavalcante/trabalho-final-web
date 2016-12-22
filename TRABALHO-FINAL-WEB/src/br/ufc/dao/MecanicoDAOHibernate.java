package br.ufc.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Mecanico;

@Repository
@Transactional
public class MecanicoDAOHibernate implements IMecanicoDAO{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void inserir(Mecanico mecanico) {
		manager.persist(mecanico);
	}

	@Override
	public List<Mecanico> listar() {
		String hql = "select m from MECANICO as m";
		List<Mecanico> mecanicos =  manager.createQuery(hql, Mecanico.class).getResultList();
		Collections.sort(mecanicos, new Comparator<Mecanico>(){
			public int compare(Mecanico m1, Mecanico m2) {
				return m1.getNome().compareToIgnoreCase(m2.getNome());
			}
		});	
		return mecanicos;
	}
	
	@Override
	public Mecanico recuperar(Long id) {
		
		String hql = "select m from MECANICO as m where m.id = :var_id";
		Query query = manager.createQuery(hql, Mecanico.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Mecanico> mecanicos = query.getResultList();
		if (mecanicos!=null && mecanicos.size()>0){
			return mecanicos.get(0);
		}
		return null;

	}

	@Override
	public void excluir(Long id) {
		Mecanico m = this.recuperar(id);
		manager.remove(m);
	}

	@Override
	public void editar(Mecanico m) {
		manager.merge(m);
	}

}
