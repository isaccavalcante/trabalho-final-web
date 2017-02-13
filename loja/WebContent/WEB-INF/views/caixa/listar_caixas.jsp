<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Caixas </title>
</head>
<body style="font-family:arial; font-size:12pt">

<center>
<h2> Histórico de Caixas </h2>


<a href="menu"> Voltar ao Menu</a>
<br/>
<br/>
<table border="1">
		<tr>
			<td width="10%"> <center> <b> Data </b> </center> </td>
			<td width="25%"> <center> <b> Saldo </b> </center> </td>
			<td width="25%"> <center> <b> Detalhes </b> </center> </td>
		</tr>

	<c:forEach var="caixa" items="${caixas}">
		<tr>
			<td> <center> ${caixa.data} </center> </td>
			<td> <center> R$ ${caixa.saldoDiario} </center> </td>
			<td> <center> <a href="detalhesCaixaFormulario?id=${caixa.id}" > Detalhes </a> </center> </td>
		</tr>
	</c:forEach>	
</table>
</center>

</body>
</html>