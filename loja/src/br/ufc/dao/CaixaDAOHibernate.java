package br.ufc.dao;

import java.util.Collections;
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
	public Caixa recuperar(Long id) {
		String hql = "select c from CAIXA as c where c.id = :var_id";
		Query query = manager.createQuery(hql, Caixa.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Caixa> caixas = query.getResultList();
		if (caixas!=null && caixas.size()>0){
			return caixas.get(0);
		}
		return null;
	}

	@Override
	public Caixa recuperar(String data) {
		System.out.println("\n\n -- "+ data);
		String hql = "select c from CAIXA as c where c.data = :var_data";
		Query query = manager.createQuery(hql, Caixa.class);
		query.setParameter("var_data", data);
		@SuppressWarnings("unchecked")
		List<Caixa> caixas = query.getResultList();
		if (caixas!=null && caixas.size()>0){
			System.out.println("\n\n -- "+ "--- not null");
			return caixas.get(0);
		}
		return null;
	}


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


	@Override
	public Double obterUltimoSaldo2(Long id){
		String hql = "select c from CAIXA as c where c.id = :var_id";
		Query query = manager.createQuery(hql, Caixa.class);
		System.out.println("ID:::"+id);
		try {
			query.setParameter("var_id", id-1);
			@SuppressWarnings("unchecked")
			List<Caixa> caixas = query.getResultList();
			if (caixas!=null && caixas.size()>0){
				return caixas.get(0).getSaldoDiario();
			}
		}
		catch (NullPointerException e) {
			return 0.0;
		}
		return 0.0;

	}

	@Override
	public Double obterUltimoSaldo(){
		String hql = "select c from CAIXA as c";
		List<Caixa> caixas=  manager.createQuery(hql, Caixa.class).getResultList();
		Collections.reverse(caixas);
		if (caixas!=null && caixas.size()>0){
			if (caixas.size() == 1){
				System.out.println("Encontrou apenas um caixa... retornando saldo diario");
				System.out.println("que é de "+caixas.get(0).getSaldoDiario());

				return caixas.get(0).getSaldoDiario();

			}else{
				System.out.println("Encontrou mais de um caixa... retornando saldo total");
				System.out.println("que é de "+caixas.get(0).getSaldoTotal());
				return caixas.get(0).getSaldoTotal();
			}
		}


		return 0.0;			
	}

	@Override
	public int obterLinhas(Long id) {
		String hql = "select c from CAIXA as c where c.id = :var_id";
		Query query = manager.createQuery(hql, Caixa.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Caixa> caixas = query.getResultList();
		if (caixas!=null && caixas.size()>0){
			Caixa caixa = caixas.get(0);
			
			int tamDescEntr = caixa.getDescricaoEntrada().size();
			int tamDescSai = caixa.getDescricaoSaida().size();
			int tamEntr = caixa.getEntradas().size();
			int tamSai = caixa.getSaidas().size();
			
			
			return Math.max( tamDescEntr, Math.max( tamDescSai, Math.max( tamEntr, tamSai ) ) );
			
		}
		return 0;
	}

	@Override
	public void obterCaixaDeHoje(String data) {
		
		
		
	}




}




