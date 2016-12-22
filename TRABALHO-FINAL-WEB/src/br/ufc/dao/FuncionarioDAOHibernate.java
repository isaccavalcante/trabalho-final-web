package br.ufc.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Funcionario;
import br.ufc.util.LoginUtil;

@Repository
@Transactional//(propagation=Propagation.REQUIRES_NEW)
public class FuncionarioDAOHibernate implements IFuncionarioDAO {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void inserir(Funcionario funcionario) {
		LoginUtil l = new LoginUtil();
		String hashSenha = l.gerarHash(funcionario.getSenha());
		funcionario.setSenha(hashSenha);
		manager.persist(funcionario);
	}

	@Override
	public List<Funcionario> listar() {
		String hql = "select f from FUNCIONARIO as f";
		List<Funcionario> funcionarios =  manager.createQuery(hql, Funcionario.class).getResultList();
		Collections.sort(funcionarios, new Comparator<Funcionario>(){
			public int compare(Funcionario f1, Funcionario f2) {
				return f1.getNome().compareToIgnoreCase(f2.getNome());
			}
		});	 
		return funcionarios;
	}

	@Override
	public Funcionario recuperar(Long funId) {
		String hql = "select f from FUNCIONARIO as f";
		List<Funcionario> funcionarios =  manager.createQuery(hql, Funcionario.class).getResultList();
		System.out.println("total: " + funcionarios.size());
		for (Funcionario f: funcionarios){
			System.out.println("comparando "+funId +" com "+ f.getFunId());
			if (f.getFunId() == funId){
				
				return f;
			}
		}
		return null;
	
}

@Override
public void excluir(Long id) {
	Funcionario f = this.recuperar(id);
	manager.remove(f);
}

@Override
public void editar(Funcionario funcionario) {
	LoginUtil l = new LoginUtil();
	String novaSenha = l.gerarHash(funcionario.getSenha());
	funcionario.setSenha(novaSenha);
	manager.merge(funcionario);
}

@Override
public Funcionario recuperar(String login) {
	System.out.println("login:::::"+login);
	String hql = "select f from FUNCIONARIO as f where f.login = :var_login";
	Query query = manager.createQuery(hql, Funcionario.class);
	query.setParameter("var_login", login);
	@SuppressWarnings("unchecked")
	List<Funcionario> funcionarios = query.getResultList();
	if (funcionarios!=null && funcionarios.size()>0){
		return funcionarios.get(0);
	}
	return null;
}

}
