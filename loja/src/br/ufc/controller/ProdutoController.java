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

import br.ufc.dao.IProdutoDAO;
import br.ufc.model.Produto;
import br.ufc.util.AulaFileUtil;

@Controller
@Transactional
public class ProdutoController {

	@Autowired
	@Qualifier(value="produtoDAOHibernate")
	private IProdutoDAO produtoDAO;

	@Autowired
	private ServletContext context;

	@RequestMapping("/listarProdutos")
	private String listarClientes(Model model){
		List<Produto> produtos = produtoDAO.listar();
		model.addAttribute("produtos", produtos);
		return "produtos/listar_produtos";
	}

	@RequestMapping("/excluirProduto")
	private String listarClientes(Long id){
		produtoDAO.excluir(id);
		return "redirect:listarProdutos";
	}

	@RequestMapping("/editarProdutoFormulario")
	private String editarProdutoFormulario(Long id, Model model){
		Produto produto = produtoDAO.recuperar(id);
		model.addAttribute("produto", produto);
		return "produtos/editar_produto_formulario";
	}

	@RequestMapping("/editarProduto")
	private String editarProduto(Produto produto, @RequestParam(value="image", required=false) MultipartFile image){
		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+produto.getCodigo()+".png";
			AulaFileUtil.saveFile(path, image);
		}		
		produtoDAO.editar(produto);
		return "redirect:listarProdutos";		
	}

	@RequestMapping("/cadastrarProduto")
	private String cadastrarProduto(Produto produto, 
			@RequestParam(value="image", required=false) MultipartFile image){
		if (image!=null && !image.isEmpty()){
			String path = context.getRealPath("/");
			path+="/resources/images/"+produto.getCodigo()+".png";
			AulaFileUtil.saveFile(path, image);
		}
		produtoDAO.inserir(produto);
		return "produtos/cadastrar_produto_ok";
	}

	@RequestMapping("/cadastrarProdutoFormulario")
	private String cadastrarProdutoFormulario(){
		return "produtos/cadastrar_produto_formulario";
	}
}
