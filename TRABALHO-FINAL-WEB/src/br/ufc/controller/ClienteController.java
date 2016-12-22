package br.ufc.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.dao.IClienteDAO;
import br.ufc.model.Cliente;
import br.ufc.util.AulaFileUtil;

@Controller
@Transactional
public class ClienteController {

	@Autowired
	@Qualifier(value="clienteDAOHibernate")
	private IClienteDAO clienteDAO;

	@Autowired
	private ServletContext context;

	@RequestMapping("/listarClientes")
	private String listarClientes(Model model){
		List<Cliente> clientes = clienteDAO.listar();
		model.addAttribute("clientes", clientes);
		Double debito = clienteDAO.obterDebitoTotaldeTodos();
		model.addAttribute("debito", debito);
		return "clientes/listar_clientes";
	}

	@RequestMapping("/excluirCliente")
	private String listarClientes(Long id){
		clienteDAO.excluir(id);
		return "redirect:listarClientes";
	}

	@RequestMapping("/editarClienteFormulario")
	private String editarClienteFormulario(Long id, Model model){
		Cliente cliente = clienteDAO.recuperar(id);
		model.addAttribute("cliente", cliente);
		return "clientes/editar_cliente_formulario";
	}

	@RequestMapping("/editarCliente")
	private String editarCliente(Cliente cliente, @RequestParam(value="image", required=false)
	MultipartFile image){

		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+cliente.getCpf()+".png";
			AulaFileUtil.saveFile(path, image);
		}

		clienteDAO.editar(cliente);
		return "redirect:listarClientes";

	}


	@RequestMapping("/cadastrarCliente")
	private String cadastrarCliente(Cliente cliente,		
			@RequestParam(value="image", required=false)
	MultipartFile image){

		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+cliente.getCpf()+".png";
			AulaFileUtil.saveFile(path, image);
		}

		cliente.setDivida(cliente.getDivida()*-1);
		clienteDAO.inserir(cliente);
		return "clientes/cadastrar_cliente_ok";
	}

	@RequestMapping("/cadastrarClienteFormulario")
	private String cadastrarClienteFormulario(){
		return "clientes/cadastrar_cliente_formulario";
	}



}