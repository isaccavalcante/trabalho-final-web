<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Funcionários</title>
</head>
<body>

<center> 
<h2> Funcionários </h2>
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>
</center>
<div align="center">
<a href="menu"> Voltar </a> <br />
<a href="cadastrarFuncionarioFormulario"> Cadastrar Funcionario </a>

<form action="gerarPDFFuncionarios" method="POST">
<input type="submit" value="Gerar PDF" style="height:30px;width:200px" onclick="pdf()">
</form>

<br />
<button onclick="Imprimir()" style="height:30px;width:150px"> Imprimir pelo browser </button>


<script>
function pdf() {
    alert("PDF de funcionarios não foi gerado! Não funciona (ainda).");
}
</script>

<script>
function Imprimir() {
    window.print();
}
</script>




<br />
<br />


<center> 
<table border="1">

		<tr>
			<td width="7%"> <center> <b> Foto </b> </center> </td>
			<td width="15%"> <center> <b> Nome </b> </center> </td>
			<td width="25%"> <center> <b>  CPF </b> </center> </td>
			<td width="25%"> <center> <b>  Cargo </b> </center> </td>
			<td width="5%"> <center> <b> Salario  </b> </center> </td>
			<td> </td>
			<td> </td>
			
		</tr>

	<c:forEach var="funcionario" items="${funcionarios}">
		
		
		
		<tr>
			<td><img width="100" height="100" src='<c:url value="/resources/images/${funcionario.login}.png" />' /></td>
			<td>${funcionario.nome}</td>
			<td>${funcionario.cpf}</td>			
			<td>${funcionario.cargo}</td>
			<td>${funcionario.salario}</td>
			<td><a href="editarFuncionarioFormulario?id=${funcionario.funId}"> Editar</a></td>
			<td> Excluir <!-- <input type="button" class="lista_funcionarios" value="Excluir" data-index="${funcionario.funId}" /> --> </td>
			
		</tr>
	</c:forEach>
	
</table>


<br/>
</center>

 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".lista_funcionarios").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirFuncionario?id="+id);
		}else{

		}
	});
		
	</script>



</body>
</html>