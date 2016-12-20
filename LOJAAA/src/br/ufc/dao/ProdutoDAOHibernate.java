package br.ufc.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Produto;

@Repository
@Transactional
public class ProdutoDAOHibernate implements IProdutoDAO{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void inserir(Produto produto) {
		manager.persist(produto);
	}

	@Override
	public List<Produto> listar() {
		String hql = "select p from PRODUTO as p";
		 List<Produto> produtos = manager.createQuery(hql, Produto.class).getResultList();
		 Collections.sort(produtos, new Comparator<Produto>(){
			    public int compare(Produto p1, Produto p2) {
			        return p1.getNome().compareToIgnoreCase(p2.getNome());
			    }
			});
				 
		 return produtos;
	}
	
	@Override
	public Produto recuperar(Long id) {
		String hql = "select p from PRODUTO as p where p.id = :var_id";
		Query query = manager.createQuery(hql, Produto.class);
		query.setParameter("var_id", id);
		List<Produto> produtos = query.getResultList();
		if (produtos!=null && produtos.size()>0){
			return produtos.get(0);
		}
		return null;

	}
	
	@Override
	public ArrayList<Produto> recuperarEscolhidos(List<Produto> produtosEscolhidos) {
		
		ArrayList<Produto> resultado = new ArrayList<Produto>();
		for (Produto pe:produtosEscolhidos){
			String hql = "select p from PRODUTO as p where p.id = :var_id";
			Query query = manager.createQuery(hql, Produto.class);
			query.setParameter("var_id", pe.getId());
			List<Produto> produtos = query.getResultList();
			if (produtos!=null && produtos.size()>0){
				resultado.add(produtos.get(0));
			}
		}
		return resultado;

	}


	@Override
	public void editar(Produto produto) {
		manager.merge(produto);
	}

	@Override
	public void excluir(Long id) {
		Produto p = this.recuperar(id);
		manager.remove(p);
	}
	
}
