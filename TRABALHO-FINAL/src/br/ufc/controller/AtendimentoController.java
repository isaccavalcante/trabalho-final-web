package br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IClienteDAO;
import br.ufc.dao.IFuncionarioDAO;
import br.ufc.dao.IProdutoDAO;
import br.ufc.model.Cliente;
import br.ufc.model.Funcionario;
import br.ufc.model.Produto;

@Controller
@Transactional
public class AtendimentoController {
	
	@Autowired
	@Qualifier(value="clienteDAOHibernate")
	private IClienteDAO clienteDAO;
	
	@Autowired
	@Qualifier(value="funcionarioDAOHibernate")
	private IFuncionarioDAO funcionarioDAO;
	
	@Autowired
	@Qualifier(value="produtoDAOHibernate")
	private IProdutoDAO produtoDAO;
	
	
	@RequestMapping("/cadastrarAtendimentoFormulario")
	private String cadastrarAtendimentoFormulario(Model model){
		List<Cliente> clientes = clienteDAO.listar();
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		List<Produto> produtos = produtoDAO.listar();
		model.addAttribute("clientes", clientes);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("produtos", produtos);
		return "atendimentos/cadastrar_atendimento_formulario";
	}

}
