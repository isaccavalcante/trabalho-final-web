<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Menu </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;"> 
<!-- <body style="background-image: url('<c:url value="/resources/images/bg.jpg"/>'); background-color red;  font-family:arial; font-size:12pt;">  -->

<div align="center"> 
<img width="200" height="150" src='<c:url value="/resources/images/logo.png" />' />
<br/>
${s}, ${funcionario_logado.nome}! <br />
<br />

<form action="cadastrarAtendimentoFormulario" method="GET" target="_blank"> 
<input type="submit" value="Atendimento" style="height:30px;width:140px"> com
<input type="number" style="width:3%" name="linhas" value="10" size="1"> linhas
<br />
</form> 

<form action="cadastrarCaixaFormulario" method="GET" target="_blank">
<input type="submit" value="Caixa Diário" style="height:30px;width:140px"> com
<input type="number" style="width:3%" name="linhas" value="20" size="1"> linhas 
<br />
</form> 

<form action="listarAtendimentosFuncionario" method="GET" target="_blank"> 
<input type="submit" value="Atendimentos" style="height:30px;width:140px"> do
 <select  class="select_lista_fun" name="id">
  <optgroup label="Funcionários" >
  	<option value="None" selected> </option>
  	<c:forEach var="funcionario" items="${funcionarios}">
    <option value="${funcionario.funId}"> ${funcionario.nome} ${funcionario.sobrenome} </option>
    </c:forEach>	    
  </optgroup>
</select> 

<!--   <input type="number" style="width:3%" name="id" value="20" size="1"> linhas  -->
<br />
</form> 


<!--
<form action="listarAtendimentosFuncionario" method="POST">
<input type="submit" value="Atendimentos" style="height:30px;width:150px">
do funcionário: <input type="number" style="width:3%" name="funcionario" value="1" size="1"> 


 <select  class="select_lista_fun" name="funId">
  <optgroup label="Funcionários" >
  	<option value="None" selected> </option>
  	<c:forEach var="funcionario" items="${funcionarios}">
    <option value="${funcionario.funId}"> ${funcionario.nome} ${funcionario.sobrenome} </option>
    </c:forEach>	    
  </optgroup>
</select> 

<br />
</form>
-->


<form action="listarCaixas" method="POST">
<input type="submit" value="Histórico de Caixas" style="height:30px;width:200px">
<br /> 
</form>
<br />

<div style="width:200px;background:#4169E1;border:1px;text-align:center">
<font color="white"> <b> Mostrar </b></font> <br/>
<form action="listarClientes" method="POST">
<input type="submit" value="Mostrar Clientes" style="height:30px;width:200px">
<br /> 
</form>

<form action="listarProdutos" method="POST">
<input type="submit" value="Mostrar Produtos" style="height:30px;width:200px">
<br /> 
</form>


<form action="listarFuncionarios" method="POST">
<input type="submit" value="Mostrar Funcionarios" style="height:30px;width:200px">
<br />
</form>


</div>
<br/>
 
 
<div style="width:200px;background:#4169E1;border:1px;text-align:center">
<font color="white"> <b> Cadastrar </b></font> <br/>
<form action="cadastrarClienteFormulario" method="POST">
<input type="submit" value="Cadastrar Cliente" style="height:30px;width:200px">
<br /> 
</form>

<form action="cadastrarProdutoFormulario" method="POST">
<input type="submit" value="Cadastrar Produto" style="height:30px;width:200px; col">
<br /> 
</form>

<form action="cadastrarFuncionarioFormulario" method="POST">
<input type="submit" value="Cadastrar Funcionario" style="height:30px;width:200px">
<br />
</form>
</div>
<br/>

<div style="width:200px;background:#4169E1;border:1px;text-align:center">
<font color="white"> <b> Sair</b> </font> <br />
<form action="logout" method="POST">
<input type="submit" value="Sair do Sistema" style="height:30px;width:200px">
<br /> 
</form>
</div>

<!--  COM LINKS
<a href="cadastrarClienteFormulario"> Cadastrar Cliente </a> <br />
<a href="cadastrarProdutoFormulario"> Cadastrar Produto </a> <br />
<a href="cadastrarMecanicoFormulario"> Cadastrar Mecânico </a> <br />
<a href="listarMecanicos"> Listar Mecânicos </a> <br />
<a href="logout"> Sair do Sistema </a> <br />
-->

</div>

</body>
</html>