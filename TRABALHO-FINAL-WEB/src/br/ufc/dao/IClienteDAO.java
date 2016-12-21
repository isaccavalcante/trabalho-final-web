package br.ufc.dao;

import java.util.List;

import br.ufc.model.Cliente;

public interface IClienteDAO {
	
	public void inserir(Cliente cliente);
	public List<Cliente> listar();
	public Cliente recuperar(Long id);
	public void editar(Cliente cliente);
	public void excluir(Long id);
	public Double obterDebitoTotal(Long id);
}
