package br.ufc.dao;

import java.util.List;

import br.ufc.model.Produto;

public interface IProdutoDAO {
	
	public void inserir(Produto produto);
	public List<Produto> listar();
	public void editar(Produto produto);
	public void excluir(Long id);
	public Produto recuperar(Long id);
	List<Produto> recuperarEscolhidos(List<Produto> produtosEscolhidos);

}
