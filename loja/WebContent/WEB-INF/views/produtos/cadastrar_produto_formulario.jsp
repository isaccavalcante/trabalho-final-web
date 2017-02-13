<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Cadastrar Produto </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;">

<div align="center"> 
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=150>

<h2> Cadastrar Produto </h2>
<a href="menu"> Voltar ao Menu </a><br/><br/>

<form action="cadastrarProduto" method="post" enctype="multipart/form-data" autocomplete="off" >	

<div style="width:350px;background:#93969b;border:1px;border-style: solid;
border-color: black;text-align:center">
  <table>
    <tr>
      <td align="right"> Nome: </td>
      <td align="left"><input type="text" name="nome" value=""/></td>
    </tr>
    <tr>
      <td align="right"> Referência: </td>
      <td align="left"><input type="text" name="referencia" value=""/></td>
    </tr>
    <tr>
      <td align="right"> Quantidade: </td>
      <td align="left"><input type="text" name="quantidade" value="0"/></td>
    </tr>
    <tr>
      <td align="right"> Preço de Compra: </td>
      <td align="left"><input type="text" name="precoCompra" value="0.0"/></td>
    </tr>
    <tr>
      <td align="right"> Preço de Venda: </td>
      <td align="left"><input type="text" name="precoVenda" value="0.0"/></td>
    </tr>
    <tr>
      <td align="right"> Local: </td>
      <td align="left"><input type="text" name="local" value=""/></td>
    </tr>
    <tr>
      <td align="right"> Fornecedor: </td>
      <td align="left"><input type="text" name="fornecedor" value=""/></td>
    </tr>
    <tr>
      <td align="right"> Código: </td>
      <td align="left"><input type="text" name="codigo" value=""/></td>
    </tr>

    
    
  </table>
</div>
	<!--
	Nome: <input type="text" name="nome"> <br />
	CPF: <input type="text" name="cpf"> <br />
	Login: <input type="text" name="login"> <br />
	Senha: <input type="password" name="senha"> <br />
	Foto: <input type="file" name="image" align="right" /> <br />
	-->


	
	<br />
	<input type="submit" value="CADASTRAR" style="height:40px; width:150px">
</form>
<br /> 	<br />

</div>
</body>
</html>