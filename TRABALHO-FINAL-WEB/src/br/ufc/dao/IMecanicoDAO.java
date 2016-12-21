package br.ufc.dao;

import java.util.List;

import br.ufc.model.Mecanico;

public interface IMecanicoDAO {
	
	public void inserir(Mecanico mecanico);
	public List<Mecanico> listar();
	public Mecanico recuperar(Long id);
	public void editar(Mecanico mecanico);
	public void excluir(Long id);
	
}
