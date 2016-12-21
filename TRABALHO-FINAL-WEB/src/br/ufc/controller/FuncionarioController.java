
package br.ufc.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.IFuncionarioDAO;
import br.ufc.model.Funcionario;
import br.ufc.util.AulaFileUtil;
import br.ufc.util.PDFUtil;


@Controller
public class FuncionarioController {

	
	@Autowired
	private IFuncionarioDAO funcionarioDAO;

	@Autowired
	private ServletContext context;
	
	@Autowired
	private PDFUtil pdf;

	@RequestMapping("/listarFuncionarios")
	public String listarFuncionarios(Model model){
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		model.addAttribute("funcionarios", funcionarios);
		return "funcionarios/listar_funcionarios";
	}

	@RequestMapping("/excluirFuncionario")
	private String listarFuncionarios(Long id){
		funcionarioDAO.excluir(id);
		return "redirect:listarFuncionarios";
	}
	
	@RequestMapping("/gerarPDFFuncionarios")
	public String gerarPDFFuncionarios(){
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		
		pdf.gerarPDF(funcionarios);
		return "redirect:listarFuncionarios";
		
	}

	@RequestMapping("/editarFuncionarioFormulario")
	private String editarFuncionarioFormulario(Long id, Model model){
		Funcionario f = funcionarioDAO.recuperar(id);
		model.addAttribute("funcionario", f);
		
		return "funcionarios/editar_funcionario_formulario";
		
	}

	@RequestMapping("/editarFuncionario")
	private String editarFuncionario(Funcionario funcionario, @RequestParam(value="image", required=false) MultipartFile image){
		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+funcionario.getLogin()+".png";
			AulaFileUtil.saveFile(path, image);
		}

		funcionarioDAO.editar(funcionario);
		return "redirect:listarFuncionarios";		
	}

	@RequestMapping("/cadastrarFuncionario")
	public String cadastrarFuncionario(Funcionario funcionario, 
			@RequestParam(value="image", required=false)
	MultipartFile image){

		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+funcionario.getLogin()+".png";
			AulaFileUtil.saveFile(path, image);
		}


		funcionarioDAO.inserir(funcionario);
		return "funcionarios/cadastrar_funcionario_ok";
	}

	@RequestMapping("/cadastrarFuncionarioFormulario")
	public String cadastrarFuncionarioFormulario(){
		return "funcionarios/cadastrar_funcionario_formulario";
	}











}
