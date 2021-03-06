<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Clientes</title>
</head>
<body style="font-family:arial; font-size:12pt">

<center> 
<h2> Clientes </h2>
<br/>
<a href="menu"> Voltar ao Menu</a> <br /> <br />
<a href="cadastrarClienteFormulario"> Cadastrar Cliente </a>
</center>
<br />
<br />

<table border="1">
		<tr>
			<td width="7%"> <center> <b> Foto </b> </center> </td>
			<td width="25%"> <center> <b> Nome </b> </center> </td>
			<td width="25%"> <center> <b> Endereço </b> </center> </td>
			<td width="15%"> <center> <b> Telefone </b> </center> </td>
			<td width="5%"> <center> <b> Débito </b> </center> </td>
			<td width="5%"> <center> <b> Editar </b> </center> </td>
			<!-- <td> </td>  --> 
			<td width="5%"> <center> <b> Histórico </b> </center> </td>			
		</tr>

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
	
</table>

<br/>

 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".lista_clientes").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirCliente?id="+id);
		}else{

		}
	});
		
	</script>

</body>
</html>