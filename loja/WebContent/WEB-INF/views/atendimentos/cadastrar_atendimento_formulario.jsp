<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link href="http://192.168.0.113/select2.min.css" rel="stylesheet" />
<script src="http://192.168.0.113/select2.min.js"></script>


<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Atendimento </title>

<style type="text/css" media="print">
  @page { size: landscape; }
</style>

<body style="font-family:arial; font-size:12pt">


<form id="100" action="cadastrarAtendimento" method="post" enctype="multipart/form-data" autocomplete="off" >	

<div align="center"> 
<table style="width:99%">
<tr >
<td style="width:12%">

<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=100>
Funcionário <br />
<select style="width:98%" class="select_lista_fun" name="funId">
  <optgroup label="Funcionários" >
  	<option value="None" selected> </option>
  	<c:forEach var="funcionario" items="${funcionarios}">
    <option value="${funcionario.funId}" > ${funcionario.nome} ${funcionario.sobrenome} </option>
    </c:forEach>	    
  </optgroup>
</select> <br />

Cliente <br />
<select style="width:98%" class="select_lista_cli" name="cliId">
  <optgroup label="Clientes" >
  	<option value="None" selected> </option>
  	<c:forEach var="cliente" items="${clientes}">
    <option value="${cliente.cliId}"> ${cliente.nome} ${cliente.sobrenome}</option>    
    </c:forEach>
  </optgroup>
</select> <br />
Descrição: <input type="text" name="descricao" value="" style="width:45%" id="desc"> <br />
Data: <input type="text" name="data" value="${data}" style="width:65%" readonly > <br />
Moto: <input type="text" name="moto" value="" style="width:65%" value=""> <br />
Placa:<input type="text" name="placa" value="" style="width:65%" value=""> <br />
Total: <input type="text" style="height:25px;width:50px" name="valorTotal" value="0" readonly class="texto_total"> <br />
Pago: &nbsp;&nbsp;&nbsp;<input type="text" style="height:25px;width:50px" value="0" name="valorPago" class="texto_pago"> <br />
Débito: <input type="text" style="height:25px;width:50px" name="debito" value="0" readonly class="texto_debito"> <br />
<input type="button" class="cadastrar" value="Salvar" style="height:40px; width:150px">
<input type="submit" style="visibility:hidden; height:0px;width:0px" class="cadastrar_escondido" value="Salvar escondido" >
</td>

<td style="width=100%" align="right">
<table border="1"  style="width:100%">
		<tr>
			<td width="5%" align="center"> <b> Item </b> </td>
			<td width="70%"align="center"> <b> ID &nbsp; — &nbsp; Produto &nbsp;  </b> </td>
			<td width="5%" align="center"> <b> Preço </b> </td>
			<td width="5%"align="center"> <b> Quant. </b> </td>
			<td width="5%" align="center"> <b> Desconto </b> </td>
			<td width="5%" align="center"> <b> Total </b> </td>
		</tr>
				
		<c:forEach var="i" begin="1" end="10">
		<tr> </tr>
			<td align="center"> ${i} </td>
			
			<td >
				<select style="width:100%" data-index="${i}" class="select_lista_prod" name="prods" id="testeisac">
				  <optgroup label="Produtos">
				  	<option value="" selected> </option>
				  	<c:forEach var="produto" items="${produtos}">
				    <option value="${produto.precoVenda}#${produto.id}"> ${produto.id} — ${produto.nome} ${produto.referencia}</option>
				    </c:forEach>
				  	</optgroup>
				</select>
			</td>
			
			
			
			<td align="center"> <label class="preco" data-index="${i}"></label> </td>
			<td align="center"> <input class="quantidade" data-index="${i}" size="1" type="text" name="qtds">  </td>
			<td align="center"> <input class="desconto" data-index="${i}" size="1" type="text" name="desc">  </td>
			<td align="center"> <label class="total" data-index="${i}" ></label> </td>
		
		</c:forEach>
				
		<tr>
		<td> </td>
		<td> </td>
		<td> </td>
		<td> </td>
		<td align="center"> <b> Total<br />Final: </b> </td>
		<td align="center"> <label class="tf"> </label> </td>
		</tr>
</table>
</td>	
</tr>
</table>
</div>
</form>

<div align="center">
Assinatura do Cliente: <br /><br />
<hr width="30%">
</div>

