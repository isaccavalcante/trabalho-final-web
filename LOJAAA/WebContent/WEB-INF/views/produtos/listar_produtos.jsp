<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Estoque </title>
</head>
<body>

<center> 
<h2> Produtos </h2>

<img src="http://bit.do/logoloja" width=200, height=200>
</center>

<div align="center">
<a href="menu"> Voltar </a> <br />
<a href="cadastrarProdutoFormulario"> Cadastrar Produto </a>

 </div>
<br />

<table border="1">
		<tr>
			<td width="7%"> <center> <b> Imagem </b> </center> </td>
			<td width="25%"> <center> <b> Nome </b> </center> </td>
			<td width="25%"> <center> <b> Descrição </b> </center> </td>
			<td width="15%"> <center> <b> Local </b> </center> </td>
			<td width="5%"> <center> <b> Preço  </b> </center> </td>
			<td> </td>
			<td> </td>			
		</tr>

	<c:forEach var="produto" items="${produtos}">
		<tr>
			<td><img width="100" height="100" src='<c:url value="/resources/images/${produto.referencia}.png" />' /></td>
			<td>${produto.nome} - ${produto.referencia} </td>
			<td>${produto.descricao}</td>
			<td> <center> ${produto.local} </center> </td>
			<td> <center>  ${produto.preco}</center> </td>
			<td><a href="editarProdutoFormulario?id=${produto.id}"> Editar</a></td>	
			<td> <input type="button" class="lista_produtos" value="Excluir" data-index="${produto.id}" />  </td>
		</tr>
	</c:forEach>
	
</table>

<br/>

 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".lista_produtos").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirProduto?id="+id);
		}else{

		}
	});
		
	</script>

</body>
</html>