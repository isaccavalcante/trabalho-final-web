<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Cadastrar Funcionário </title>
</head>
<body>

<center> 
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>

<h2> Cadastrar Funcionário </h2>

<form action="cadastrarFuncionario" method="post" enctype="multipart/form-data">	

  <table>
    <tr>
      <td align="right"> Nome:</td>
      <td align="left"><input type="text" name="nome" /></td>
    </tr>
    <tr>
      <td align="right"> Sobrenome:</td>
      <td align="left"><input type="text" name="sobrenome" /></td>
    </tr>
    <tr>
      <td align="right"> CPF:</td>
      <td align="left"><input type="text" name="cpf" /></td>
    </tr>
    <tr>
      <td align="right">Login:</td>
      <td align="left"><input type="text" name="login" /></td>
    </tr>
    <tr>
      <td align="right">Senha:</td>
      <td align="left"><input type="password" name="senha" /></td>
    </tr>
    <tr>
      <td align="right">Cargo:</td>
      <td align="left"><input type="text" name="cargo" /></td>
    </tr>
    <tr>
      <td align="right">Salário:</td>
      <td align="left"><input type="text" name="salario" /></td>
    </tr>   
    <tr>
      <td align="right">Foto:</td>
      <td align="left"><input type="file" name="image" align="right" /></td>
    </tr>

  </table>
	
	<!--
	Nome: <input type="text" name="nome"> <br />
	CPF: <input type="text" name="cpf"> <br />
	Login: <input type="text" name="login"> <br />
	Senha: <input type="password" name="senha"> <br />
	Foto: <input type="file" name="image" align="right" /> <br />
	-->


	
	<br />
	
	<input type="submit" value="SALVAR" style="height:40px; width:150px">
</form>
<br /> 	<br />
<a href="menu"> Voltar </a>
</center>



</body>
</html>