<div align="center"> <button onclick="Imprimir()" style="height:45px;width:100px"> Imprimir</button> 
</div>
<script>
function Imprimir() {
    window.print();
}
</script>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript" src="http://192.168.0.113/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
	<script type="text/javascript" src="http://192.168.0.113/select2.min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
	  $(".select_lista_prod").select2();
	  $(".select_lista_fun").select2();
	  $(".select_lista_cli").select2();
	  var x = $(".texto_total").val();
	  $(".texto_total").val(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
	});
	
	
		
	$(".select_lista_prod").change(function(){
		var preco = $(this).val().split('#')[0];
		var prod_id = $(this).val().split('#')[1];

		$(".aux[data-index=" + $(this).attr("data-index") + "]").val(prod_id);
		
		
		
		
		$(".preco[data-index=" + $(this).attr("data-index") + "]").text(preco);
		$(".quantidade[data-index=" + $(this).attr("data-index") + "]").val("1");
		
		
		
		
		// DANDO CERTO
		var preco = parseFloat($(".preco[data-index=" + $(this).attr("data-index") + "]").text());
		var quantidade = $(".quantidade").val();
		$(".total[data-index=" + $(this).attr("data-index") + "]").text(parseFloat(preco * quantidade).toFixed(2));
		
		// SOMATORIO -->
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".total[data-index='" + i +"']").text());
		}
		$(".tf").text(s);
		$(".texto_total").val(s);
		
		
		//
		
		// ATUALIZAR DEBITO
		var pago = parseFloat($(".texto_pago").val())
		var total = parseFloat($(".texto_total").val())
		
		$(".texto_debito").val(pago - total);
		
		//+ ou -			+ ou -
		var x = $(".texto_total").val();
		$(".texto_total").val(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
		//+ ou -			+ ou -
		
	});
	
	$(".quantidade").on("keyup",function(){	
		var preco = parseFloat($(".preco[data-index=" + $(this).attr("data-index") + "]").text());
		var quantidade = $(this).val();
		$(".total[data-index=" + $(this).attr("data-index") + "]").text(parseFloat(preco * quantidade).toFixed(2));
		
		// SOMATORIO -->
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".total[data-index='" + i +"']").text());
		}
		$(".tf").text(s);
		$(".texto_total").val(s);
		
		// ATUALIZAR DEBITO
		var pago = parseFloat($(".texto_pago").val())
		var total = parseFloat($(".texto_total").val())
		
		$(".texto_debito").val(pago - total);
		
		
		//+ ou -			+ ou -
		var x = $(".texto_total").val();
		$(".texto_total").val(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
		//+ ou -			+ ou -
		
		
	});
	
	$(".texto_pago").on("keyup",function(){
		
		var pago = parseFloat($(".texto_pago").val())
		var total = parseFloat($(".texto_total").val())
		
		$(".texto_debito").val(pago - total);
		
		
		
		//+ ou -			+ ou -
		var x = $(".texto_total").val();
		$(".texto_total").val(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
		//+ ou -			+ ou -
	});
	
	
	$(".desconto").on("keyup",function(){
		var preco = parseFloat($(".preco[data-index=" + $(this).attr("data-index") + "]").text());
		var quantidade = $(".quantidade[data-index=" + $(this).attr("data-index") + "]").val();
		var desconto = $(this).val();
		console.log(preco);
		console.log(quantidade);
		console.log(desconto);
		$(".total[data-index=" + $(this).attr("data-index") + "]").text(parseFloat( (preco * quantidade) -( (preco * quantidade) * (desconto/100)) ).toFixed(2));
		
		// SOMATORIO -->
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".total[data-index='" + i +"']").text());
		}
		$(".tf").text(s);
		$(".texto_total").val(s);
		
		
		// ATUALIZAR DEBITO
		var pago = parseFloat($(".texto_pago").val())
		var total = parseFloat($(".texto_total").val())
		
		$(".texto_debito").val(pago - total);		
		
		//+ ou -			+ ou -
		var x = $(".texto_total").val();
		$(".texto_total").val(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
		//+ ou -			+ ou -
		
	});
	
	
 	$(".cadastrar").click(function(){
 		var fun = $(".select_lista_fun").val();
 		var cli = $(".select_lista_cli").val();
 		
 		if (fun=="None"){
 			alert("Escolha um Funcionário!");
 		} else if (cli=="None") {
 			alert("Escolha um Cliente!");
 		} else {
 			$('.cadastrar_escondido').trigger('click');
 		}
	});
	
	</script>

	
	
</body>
</html>