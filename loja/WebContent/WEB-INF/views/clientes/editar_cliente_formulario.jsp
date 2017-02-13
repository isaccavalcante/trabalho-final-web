<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Editar Cliente </title></head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;">

<div align="center"> 
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=150>

<h2> Editar Cliente </h2>
<a href="menu"> Voltar ao Menu </a><br/><br/>

<form action="editarCliente" method="post" enctype="multipart/form-data" autocomplete="off" >	
<div style="width:350px;background:#93969b;border:1px;border-style: solid;
border-color: black;text-align:center">
<input type="hidden" value="${cliente.cliId}" name="cliId"> 
  <table>
    <tr>
      <td align="right"> Nome:</td>
      <td align="left"><input type="text" name="nome" value="${cliente.nome}" /></td>
    </tr>
    <tr>
      <td align="right"> Sobrenome:</td>
      <td align="left"><input type="text" name="sobrenome" value="${cliente.sobrenome}" /></td>
    </tr>
    <tr>
      <td align="right"> CPF:</td>
      <td align="left"><input type="text" name="cpf" value="${cliente.cpf}" /></td>
    </tr>
    <tr>
      <td align="right">RG:</td>
      <td align="left"><input type="text" name="rg" value="${cliente.rg}" /></td>
    </tr>
    <tr>
      <td align="right">Endereço:</td>
      <td align="left"><input type="text" name="endereco" value="${cliente.endereco}" /></td>
    </tr>
    <tr>
      <td align="right">Telefone:</td>
      <td align="left"><input type="text" name="telefone" value="${cliente.telefone}" /></td>
    </tr>
    <tr>
      <td align="right">Débito:</td>
      <td align="left"><input type="text" name="divida" readonly value="${cliente.divida}" /></td>
    </tr>
    <tr>
      <td align="right"> Foto: </td>
      <td align="left"><input type="file" name="image" /></td>
    </tr>
</table>
</div>

<br />
	<input type="submit" value="Salvar" id="test" style="height:40px; width:150px">
</form>
<br /> <br />

</div>
 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	$("#test").click(function(){
		alert("Salvo!");
	});
		
	</script>

</body>
</html>