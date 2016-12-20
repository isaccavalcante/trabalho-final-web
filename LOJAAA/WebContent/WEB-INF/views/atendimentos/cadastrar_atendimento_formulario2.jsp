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
<body>

<center> <h2> Atendimento </h2> </center>


<!-- 
<!-- ID CLIENTE E ID DO FUNCIONARIO
<form action="cadastrarAtendimento" method="post" enctype="multipart/form-data">	
	<input type="text" name="cliId" class="cli" value=""> 
	<input type="text" name="funId" class="fun" value="">

 -->


<!--início da tabela inicial-->
<table> 
<tr>
<td align=left>

<!--início da outra tabela-->



<form action="cadastrarAtendimento" method="post" enctype="multipart/form-data">	
	<input type="submit" value="SALVAR" style="height:40px; width:150px">

<table>
<tr>
<td align="left"> Cliente: </td>
<td align="right">
<select style="width:100%" class="select_lista_cli" name="cliId">
  <optgroup label="Clientes" >
  	<option value="None" selected> </option>
  	<c:forEach var="cliente" items="${clientes}">
    <option value="${cliente.cliId}"> ${cliente.nome} ${cliente.sobrenome}</option>    
    </c:forEach>
  </optgroup>
</select>
</td>
</tr>
<tr>
<td align="left"> Funcionário: </td>
<td align="right">
<select style="width:100%" class="select_lista_fun" name="funId">
  <optgroup label="Funcionários" >
  	<option value="None" selected> </option>
  	<c:forEach var="funcionario" items="${funcionarios}">
    <option value="${funcionario.funId}" > ${funcionario.nome} ${funcionario.sobrenome} </option>
    </c:forEach>	    
  </optgroup>
</select>
</td>
</tr>
<tr>
<td align="left"> Moto: </td>
<td align="right"> <input type="text" name="moto" style="width:94%"> </td>
</tr>
<tr>
<td align="left"> Placa: </td>
<td align="right"> <input type="text" name="placa" style="width:94%"> </td>
</tr>
<tr>
<td align="left"> Data: </td>
<td align="right"> <input type="text" name="data" value="${data}" style="width:94%"> </td>
</tr>
</table>



<div align="right">
Valor Pago: <input type="text" style="height:25px;width:50px" name="valorPago"> <br />
Débito: <input type="text" style="height:25px;width:50px" readonly>
</div>



</form>
<!--fim outra tabela-->
<br />
<button onclick="Imprimir()" style="height:30px;width:150px"> IMPRIMIR </button>




</td>
<td align=right>
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>
</td>
</tr>
<!--fim da tabela inicial-->

</table>



<br />
<br />



<!-- PRODUTOS -->




<table border="1">
		
		<tr>
			<td width="1%"><center><b>Item</b></center></td>
			<td width="60%"><center><b>Descrição</b></center></td>
			<td width="1%"><center><b>Preço</b></center></td>
			<td width="1%"><center><b>Quant.</b></center></td>
			<td width="1%"><center><b>Desconto</b></center></td>
			<td width="1%"><center><b>Total</b></center></td>
		</tr>
		
		
		<c:forEach var="i" begin="1" end="${param.linhas}">
		<tr> </tr>
			<td > <center> ${i} </center> </td>
			
			<td >
				<select style="width:100%" data-index="${i}" class="select_lista_prod" name="produtos">
				  <optgroup label="Produtos">
				  	<option value="None" selected> </option>
				  	<c:forEach var="produto" items="${produtos}">
				    <option value="${produto.preco}"> ${produto.nome} - ${produto.referencia} </option>
				  	</c:forEach>
				  	</optgroup>
				</select>
				
			</td>
			
			<td > <center><label class="preco" data-index="${i}"></label> </center></td>
			<td > <center> <input class="quantidade" data-index="${i}" size="1" type="text" name="qtd"> </center> </td>
			<td > <center> <input class="desconto" data-index="${i}" size="1" type="text" name="desc"> </center> </td>
			<td > <center><label class="total" data-index="${i}" ></label> </center> </td>
		
		
		</tr>
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
<br />

<!-- 

<div align="right">
Valor Pago: <input type="text" style="height:25px;width:50px" name="valorPago"> <br />
Débito: <input type="text" style="height:25px;width:50px" readonly>
</div>

 -->

<center>
<br /><br />
Assinatura do Funcionário:
<br/> <br/> <br/>
<hr align="center" width="50%" size="1" color="black">
<br />
Assinatura do Cliente:
<br/> <br/> <br/>
<hr align="center" width="50%" size="1" color="black">
</center>
<br /> <br />

 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".select_lista_prod").change(function(){
		var preco = $(this).val();
		$(".preco[data-index=" + $(this).attr("data-index") + "]").text(preco);		
	});
	
	$(".quantidade").on("keyup",function(){
		var preco = parseFloat($(".preco[data-index=" + $(this).attr("data-index") + "]").text());
		var quantidade = $(this).val();
		$(".total[data-index=" + $(this).attr("data-index") + "]").text(parseFloat(preco * quantidade).toFixed(2));
		
		// EU QUE FIZ -->
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".total[data-index='" + i +"']").text());
		}
		$(".tf").text(s);
	});
	
	$(".desconto").on("keyup",function(){
		var preco = parseFloat($(".preco[data-index=" + $(this).attr("data-index") + "]").text());
		var quantidade = $(".quantidade[data-index=" + $(this).attr("data-index") + "]").val();
		var desconto = $(this).val();
		console.log(preco);
		console.log(quantidade);
		console.log(desconto);
		$(".total[data-index=" + $(this).attr("data-index") + "]").text(parseFloat( (preco * quantidade) -( (preco * quantidade) * (desconto/100)) ).toFixed(2));
		
	
	});
	</script>
</body>
</html>