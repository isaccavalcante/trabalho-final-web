<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos &nbsp; | &nbsp;  Entrar </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;">
    

<div align="center">
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=150>
<br/>
${info}
<form action="login" method="post" >
<table>
    <tr>
      <td align="right"> Login: </td>
      <td align="left"> <input type="text" name="login" size="10"/> </td>
    </tr>
    <tr>
      <td align="right"> Senha:</td>
      <td align="left"> <input type="password" name="senha" size="10"/> </td>
    </tr>    
</table>
<br />
<input type="submit" value="Entrar" style="height:40px; width:150px" />
</form>
</div>


<!-- 
<h2> Entrar no Sistema </h2>
<form action="login" method="post" >
Login: <input type="text" name="login"/><br />
Senha: <input type="password" name="senha"/><br />
<input type="submit" value="OK" />
</form>
-->

</body>
</html>