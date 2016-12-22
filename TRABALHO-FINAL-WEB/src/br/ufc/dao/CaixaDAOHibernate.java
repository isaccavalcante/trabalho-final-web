package br.ufc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Caixa;

@Repository
@Transactional
public class CaixaDAOHibernate implements ICaixaDAO {

	@PersistenceContext
	private EntityManager manager;



	@Override
	public boolean existe(String data) {
		String hql = "select c from CAIXA as c where c.data = :var_data";
		Query query = manager.createQuery(hql, Caixa.class);
		query.setParameter("var_data", data);
		@SuppressWarnings("unchecked")
		List<Caixa> caixas = query.getResultList();
		if (caixas!=null && caixas.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public void inserir(Caixa caixa) {
		manager.persist(caixa);
	}
	
	@Override
	public void atualizar(Caixa caixa) {
		manager.merge(caixa);
	}

	@Override
	public List<Caixa> listar() {
		String hql = "select c from CAIXA as c";
		return manager.createQuery(hql, Caixa.class).getResultList();
	}



}




