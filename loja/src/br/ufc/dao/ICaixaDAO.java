package br.ufc.dao;

import java.util.List;

import br.ufc.model.Caixa;

public interface ICaixaDAO {
	
	public void inserir(Caixa caixa);
	public boolean existe(String data);
	public void atualizar(Caixa caixa);
	public List<Caixa> listar();
	public Double obterUltimoSaldo();
	public Caixa recuperar(String data);
	public Caixa recuperar(Long id);
	public Double obterUltimoSaldo2(Long id);
	public int obterLinhas(Long id);
	public void obterCaixaDeHoje(String data);
}