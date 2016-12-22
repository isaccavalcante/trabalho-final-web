package br.ufc.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IAtendimentoDAO;
import br.ufc.dao.IClienteDAO;
import br.ufc.dao.IFuncionarioDAO;
import br.ufc.dao.IProdutoDAO;
import br.ufc.model.Atendimento;
import br.ufc.model.Cliente;
import br.ufc.model.Funcionario;
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
	private String cadastrarAtendimento(Atendimento atendimento,Cliente cliente, Funcionario funcionario){

		cliente = clienteDAO.recuperar(cliente.getCliId());

		cliente.setDivida(cliente.getDivida()+atendimento.getDebito());
		clienteDAO.editar(cliente);

		funcionario = funcionarioDAO.recuperar(funcionario.getFunId());

		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);
		atendimentoDAO.inserir(atendimento);

		return "atendimentos/cadastrar_atendimento_ok";
	}

	@RequestMapping("/listarAtendimentosCliente")
	private String listarAtendimentosCliente(Long id, Model model){
		Cliente cliente = clienteDAO.recuperar(id);
		Collection<Atendimento> atendimentos = cliente.getAtendimentos();
		
		List<Atendimento> atendimentosOrdenados = atendimentoDAO.ordenarAtendimentos((List<Atendimento>) atendimentos);
		Collections.reverse(atendimentosOrdenados);
		

		//Double debitoTotal = clienteDAO.obterDebitoTotalCliente(id);

		model.addAttribute("debitoTotal", cliente.getDivida());

		model.addAttribute("atendimentos", atendimentosOrdenados);
		model.addAttribute("cliente", cliente);
		return "atendimentos/listar_atendimentos";
	}

	@RequestMapping("/realizarPagamento")
	private String realizarPagamento(Long id, Double valor, String descricao){
		Cliente cliente = clienteDAO.recuperar(id);
		cliente.setDivida(cliente.getDivida()+valor);
		clienteDAO.editar(cliente);
		Atendimento a =  new Atendimento();
		a.setData(tempoUtil.obterData());
		a.setCliente(cliente);
		a.setDescricao(descricao);
		a.setValorPago(valor);
		a.setDebito(0.0);
		atendimentoDAO.inserir(a);
		return "redirect:listarAtendimentosCliente?id="+id;
	}



}
