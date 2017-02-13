package br.ufc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.dao.IAtendimentoDAO;
import br.ufc.dao.ICaixaDAO;
import br.ufc.dao.IClienteDAO;
import br.ufc.dao.IFuncionarioDAO;
import br.ufc.dao.IProdutoDAO;
import br.ufc.model.Atendimento;
import br.ufc.model.Caixa;
import br.ufc.model.Cliente;
import br.ufc.model.Funcionario;
import br.ufc.model.Produto;
import br.ufc.util.TempoUtil;

@Controller
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

	@Autowired
	@Qualifier(value="atendimentoDAOHibernate")
	private IAtendimentoDAO atendimentoDAO;

	@Autowired
	@Qualifier(value="caixaDAOHibernate")
	private ICaixaDAO caixaDAO;


	@Autowired
	@Qualifier(value="tempoUtil")
	private TempoUtil tempoUtil;

	@RequestMapping("/editarAtendimentoFormulario")
	public String editarAtendimentoFormulario(Long id, Model model){

		List<Funcionario> funcionarios = funcionarioDAO.listar();
		List<Cliente> clientes =  clienteDAO.listar();
		List<Produto> produtos = produtoDAO.listar();

		Atendimento atendimento = atendimentoDAO.recuperar(id);
		Integer linhas = atendimentoDAO.obterLinhas(id);

		model.addAttribute("atendimento", atendimento);
		model.addAttribute("produtos", produtos);
		model.addAttribute("clientes", clientes);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("linhas", linhas);
		return "atendimentos/editar_atendimento_formulario";
	}

	@RequestMapping("/cadastrarAtendimentoFormulario")
	private String cadastrarAtendimentoFormulario(Model model){
		List<Cliente> clientes = clienteDAO.listar();
		List<Funcionario> funcionarios = funcionarioDAO.listar();
		List<Produto> produtos = produtoDAO.listar();

		Long iden = atendimentoDAO.obterUltimoId();

		model.addAttribute("clientes", clientes);
		model.addAttribute("funcionarios",funcionarios);
		model.addAttribute("produtos", produtos);
		model.addAttribute("data", tempoUtil.obterData());
		model.addAttribute("iden", iden);


		return "atendimentos/cadastrar_atendimento_formulario";
	}

	@RequestMapping("/salvarAtendimento")
	private String salvarAtendimento(Atendimento atendimento,Cliente cliente, Funcionario funcionario,
			String[] prods, Long[] qtds, Model model){

		// Recebendo os IDs de produtos da JSP
		List<Long> idProdutos = new ArrayList<Long>();
		for (int i=0; i < prods.length; i++){
			if (prods[i] != null){
				try{
					//System.out.println("ids->"+ prods[i].split("#")[1]);		
					idProdutos.add(Long.parseLong(prods[i].split("#")[1]));
				}catch (ArrayIndexOutOfBoundsException e){
					continue;
				}
			}
		}

		//Recebendo as quantidades da JSP
		List<Long> quantidades = new ArrayList<Long>();
		for (int i=0; i < qtds.length; i++){
			if (qtds[i] != null){
				quantidades.add((qtds[i]));
			}
		}


		// Recuperando os produtos do Banco
		List<Produto> produtos = new ArrayList<Produto>();
		for (Long id:idProdutos){
			produtos.add(produtoDAO.recuperar(id));
		}

		
		
		
	


		
		
		// Diminuindo as quantidades dos produtos do banco
		for (int i = 0; i < produtos.size(); i++){
			//System.out.println("qtd antes:" + produtos.get(i).getQuantidade());
			Produto p = produtos.get(i);
			
			
			p.setQuantidade(produtos.get(i).getQuantidade() - quantidades.get(i));


			//System.out.println("qtd depois:" + produtos.get(i).getQuantidade());


			//List<Atendimento> novosAtendimentos = p.getAtendimentos();
			//novosAtendimentos.add(atendimento);
			//p.setAtendimentos(novosAtendimentos);

			produtoDAO.editar(p);

		}
		/*
		 *    --- fim ----
		 */



		// SEtando as quantidades para cada produto para este ATENDIMENTO sendo as definidas na JSP
		// Isso aparentemete não pode funcionar
		/*
		for (int i = 0; i < produtos.size(); i++){
			produtos.get(i).setQuantidade(quantidades.get(i));
			System.out.println("Atendimento:: Produto: "+ produtos.get(i).getNome() + " com quantidade: " +quantidades.get(i));

		}
		 */
		
		//List<Produto> vazia = new ArrayList<>();
		//atendimento.setProdutos(vazia);
		//atendimentoDAO.editar(atendimento);
		
		System.out.println("```````````");
		for (Produto p:produtos){
			System.out.println("Ta aqui os produtos produtos: "+p.getNome());
			
		}
		System.out.println("```````````");
		
		atendimento.setProdutos(produtos);
		atendimentoDAO.editar(atendimento);
		
		System.out.println("```````````========");
		for (Produto p:atendimento.getProdutos()){
			System.out.println("Ta aqui os produtos do atendimento: "+p.getNome());
			
		}
		System.out.println("```````````========");

		
	
		cliente = clienteDAO.recuperar(cliente.getCliId());

		cliente.setDivida(cliente.getDivida()+atendimento.getDebito());
		clienteDAO.editar(cliente);

		funcionario = funcionarioDAO.recuperar(funcionario.getFunId());

		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);

		for(Produto p:atendimento.getProdutos()){
			System.out.println("P: " +p.getNome());
		}


		atendimentoDAO.editar(atendimento);

		// Caro Isac do Futuro
		// esse bloco de código faz o many to many funcionar
		// aceite.
		for (Produto p:atendimento.getProdutos()){
			List<Atendimento> novosAtendimentos = p.getAtendimentos();
			novosAtendimentos.add(atendimento);
			p.setAtendimentos(novosAtendimentos);
			produtoDAO.editar(p);
		}
		// Fim do bloco


		/// O try catch para caso ainda não exista caixa, criar o caixa automaticamente
		// Acontece quando o atendimento é realizado antes de salvar o caixa
		try {
			Caixa caixa = caixaDAO.recuperar(tempoUtil.obterData());
			List<String> descEntradas = caixa.getDescricaoEntrada();
			List<Double> entr = caixa.getEntradas();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}catch (NullPointerException e) {
			Caixa caixa = new Caixa();
			caixa.setData(tempoUtil.obterData());
			List<String> descEntradas = new ArrayList<String>();
			List<Double> entr = new ArrayList<Double>();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}


		
		Long iden = atendimento.getAteId();
		Integer linhas = atendimento.getProdutos().size();
		model.addAttribute("linhas", linhas);
		model.addAttribute("iden", iden);
		

		return "atendimentos/cadastrar_atendimento_ok";
	}


	@RequestMapping("/cadastrarAtendimento")
	private String cadastrarAtendimento(Atendimento atendimento,Cliente cliente, Funcionario funcionario,
			String[] prods, Long[] qtds, Model model){

		// Recebendo os IDs de produtos da JSP
		List<Long> idProdutos = new ArrayList<Long>();
		for (int i=0; i < prods.length; i++){
			if (prods[i] != null){
				try{
					System.out.println("iiids->"+ prods[i].split("#")[1]);		
					idProdutos.add(Long.parseLong(prods[i].split("#")[1]));
				}catch (ArrayIndexOutOfBoundsException e){
					continue;
				}
			}
		}

		//Recebendo as quantidades da JSP
		List<Long> quantidades = new ArrayList<Long>();
		for (int i=0; i < qtds.length; i++){
			if (qtds[i] != null){
				quantidades.add((qtds[i]));
			}
		}


		// Recuperando os produtos do Banco
		List<Produto> produtos = new ArrayList<Produto>();
		for (Long id:idProdutos){
			produtos.add(produtoDAO.recuperar(id));
		}



		// Diminuindo as quantidades dos produtos do banco
		for (int i = 0; i < produtos.size(); i++){
			//System.out.println("qtd antes:" + produtos.get(i).getQuantidade());
			Produto p = produtos.get(i);
			p.setQuantidade(produtos.get(i).getQuantidade() - quantidades.get(i));


			//System.out.println("qtd depois:" + produtos.get(i).getQuantidade());

			/*
			List<Atendimento> novosAtendimentos = p.getAtendimentos();
			novosAtendimentos.add(atendimento);
			p.setAtendimentos(novosAtendimentos);
			 */
			//*/
			produtoDAO.editar(p);

		}

		// SEtando as quantidades para cada produto para este ATENDIMENTO sendo as definidas na JSP
		// Isso aparentemete não pode funcionar
		/*
		for (int i = 0; i < produtos.size(); i++){
			produtos.get(i).setQuantidade(quantidades.get(i));
			System.out.println("Atendimento:: Produto: "+ produtos.get(i).getNome() + " com quantidade: " +quantidades.get(i));

		}
		 */
		atendimento.setProdutos(produtos);

		cliente = clienteDAO.recuperar(cliente.getCliId());

		cliente.setDivida(cliente.getDivida()+atendimento.getDebito());
		clienteDAO.editar(cliente);

		funcionario = funcionarioDAO.recuperar(funcionario.getFunId());

		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);

		for(Produto p:atendimento.getProdutos()){
			System.out.println("P: " +p.getNome());
		}


		atendimentoDAO.inserir(atendimento);

		// Caro Isac do Futuro
		// esse bloco de código faz o many to many funcionar
		// aceite.
		for (Produto p:atendimento.getProdutos()){
			List<Atendimento> novosAtendimentos = p.getAtendimentos();
			novosAtendimentos.add(atendimento);
			p.setAtendimentos(novosAtendimentos);
			produtoDAO.editar(p);
		}
		// Fim do bloco


		/// O try catch para caso ainda não exista caixa, criar o caixa automaticamente
		// Acontece quando o atendimento é realizado antes de salvar o caixa
		try {
			Caixa caixa = caixaDAO.recuperar(tempoUtil.obterData());
			List<String> descEntradas = caixa.getDescricaoEntrada();
			List<Double> entr = caixa.getEntradas();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}catch (NullPointerException e) {
			Caixa caixa = new Caixa();
			caixa.setData(tempoUtil.obterData());
			List<String> descEntradas = new ArrayList<String>();
			List<Double> entr = new ArrayList<Double>();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}

		Long iden = atendimentoDAO.obterUltimoId();
		Integer linhas = produtos.size();
		System.out.println("--<<><><><>::: "+linhas);
		
		model.addAttribute("iden", iden-1);
		model.addAttribute("linhas", linhas);


		return "atendimentos/cadastrar_atendimento_ok";
	}

	
	@RequestMapping("/cadastrarAtendimento2")
	private String cadastrarAtendimento2(Atendimento atendimento,Cliente cliente, Funcionario funcionario,
			String[] prods, Long[] qtds, Model model){

		// Recebendo os IDs de produtos da JSP
		List<Long> idProdutos = new ArrayList<Long>();
		for (int i=0; i < prods.length; i++){
			if (prods[i] != null){
				try{
					System.out.println("iiids->"+ prods[i].split("#")[1]);		
					idProdutos.add(Long.parseLong(prods[i].split("#")[1]));
				}catch (ArrayIndexOutOfBoundsException e){
					continue;
				}
			}
		}

		//Recebendo as quantidades da JSP
		List<Long> quantidades = new ArrayList<Long>();
		for (int i=0; i < qtds.length; i++){
			if (qtds[i] != null){
				quantidades.add((qtds[i]));
			}
		}


		// Recuperando os produtos do Banco
		List<Produto> produtos = new ArrayList<Produto>();
		for (Long id:idProdutos){
			produtos.add(produtoDAO.recuperar(id));
		}



		// Diminuindo as quantidades dos produtos do banco
		for (int i = 0; i < produtos.size(); i++){
			//System.out.println("qtd antes:" + produtos.get(i).getQuantidade());
			Produto p = produtos.get(i);
			p.setQuantidade(produtos.get(i).getQuantidade() - quantidades.get(i));


			//System.out.println("qtd depois:" + produtos.get(i).getQuantidade());

			/*
			List<Atendimento> novosAtendimentos = p.getAtendimentos();
			novosAtendimentos.add(atendimento);
			p.setAtendimentos(novosAtendimentos);
			 */
			//*/
			produtoDAO.editar(p);

		}

		// SEtando as quantidades para cada produto para este ATENDIMENTO sendo as definidas na JSP
		// Isso aparentemete não pode funcionar
		/*
		for (int i = 0; i < produtos.size(); i++){
			produtos.get(i).setQuantidade(quantidades.get(i));
			System.out.println("Atendimento:: Produto: "+ produtos.get(i).getNome() + " com quantidade: " +quantidades.get(i));

		}
		 */
		atendimento.setProdutos(produtos);

		cliente = clienteDAO.recuperar(cliente.getCliId());

		cliente.setDivida(cliente.getDivida()+atendimento.getDebito());
		clienteDAO.editar(cliente);

		funcionario = funcionarioDAO.recuperar(funcionario.getFunId());

		atendimento.setCliente(cliente);
		atendimento.setFuncionario(funcionario);

		for(Produto p:atendimento.getProdutos()){
			System.out.println("P: " +p.getNome());
		}


		atendimentoDAO.editar(atendimento);

		// Caro Isac do Futuro
		// esse bloco de código faz o many to many funcionar
		// aceite.
		for (Produto p:atendimento.getProdutos()){
			List<Atendimento> novosAtendimentos = p.getAtendimentos();
			novosAtendimentos.add(atendimento);
			p.setAtendimentos(novosAtendimentos);
			produtoDAO.editar(p);
		}
		// Fim do bloco


		/// O try catch para caso ainda não exista caixa, criar o caixa automaticamente
		// Acontece quando o atendimento é realizado antes de salvar o caixa
		try {
			Caixa caixa = caixaDAO.recuperar(tempoUtil.obterData());
			List<String> descEntradas = caixa.getDescricaoEntrada();
			List<Double> entr = caixa.getEntradas();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}catch (NullPointerException e) {
			Caixa caixa = new Caixa();
			caixa.setData(tempoUtil.obterData());
			List<String> descEntradas = new ArrayList<String>();
			List<Double> entr = new ArrayList<Double>();
			descEntradas.add("Atendimento "+cliente.getNome());
			entr.add(atendimento.getValorPago());

			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}

		Long iden = atendimento.getAteId();
		Integer linhas = produtos.size();
		System.out.println("--<<><><><>::: "+linhas);
		
		model.addAttribute("iden", iden);
		model.addAttribute("linhas", linhas);


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
		return "atendimentos/listar_atendimentos_cliente";
	}

	@RequestMapping("/listarAtendimentosFuncionario")
	private String listarAtendimentosFuncionario(Long id, Model model){
		Funcionario funcionario = funcionarioDAO.recuperar(id);
		Collection<Atendimento> atendimentos = funcionario.getAtendimentos();

		List<Atendimento> atendimentosOrdenados = atendimentoDAO.ordenarAtendimentos((List<Atendimento>) atendimentos);
		Collections.reverse(atendimentosOrdenados);


		//Double debitoTotal = clienteDAO.obterDebitoTotalCliente(id);


		model.addAttribute("atendimentos", atendimentosOrdenados);
		model.addAttribute("funcionario", funcionario);
		return "atendimentos/listar_atendimentos_funcionario";
	}




	@RequestMapping("/realizarPagamento")
	private String realizarPagamento(Long id, Double valor, String descricao, HttpSession session){
		Funcionario f = (Funcionario) session.getAttribute("funcionario_logado");

		Cliente cliente = clienteDAO.recuperar(id);
		//Funcionario funcionario = funcionarioDAO.recuperar(1L);
		cliente.setDivida(cliente.getDivida()+valor);
		clienteDAO.editar(cliente);
		Atendimento a =  new Atendimento();
		a.setData(tempoUtil.obterData());
		a.setCliente(cliente);
		a.setFuncionario(f);
		a.setDescricao(descricao);
		a.setValorPago(valor);
		a.setDebito(0.0);


		try {
			Caixa caixa = caixaDAO.recuperar(tempoUtil.obterData());
			List<String> descEntradas = caixa.getDescricaoEntrada();
			List<Double> entr = caixa.getEntradas();
			descEntradas.add("Pagamento "+cliente.getNome());
			entr.add(a.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}catch (NullPointerException e) {
			Caixa caixa = new Caixa();
			caixa.setData(tempoUtil.obterData());
			List<String> descEntradas = new ArrayList<String>();
			List<Double> entr = new ArrayList<Double>();
			descEntradas.add("Pagamento "+cliente.getNome());
			entr.add(a.getValorPago());
			caixa.setDescricaoEntrada(descEntradas);
			caixa.setEntradas(entr);
			caixaDAO.atualizar(caixa);
		}



		atendimentoDAO.inserir(a);
		return "redirect:listarAtendimentosCliente?id="+id;
	}



}
