<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Menu </title>
</head>
<body style="font-family:arial; font-size:12pt">


<center> 
<img width="200" height="200" src='<c:url value="/resources/images/logo.png" />' />
<br/>
${s}, ${funcionario_logado.nome}! <br />
<br />

 
<form action="cadastrarAtendimentoFormulario" method="GET" target="_blank">
<input type="submit" value="Atendimento" style="height:30px;width:145px">
<input type="number" style="width:3%" name="linhas" value="10" size="1"> 
<br />
</form> 

<form action="cadastrarCaixaFormulario" method="GET" target="_blank">
<input type="submit" value="Caixa" style="height:30px;width:145px"> 
<input type="number" style="width:3%" name="linhas" value="20" size="1"> 
<br />
</form> 


<form action="cadastrarClienteFormulario" method="POST">
<input type="submit" value="Cadastrar Cliente" style="height:30px;width:200px">
<br /> 
</form>

<form action="listarClientes" method="POST">
<input type="submit" value="Listar Clientes" style="height:30px;width:200px">
<br /> 
</form>

<form action="cadastrarProdutoFormulario" method="POST">
<input type="submit" value="Cadastrar Produto" style="height:30px;width:200px">
<br /> 
</form>

<form action="listarProdutos" method="POST">
<input type="submit" value="Listar Produtos" style="height:30px;width:200px">
<br /> 
</form>


<form action="cadastrarFuncionarioFormulario" method="POST">
<input type="submit" value="Cadastrar Funcionario" style="height:30px;width:200px">
<br /> 
</form>

<form action="listarFuncionarios" method="POST">
<input type="submit" value="Listar Funcionarios" style="height:30px;width:200px">
<br /> 
</form>

<form action="logout" method="POST">
<input type="submit" value="Sair do Sistema" style="height:30px;width:200px">
<br /> 
</form>


<!--  COM LINKS
<a href="cadastrarClienteFormulario"> Cadastrar Cliente </a> <br />
<a href="cadastrarProdutoFormulario"> Cadastrar Produto </a> <br />
<a href="cadastrarMecanicoFormulario"> Cadastrar Mecânico </a> <br />
<a href="listarMecanicos"> Listar Mecânicos </a> <br />
<a href="logout"> Sair do Sistema </a> <br />
-->

</center>

</body>
</html>