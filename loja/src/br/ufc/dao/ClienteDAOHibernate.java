package br.ufc.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Atendimento;
import br.ufc.model.Cliente;

@Repository
@Transactional
public class ClienteDAOHibernate implements IClienteDAO {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Cliente cliente) {
		manager.persist(cliente);
	}

	@Override
	public List<Cliente> listar() {
		String hql = "select c from CLIENTE as c";
		List<Cliente> clientes = manager.createQuery(hql, Cliente.class).getResultList();
		Collections.sort(clientes, new Comparator<Cliente>(){
			public int compare(Cliente c1, Cliente c2) {
				return (c1.getNome()+c1.getSobrenome()).compareToIgnoreCase(c2.getNome()+c2.getSobrenome());
			}
		});
		return clientes;

	}

	@Override
	public Cliente recuperar(Long id) {
		String hql = "select c from CLIENTE as c where c.id = :var_id";
		Query query = manager.createQuery(hql, Cliente.class);
		query.setParameter("var_id", id);
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = query.getResultList();
		if (clientes!=null && clientes.size()>0){
			return clientes.get(0);
		}
		return null;

	}
	
	@Override
	public void excluir(Long id) {
		Cliente c = this.recuperar(id);
		manager.remove(c);
	}

	
	@Override
	public void editar(Cliente cliente) {
		manager.merge(cliente);
	}

	@Override
	public Double obterDebitoTotalCliente(Long id) {
		
		Cliente cliente = this.recuperar(id);
		Double total = cliente.getDivida();
		for (Atendimento a:cliente.getAtendimentos()){
			total += a.getDebito();
		}

		return total;
	}

	@Override
	public Double obterDebitoTotaldeTodos() {
		
		String hql = "select c from CLIENTE as c";
		List<Cliente> clientes = manager.createQuery(hql, Cliente.class).getResultList();
		Double total = 0.0;
		for (Cliente c:clientes){
			total += c.getDivida();			
		}
		
		return total;
	}







}
