package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IFuncionarioDAO;
import br.ufc.model.Funcionario;
import br.ufc.util.LoginUtil;

@Transactional
@Controller
public class LoginController {

	@Autowired
	@Qualifier(value="funcionarioDAOHibernate")
	private IFuncionarioDAO funcionarioDAO;
	
	
	@RequestMapping("/menu")
	private String menu(){
		return "menu";
	}
	
	
	@RequestMapping("/loginFormulario")
	public String loginFormulario(){
		return "login_formulario";
	}
	
	@RequestMapping("/login")
	public String login(Funcionario funcionario, HttpSession session){
		Funcionario f = funcionarioDAO.recuperar(funcionario.getLogin());
		
		if (f!=null){
			
			LoginUtil l = new LoginUtil();
			String hashSenha = l.gerarHash(funcionario.getSenha());
			
			if(f.getSenha().equals(hashSenha)){
				session.setAttribute("funcionario_logado", f);
				return "menu";
			}
		}
		return "redirect:loginFormulario";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginFormulario";
	}
}
