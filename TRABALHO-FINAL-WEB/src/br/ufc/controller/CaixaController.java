package br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.ICaixaDAO;
import br.ufc.model.Caixa;
import br.ufc.util.TempoUtil;

@Controller
@Transactional
public class CaixaController {
	
	@Autowired
	private ICaixaDAO caixaDAOHibernate;
	
	@Autowired
	private TempoUtil tempoUtil;
	
	@RequestMapping("/cadastrarCaixaFormulario")
	public String cadastrarRelatorioFormulario(Long linhas, Model model){
		String data = tempoUtil.obterData();
		model.addAttribute("data", data);
		return "caixa/cadastrar_caixa_formulario";
	}
	
	@RequestMapping("/cadastrarCaixa")
	public String cadastrarCaixa(Caixa caixa){
		if (caixaDAOHibernate.existe(caixa.getData())){
			caixaDAOHibernate.atualizar(caixa);
			return "caixa/salvar_caixa_ok";
		}
		caixaDAOHibernate.inserir(caixa);
		return "caixa/salvar_caixa_ok";
	}
	
	@RequestMapping("/listarCaixas")
	public String listarCaixas(Model model){
		List<Caixa> caixas = caixaDAOHibernate.listar();
		model.addAttribute("caixas", caixas);
		return "listar_caixas";
	}
	

}
