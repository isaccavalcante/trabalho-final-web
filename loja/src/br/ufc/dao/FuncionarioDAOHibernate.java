package br.ufc.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.model.Funcionario;
import br.ufc.util.LoginUtil;

@Repository
@Transactional//(propagation=Propagation.REQUIRES_NEW)
public class FuncionarioDAOHibernate implements IFuncionarioDAO {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	@Qualifier(value="loginUtil")
	private LoginUtil loginUtil;

	@Override
	public void inserir(Funcionario funcionario) {
		String hashSenha = loginUtil.gerarHash(funcionario.getSenha());
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
		for (Funcionario f: funcionarios){
			System.out.println("---------------> "+ f.getFunId());
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
		Funcionario funcionarioAux = this.recuperar(funcionario.getFunId());
		if (funcionario.getSenha().equals("") || funcionario.getSenha().equals(null)){
			funcionario.setSenha(funcionarioAux.getSenha());
			manager.merge(funcionario);
		}else{
			String novaSenha = loginUtil.gerarHash(funcionario.getSenha());
			funcionario.setSenha(novaSenha);
			manager.merge(funcionario);
		}
	}

	@Override
	public Funcionario recuperar(String login) {
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
