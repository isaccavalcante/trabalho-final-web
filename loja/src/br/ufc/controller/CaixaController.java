package br.ufc.controller;

import java.util.ArrayList;
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


	@RequestMapping("/detalhesCaixaFormulario")
	public String detalhesCaixaFormulario(Long id, Model model){

		Caixa c = caixaDAOHibernate.recuperar(id);
		int linhas = caixaDAOHibernate.obterLinhas(id);

		model.addAttribute("linhas", linhas);
		model.addAttribute("caixa", c);

		return "caixa/editar_caixa_formulario";
	}

	@RequestMapping("/cadastrarCaixaFormulario")
	public String cadastrarCaixaFormulario(Long linhas, Model model){
		String data = tempoUtil.obterData();

		model.addAttribute("ultimoSaldo", caixaDAOHibernate.obterUltimoSaldo());
		model.addAttribute("caixa", caixaDAOHibernate.recuperar(data));
		model.addAttribute("data", data);
		return "caixa/cadastrar_caixa_formulario";
	}

	@RequestMapping("/cadastrarCaixa")
	public String cadastrarCaixa(Long id, String[] descricaoEntradaForm, String[] descricaoSaidaForm,
			Double[] entradasForm, Double[] saidasForm, Long linhas){
		String data = tempoUtil.obterData();

		Caixa c = new Caixa();

		c.setId(id);
		c.setData(data);


		// Recebendo as entradas, saidas e descrições em arays e adicionando a uma lista

		/*
		 *  
		 * 
		 * 
		 */

		/// VALORES DE SAIDA
		List<Double> entradas = new ArrayList<Double>();
		for (int i = 0; i < entradasForm.length; i++){
			if (entradasForm[i] != null){
				entradas.add(entradasForm[i]);
			}
		}
		c.setEntradas(entradas);


		// Somatório das Entradas
		Double totalEntrada = 0.0;
		for (Double entrada:entradas){
			totalEntrada += entrada;
		}
		c.setTotalEntrada(totalEntrada);
		//System.out.println("Total entrada " + totalEntrada);


		/// VALORES DE SAIDA
		List<Double> saidas = new ArrayList<Double>();
		for (int i = 0; i < saidasForm.length; i++){
			if (saidasForm[i] != null){
				saidas.add(saidasForm[i]);
			}
		}
		c.setSaidas(saidas);

		// Somatório das Saidas
		Double totalSaida = 0.0;
		for (Double saida:saidas){
			totalSaida += saida;
		}
		c.setTotalSaida(totalSaida);
		//System.out.println("Total saida " + totalSaida);


		/// VALORES DO SALDO
		//DIARIO
		Double saldoDiario = totalEntrada -  totalSaida;
		c.setSaldoDiario(saldoDiario);
		//System.out.println("Saldo diario " + saldoDiario);

		//TOTAL
		Double saldoTotal = caixaDAOHibernate.obterUltimoSaldo2(c.getId()) + saldoDiario;

		//System.out.println("Ultimo saldo " + caixaDAOHibernate.obterUltimoSaldo());
		c.setSaldoTotal(saldoTotal);
		//System.out.println("somando ultimo saldo + saldo diario...");
		//System.out.println("saldo total "+ saldoTotal);


		List<String> descricaoEntrada = new ArrayList<String>();
		System.out.println("excuse me, sir. I thik we have a missundesrtanding here...");
		for (int j=0; j< descricaoEntradaForm.length;j++){
			try{
				System.out.println(">>>" +descricaoEntradaForm[j]);

				if (descricaoEntradaForm[j] != ""){
					descricaoEntrada.add(descricaoEntradaForm[j]);
				}
			}catch (Exception e) {
				System.out.println("meh");
			}
		}
		c.setDescricaoEntrada(descricaoEntrada);

		List<String> descricaoSaida = new ArrayList<String>();
		for (int k=0; k< descricaoSaidaForm.length;k++){
			if (descricaoSaidaForm[k] != ""){
				descricaoSaida.add(descricaoSaidaForm[k]);
			}
		}
		c.setDescricaoSaida(descricaoSaida);


		caixaDAOHibernate.atualizar(c);


		return "redirect:cadastrarCaixaFormulario?linhas="+linhas +"&salvo_as="+tempoUtil.obterHora();
	}

	@RequestMapping("/listarCaixas")
	public String listarCaixas(Model model){
		List<Caixa> caixas = caixaDAOHibernate.listar();
		model.addAttribute("caixas", caixas);
		return "caixa/listar_caixas";
	}


}
