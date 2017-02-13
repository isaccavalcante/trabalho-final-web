package br.ufc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IFuncionarioDAO;
import br.ufc.model.Funcionario;
import br.ufc.util.LoginUtil;
import br.ufc.util.TempoUtil;

@Controller
public class LoginController {

	@Autowired
	@Qualifier(value="funcionarioDAOHibernate")
	private IFuncionarioDAO funcionarioDAO;
	
	@Autowired
	private TempoUtil tempoUtil;
	
	@RequestMapping("/menu")
	private String menu(Model model){
		
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		
		String s=tempoUtil.saudacao();
		model.addAttribute("s", s);
		model.addAttribute("funcionarios", funcionarios);
		return "menu";
	}
	
	@RequestMapping("/teste")
	private String teste(Model model){
		String s=tempoUtil.saudacao();
		model.addAttribute("s", s);
		return "teste";
	}
	
	@RequestMapping("/loginFormulario")
	public String loginFormulario(){
		return "login_formulario";
	}
	
	@RequestMapping("/login")
	public String login(Funcionario funcionario, HttpSession session, Model model){
		Funcionario f = funcionarioDAO.recuperar(funcionario.getLogin());
		
		if (f!=null){
			
			LoginUtil l = new LoginUtil();
			String hashSenha = l.gerarHash(funcionario.getSenha());
			
			if(f.getSenha().equals(hashSenha)){
				session.setAttribute("funcionario_logado", f);
				return "redirect:menu";
			}
		}
		model.addAttribute("info", "Login ou senha incorretos.");
		return "login_formulario";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginFormulario";
	}
}
