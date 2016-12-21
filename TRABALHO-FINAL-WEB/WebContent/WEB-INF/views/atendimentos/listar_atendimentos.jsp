<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Atendimentos </title>
</head>
<body>

<center> 
<h2> Atendimentos do Cliente </h2>

<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>
</center>

<div align="center">
<a href="menu"> Menu </a> <br />
<a href="listarClientes"> Listar Clientes </a>
</div>
<br />
<br />

<table border="1">
		<tr>
			<td width="19%"> <center> <b> Data </b> </center> </td>
			<td width="19%"> <center> <b> Moto </b> </center> </td>
			<td width="19%"> <center> <b> Placa </b> </center> </td>
			<td width="19%"> <center> <b> Total </b> </center> </td>
			<td width="19%"> <center> <b> Pago </b> </center> </td>
			<td width="19%"> <center> <b> Débito </b> </center> </td>
		</tr>

	<c:forEach var="atendimento" items="${atendimentos}">
		<tr>
			<td> <center> ${atendimento.data} </center> </td>
			<td> <center> ${atendimento.moto} </center> </td>
			<td> <center> ${atendimento.placa} </center> </td>
			<td> <center> ${atendimento.valorTotal} </center> </td>
			<td> <center> ${atendimento.valorPago} </center> </td>
			<td> <center> ${atendimento.debito} </center> </td>
		</tr>
					
	</c:forEach>
	
		<tr>
			<td> </td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td> <center> Debito Total </center> </td>
			<td> <center> <input type="text" style="height:25px;width:50px" name="debito" readonly class="texto_debito" value="${debitoTotal}"> <br /></center> </td>
		</tr>	
</table>

<br/>
Realizar pagamento:
<br/>

<form action="realizarPagamento?id=${idCli}" method="post" enctype="multipart/form-data">	
Valor:  <input type="text" name="valor" />
<input type="submit" value="Efetuar" style="height:40px; width:150px">
</form>


<br/>

</body>
</html>