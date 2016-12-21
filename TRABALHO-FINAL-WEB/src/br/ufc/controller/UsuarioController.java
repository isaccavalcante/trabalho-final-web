package br.ufc.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.IAmizadeDAO;
import br.ufc.dao.IUsuarioDAO;
import br.ufc.form.AmizadeCheckboxForm;
import br.ufc.model.Amizade;
import br.ufc.model.Usuario;
import br.ufc.util.AulaFileUtil;


@Controller
@Transactional
public class UsuarioController {
	
	//dizer para o spring criar o usariodao
	@Autowired
	@Qualifier(value="usuarioDAOHibernate")
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	@Qualifier(value="amizadeDAOHibernate")
	private IAmizadeDAO amizadeDAO;
	
	@Autowired
	private ServletContext context;
	
	//INSERIR redirecionar para o formulario
	@RequestMapping("/inserirUsuarioFormulario")
	public String inserirUsuarioFormulario(){
		return "usuarios/inserir_usuario_formulario";
	}
	
	//botão de ok do formulario 
	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(Usuario usuario, 
								@RequestParam(value="image", required=false)MultipartFile image){
		
		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="resources/images/"+usuario.getLogin()+".png";
			AulaFileUtil.saveFile(path, image);
		}
		usuarioDAO.inserir(usuario);
		return "usuarios/inserir_ok";
	}
	
	//LISTAR
	@RequestMapping("/listarUsuario")
	public String listarUsuario(Model model){
		List<Usuario> usuarios = usuarioDAO.listar();
		model.addAttribute("usuarios", usuarios);
		return "usuarios/listar_usuario";
	}
	
	//
	
	//APAGAR
	@RequestMapping("/apagarUsuario")
	public String apagarUsuario(Long id){
		usuarioDAO.apagar(id);
		return "redirect:listarUsuario";
		
		
	}
	
	//ALTERAR
	@RequestMapping("/alterarUsuarioFormulario")
	public String alterarFormulario(Long id, Model model){
		Usuario u = usuarioDAO.recuperar(id);
		model.addAttribute("usuario", u);
		
		return "usuarios/alterar_usuario_formulario";
	}
	
	@RequestMapping("/alterarUsuario")
	public String alterarUsuario(Usuario u){
		usuarioDAO.alterar(u);
		return "redirect:listarUsuario";
		
	}
	
	@RequestMapping("/inserirAmizadeFormulario")
	public String inserirAmizadeFormulario(HttpSession session ,Model model){
		Usuario usuario = (Usuario)session.getAttribute("usuario_logado");
		List<Usuario> potenciaisAmigos = usuarioDAO.listar();
		potenciaisAmigos.remove(usuario);//removendo eu mesmo da lista
		
		AmizadeCheckboxForm acf = new AmizadeCheckboxForm();
		List<Amizade> minhasAmizades = this.amizadeDAO.listarAmizadeId(usuario.getId());
		if (minhasAmizades != null && minhasAmizades.size()>0){
			//Long[] vetorIds = new Long[minhasAmizades.size()];
			//int i = 0;
			for(Amizade amizade:minhasAmizades){
				Long amigoId = amizade.getUsuarioAlvo().getId();
				Usuario amigoTemp = new Usuario();
				amigoTemp.setId(amigoId);
				potenciaisAmigos.remove(amigoTemp);
				//vetorIds[i] = amigoId;
				//i++;
			}
			//acf.setAmigos(vetorIds);
		}
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("potenciais_amigos", potenciaisAmigos);
		model.addAttribute("amizade", acf);
		return "usuarios/inserir_amizade_formulario";
		
	}
	
	@RequestMapping("/inserirAmizade")
	public String inserirAmizade(HttpSession session, AmizadeCheckboxForm amizades){
		
		Usuario amigoFonte = (Usuario)session.getAttribute("usuario_logado");
		
		for(Long id:amizades.getAmigos()){
			Usuario amigoAlvo = usuarioDAO.recuperar(id);
			Amizade amizade = new Amizade();
			amizade.setUsuarioFonte(amigoFonte);
			amizade.setUsuarioAlvo(amigoAlvo);
			amizadeDAO.inserir(amizade);
		}
		return "redirect:listarUsuario";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
