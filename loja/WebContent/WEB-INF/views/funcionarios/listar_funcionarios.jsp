<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Funcionários</title>
</head>
<link href="http://192.168.0.113/select2.min.css" rel="stylesheet" />
<script src="http://192.168.0.113/select2.min.js"></script>


<body style="font-family:arial; font-size:12pt">

<center> 
<h2> Funcionários </h2>
<br/>
<a href="menu"> Voltar ao Menu</a> <br /> <br />
<a href="cadastrarFuncionarioFormulario"> Cadastrar Funcionário </a>
</center>
<br />
<br />
<br />
<center>Aperte Ctrl F para pesquisar</center>
<br />


<table border="1" id="tei">
		<thead style="background-color: #4169E1;">
		<tr style="color: white;">
			<th width="7%" height="40"> <center> <b> Foto </b> </center> </th>
			<th width="15%"> <center> <b> Nome </b> </center> </th>
			<th width="25%"> <center> <b> CPF </b> </center> </th>
			<th width="25%"> <center> <b> Cargo </b> </center> </th>
			<th width="5%"> <center> <b> Salario </b> </center> </th>
			<th width="5%"> <center> <b> Editar </b> </center> </th> 
			<th width="5%"> <center> <b> Histórico </b> </center> </th>	
			
		</tr>
		
	</thead>
	<c:forEach var="funcionario" items="${funcionarios}">

		<tr >
			<td align="center"><img width="100" height="100" src='<c:url value="/resources/images/${funcionario.login}.png" />' /></td>
			<td>${funcionario.nome}</td>
			<td> <center> ${funcionario.cpf} </center></td>			
			<td> <center>${funcionario.cargo}</center></td>
			<td> <center>${funcionario.salario}</center></td>
			<td> <center><a href="editarFuncionarioFormulario?id=${funcionario.funId}"> Editar</a></center></td>
			<td> <center> <a href="listarAtendimentosFuncionario?id=${funcionario.funId}"> Histórico </a>  </center> </td>
		</tr>
		
	</c:forEach>
	
</table>


<br/>



 	<script type="text/javascript" src="http://localhost/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$(".lista_funcionarios").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirFuncionario?id="+id);
		}else{

		}
	});
	
	var table = $('#tei');
	table.floatThead();
		
	</script>



</body>
</html>