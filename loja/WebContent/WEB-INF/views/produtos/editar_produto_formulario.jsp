<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Editar Produto </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;">


<div align="center">
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=150>

<h2> Editar Produto </h2>

<a href="menu"> Voltar ao Menu </a><br/><br/>

<form action="editarProduto" method="post" enctype="multipart/form-data" autocomplete="off" >	


<input type="hidden" name="id" value="${produto.id} ">
<div style="width:350px;background:#93969b;border:1px;border-style: solid;
border-color: black;text-align:center">
  <table>
    <tr>
      <td align="right"> Nome:</td>
      <td align="left"><input type="text" name="nome" value="${produto.nome}" /></td>
    </tr>
    <tr>
      <td align="right"> Referência:</td>
      <td align="left"><input type="text" name="referencia" value="${produto.referencia}" /></td>
    </tr>
    <tr>
      <td align="right"> Quantidade:</td>
      <td align="left"><input type="text" name="quantidade" value="${produto.quantidade}" /></td>
    </tr>
    <tr>
      <td align="right">Preço de Compra:</td>
      <td align="left"><input type="text" name="precoCompra" value="${produto.precoCompra}" /></td>
    </tr>
    <tr>
      <td align="right">Preço de Venda:</td>
      <td align="left"><input type="text" name="precoVenda" value="${produto.precoVenda}" /></td>
    </tr>
    <tr>
      <td align="right">Local:</td>
      <td align="left"><input type="text" name="local" value="${produto.local}" /></td>
    </tr>
    <tr>
      <td align="right"> Fornecedor: </td>
      <td align="left"><input type="text" name="fornecedor" value=""/></td>
    </tr>
    <tr>
      <td align="right"> Código: </td>
      <td align="left"><input type="text" name="codigo" value="${produto.codigo}"/></td>
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