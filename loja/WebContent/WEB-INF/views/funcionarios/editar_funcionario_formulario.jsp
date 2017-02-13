<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Editar Funcionário </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;">


<div align="center">
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=150>

<h2> Editar Funcionário </h2>
<a href="menu"> Voltar ao Menu </a><br/><br/>

<form action="editarFuncionario" method="post" enctype="multipart/form-data" autocomplete="off" >	
<input type="hidden" name="funId" value="${funcionario.funId} ">
<div style="width:350px;background:#93969b;border:1px;border-style: solid;
border-color: black;text-align:center">
  <table>
    <tr>
      <td align="right"> Nome:</td>
      <td align="left"><input type="text" name="nome" value="${funcionario.nome}"/></td>
    </tr>
    <tr>
      <td align="right"> Sobrenome:</td>
      <td align="left"><input type="text" name="sobrenome" value="${funcionario.sobrenome}"/></td>
    </tr>
    <tr>
      <td align="right"> CPF:</td>
      <td align="left"><input type="text" name="cpf" value="${funcionario.cpf}"/></td>
    </tr>
    <tr>
      <td align="right">Login:</td>
      <td align="left"><input type="text" name="login" value="${funcionario.login}"/></td>
    </tr>
    <tr>
      <td align="right">Senha:</td>
      <td align="left"><input type="password" name="senha" value=""/></td>
    </tr>
    <tr>
      <td align="right">Cargo:</td>
      <td align="left"><input type="text" name="cargo" value="${funcionario.cargo}"/></td>
    </tr>
    <tr>
      <td align="right">Salário:</td>
      <td align="left"><input type="text" name="salario" value="${funcionario.salario}"/></td>
    </tr>   
    <tr>
      <td align="right">Foto:</td>
      <td align="left"><input type="file" name="image"/></td>
    </tr>

  </table>
</div>
<br />
	<input type="submit" value="Salvar" style="height:40px; width:150px">
</form>
<br /> 	<br />

</div>



</body>
</html>