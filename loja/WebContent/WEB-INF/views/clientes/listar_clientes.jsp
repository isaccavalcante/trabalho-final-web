<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Clientes </title></head>

<link href="http://192.168.0.113/select2.min.css" rel="stylesheet" />
<script src="http://192.168.0.113/select2.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<body style="font-family:arial; font-size:12pt">

<center> 
<h2> Clientes </h2>
<br/>
<a href="menu"> Voltar ao Menu</a> <br /> <br />
<a href="cadastrarClienteFormulario"> Cadastrar Cliente </a> <br /> <br />
Débito total: R$ <label class="debito_clientes"> ${debito} </label> 
</center>
<br />
<center>Aperte Ctrl F para pesquisar <br/> ou clique abaixo </center>
<br />


<table border="1" id="isac">
		<thead style="background-color: #4169E1;">
		
		<tr>
		<th colspan="5">
		
		<select style="width:100%" class="select_lista_cli" name="cliId">
		 <optgroup label="Clientes" >
		 	<option value="None" selected> </option>
		 	<c:forEach var="cliente" items="${clientes}">
		   <option value="${cliente.cliId}"> |
		   ${cliente.nome} |
		   ${cliente.sobrenome} |
		   ${cliente.endereco} |
		   ${cliente.telefone} |
		   Débito: ${cliente.divida} |
		   </option>    
		   </c:forEach>
		  </optgroup>
		</select>
		</th>
		
		<th colspan="2">
		<a href="#topo"> <font color="white">Ir para o topo</font></a>
		</th>
		
		</tr>
		
		
		<tr style="color: white;">
			<th width="7%" height="40"> <center> <b> Foto </b> </center> </th>
			<th width="25%"> <center> <b> Nome </b> </center> </th>
			<th width="25%"> <center> <b> Endereço </b> </center> </th>
			<th width="15%"> <center> <b> Telefone </b> </center> </th>
			<th width="5%"> <center> <b> Débito </b> </center> </th>
			<th width="5%"> <center> <b> Editar </b> </center> </th>
			<!-- <td> </td>  --> 
			<th width="5%"> <center> <b> Histórico </b> </center> </th>			
		</tr>
	</thead>

	<tbody>
	<c:forEach var="cliente" items="${clientes}">
		<tr>
			<td><center><img width="100" height="100" src='<c:url value="/resources/images/${cliente.cpf}.png" />' /></center></td>
			<td>${cliente.nome} ${cliente.sobrenome}</td>
			<td>${cliente.endereco}</td>
			<td> <center> ${cliente.telefone} </center> </td>
			<td> <center> ${cliente.divida} </center> </td>
			<td> <center> <a href="editarClienteFormulario?id=${cliente.cliId}"> Editar</a> </center></td>	
			<!-- <td> Excluir  <input type="button" class="lista_clientes" value="Excluir" data-index="${cliente.cliId}" />   </td> -->
			<td> <center> <a href="listarAtendimentosCliente?id=${cliente.cliId}"> Histórico </a>  </center> </td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<br/>

 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript" src="http://192.168.0.113/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="http://192.168.0.113/select2.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/floatthead/2.0.1/jquery.floatThead.min.js"></script>
 	<script type="text/javascript" src="http://192.168.0.113/jquery.floatThead.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
	<script type="text/javascript">
		
	$(document).ready(function() {
		var x = $(".debito_clientes").text();
		
		// Formatando o valor em dinheiro
		$(".debito_clientes").text(Number(x).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
		
		$(".select_lista_cli").select2();
			
	});
	
	
	
	
	$(".lista_clientes").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirCliente?id="+id);
		}else{

		}
	});
	
	var table = $('#isac');
	table.floatThead();
	
	$("a[href='#topo']").click(function() {
		  $("html, body").animate({ scrollTop: 0 }, "slow");
		  return false;
		});
	
	</script>

</body>
</html>