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
<body style="font-family:arial; font-size:12pt">

<center> 
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>



<h2> Cadastrar Produto </h2>

<form action="cadastrarProduto" method="post" enctype="multipart/form-data">	

  <table>
    <tr>
      <td align="right"> Nome: </td>
      <td align="left"><input type="text" name="nome" /></td>
    </tr>
    <tr>
      <td align="right"> Referência: </td>
      <td align="left"><input type="text" name="referencia" /></td>
    </tr>
    <tr>
      <td align="right"> Quantidade: </td>
      <td align="left"><input type="text" name="quantidade" /></td>
    </tr>
    <tr>
      <td align="right"> Descrição: </td>
      <td align="left"><input type="text" name="descricao" /></td>
    </tr>
    <tr>
      <td align="right"> Preço: </td>
      <td align="left"><input type="text" name="preco" /></td>
    </tr>
    <tr>
      <td align="right"> Local: </td>
      <td align="left"><input type="text" name="local" /></td>
    </tr>
        <tr>
      <td align="right"> Imagem:</td>
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
	<input type="submit" value="CADASTRAR" style="height:40px; width:150px">
</form>
<br /> 	<br />
<a href="menu"> Voltar </a>

</center>



</body>
</html>