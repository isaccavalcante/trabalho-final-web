package br.ufc.dao;

import java.util.List;

import br.ufc.model.Funcionario;

public interface IFuncionarioDAO {
	
	public void inserir(Funcionario funcionario);
	public List<Funcionario> listar();
	public Funcionario recuperar(Long id);
	public void editar(Funcionario funcionario);
	public void excluir(Long id);
	public Funcionario recuperar(String login);

}
