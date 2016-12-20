
package br.ufc.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.IMecanicoDAO;
import br.ufc.model.Mecanico;
import br.ufc.util.AulaFileUtil;


@Controller
public class MecanicoController {

	
	@Autowired
	private IMecanicoDAO mecanicoDAO;

	@Autowired
	private ServletContext context;

	@RequestMapping("/listarMecanicos")
	public String listarMecanicos(Model model){
		List<Mecanico> mecanicos = mecanicoDAO.listar();
		System.out.println("Controller ->");
		for (Mecanico m: mecanicos){
			System.out.println("\t "+m.getNome());
			
		}
		model.addAttribute("mecanicos", mecanicos);
		return "mecanicos/listar_mecanicos";
	}

	@RequestMapping("/excluirMecanico")
	private String listarMecanicos(Long id){
		mecanicoDAO.excluir(id);
		return "redirect:listarMecanicos";
	}

	@RequestMapping("/editarMecanicoFormulario")
	private String editarMecanicoFormulario(Long id, Model model){
		System.out.println("controller> "+ id);

		Mecanico f = mecanicoDAO.recuperar(id);	

		model.addAttribute("mecanico", f);
		return "mecanicos/editar_mecanico_formulario";
		
	}

	@RequestMapping("/editarMecanico")
	private String editarMecanico(Mecanico mecanico, @RequestParam(value="image", required=false) MultipartFile image){
		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+mecanico.getNome()+mecanico.getCpf()+".png";
			AulaFileUtil.saveFile(path, image);
		}

		mecanicoDAO.editar(mecanico);
		return "redirect:listarMecanicos";		
	}

	@RequestMapping("/cadastrarMecanico")
	public String cadastrarMecanico(Mecanico mecanico, 
			@RequestParam(value="image", required=false)
	MultipartFile image){

		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+mecanico.getNome()+mecanico.getCpf()+".png";
			AulaFileUtil.saveFile(path, image);
		}


		mecanicoDAO.inserir(mecanico);
		return "mecanicos/cadastrar_mecanico_ok";
	}

	@RequestMapping("/cadastrarMecanicoFormulario")
	public String cadastrarMecanicoFormulario(){
		return "mecanicos/cadastrar_mecanico_formulario";
	}











}
