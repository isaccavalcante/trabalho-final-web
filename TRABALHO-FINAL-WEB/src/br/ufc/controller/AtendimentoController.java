package br.ufc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IAtendimentoDAO;
import br.ufc.dao.IClienteDAO;
import br.ufc.dao.IFuncionarioDAO;
import br.ufc.dao.IMecanicoDAO;
import br.ufc.dao.IProdutoDAO;
import br.ufc.model.Atendimento;
import br.ufc.model.Cliente;
import br.ufc.model.Funcionario;
import br.ufc.model.Mecanico;
import br.ufc.model.Produto;
import br.ufc.util.TempoUtil;

@Transactional
@Controller
public class AtendimentoController {
	
	@Autowired
	@Qualifier(value="clienteDAOHibernate")
	private IClienteDAO clienteDAO;
	
	/*@Qualifier(value="mecanicoDAOHibernate")*/
	
	@Autowired
	private IFuncionarioDAO funcionarioDAO;

	
	@Autowired
	@Qualifier(value="produtoDAOHibernate")
	private IProdutoDAO produtoDAO;
	
	@Autowired
	@Qualifier(value="atendimentoDAOHibernate")
	private IAtendimentoDAO atendimentoDAO;
	
	@Autowired
	@Qualifier(value="tempoUtil")
	private TempoUtil tempoUtil;
	
	@RequestMapping("/cadastrarAtendimentoFormulario")
	private String cadastrarAtendimentoFormulario(Long linhas, Model model){
		List<Cliente> clientes = clienteDAO.listar();
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		List<Produto> produtos = produtoDAO.listar();
		model.addAttribute("clientes", clientes);
		model.addAttribute("funcionarios",funcionarios);
		model.addAttribute("produtos", produtos);
		model.addAttribute("data", tempoUtil.obterData());
		return "atendimentos/cadastrar_atendimento_formulario";
	}
	
	@RequestMapping("/cadastrarAtendimento")
	private String cadastrarAtendimento(Atendimento atendimento, @ModelAttribute("cliente")Cliente cliente, Funcionario funcionario,
			@ModelAttribute("produtos") ArrayList<Produto> produtos){

		for (Produto p:produtos){
			System.out.println(p.getNome());
		}
		
		cliente = clienteDAO.recuperar(cliente.getCliId());
		
		cliente.setDivida(cliente.getDivida()+atendimento.getDebito());
		clienteDAO.editar(cliente);
		
		funcionario = funcionarioDAO.recuperar(funcionario.getFunId());
		produtos = (ArrayList<Produto>) produtoDAO.recuperarEscolhidos(produtos);
		
		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);
		atendimento.setProdutos(produtos);
		atendimentoDAO.inserir(atendimento);
			
		return "atendimentos/cadastrar_atendimento_ok";
	}
	
	@RequestMapping("/listarAtendimentosCliente")
	private String listarAtendimentosCliente(Long id, Model model){
		Cliente cliente = clienteDAO.recuperar(id);
		Collection<Atendimento> atendimentos = cliente.getAtendimentos();
		
		Double debitoTotal = clienteDAO.obterDebitoTotal(id);
		
		model.addAttribute("debitoTotal", debitoTotal);
		
		model.addAttribute("atendimentos", atendimentos);
		model.addAttribute("idCli", id);
		return "atendimentos/listar_atendimentos";
	}
	
	@RequestMapping("/realizarPagamento")
	private String realizarPagamento(Long id, Double valor){
		System.out.println("-->" + id);
		Cliente cliente = clienteDAO.recuperar(id);
		Atendimento a =  new Atendimento();
		a.setData(tempoUtil.obterData());
		a.setCliente(cliente);
		a.setValorPago(valor*-1);
		a.setDebito(valor);
		atendimentoDAO.inserir(a);
		return "redirect:listarAtendimentosCliente?id="+id;
	}
	
	

}
