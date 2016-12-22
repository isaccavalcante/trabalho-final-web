<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Atendimento </title>
</head>
<body style="font-family:arial; font-size:12pt">

<center> <h2> Atendimento </h2> 
<a href="menu"> Voltar ao Menu </a>
</center>


<form id="100" action="cadastrarAtendimento" method="post" enctype="multipart/form-data">	

<table style="width:100%" >
<tr >
<td style="width:20%">
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>
<br />Funcionário <br />
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
Descrição: <input type="text" name="descricao" value="" style="width:50%"> <br />
Data: <input type="text" name="data" value="${data}" style="width:50%" readonly > <br />
Moto: <input type="text" name="moto" value="" style="width:50%" value=""> <br />
Placa:<input type="text" name="placa" value="" style="width:50%" value=""> <br />
Pago: &nbsp;&nbsp;&nbsp;<input type="text" style="height:25px;width:50px" value="0" name="valorPago" class="texto_pago"> <br />
Débito: <input type="text" style="height:25px;width:50px" name="debito" value="0" readonly class="texto_debito"> <br />
Total: <input type="text" style="height:25px;width:50px" name="valorTotal" value="0" readonly class="texto_total"> <br />
<input type="button" class="cadastrar" value="Salvar" style="height:40px; width:150px">
<input type="submit" style="visibility:hidden; height:0px;width:0px" class="cadastrar_escondido" value="Salvar escondido" >

</td>

<td style="width=100%" align="right">
<br /> <br /> 
<table border="1"  style="width:100%">
		
		<tr>
			<td width="5%"><center><b>Item</b></center></td>
			<td width="70%"><center><b>Produtos</b></center></td>
			<td width="5%"><center><b>Preço</b></center></td>
			<td width="5%"><center><b>Quant.</b></center></td>
			<td width="5%"><center><b>Desconto</b></center></td>
			<td width="5%"><center><b>Total</b></center></td>
		</tr>
				
		<c:forEach var="i" begin="1" end="${param.linhas}">
		<tr> </tr>
			<td > <center> ${i} </center> </td>
			
			<td >
				<select style="width:100%" data-index="${i}" class="select_lista_prod" >
				  <optgroup label="Produtos">
				  	<option value="" selected> </option>
				  	<c:forEach var="produto" items="${produtos}">
				    <option value="${produto.preco}"> ${produto.nome} - ${produto.referencia} </option>
				  	</c:forEach>
				  	</optgroup>
				</select>
				
			</td>
			
			<td > <center><label class="preco" data-index="${i}"></label> </center> </td>
			<td > <center> <input class="quantidade" data-index="${i}" size="1" type="text" name="qtd"> </center> </td>
			<td > <center> <input class="desconto" data-index="${i}" size="1" type="text" name="desc"> </center> </td>
			<td > <center><label class="total" data-index="${i}" ></label> </center> </td>
		
		</c:forEach>
				
		<tr>
		<td> </td>
		<td> </td>
		<td> </td>
		<td> </td>
		<td><center><b>Total<br />Final:</b></center></td>
		<td> <center><label class="tf"> </label></center></td>
		</tr>
</table>
</td>
</tr>
</table>

</form>


<center> <button onclick="Imprimir()" style="height:45px;width:150px"> Imprimir </button> </center>

<script>
function Imprimir() {
    window.print();
}
</script>



 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".select_lista_prod").change(function(){
		var preco = $(this).val();
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
		
		
	});
	
	$(".texto_pago").on("keyup",function(){
		
		var pago = parseFloat($(".texto_pago").val())
		var total = parseFloat($(".texto_total").val())
		
		$(".texto_debito").val(pago - total);
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