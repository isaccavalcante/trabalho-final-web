<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Entrar no Sistema </title>
</head>
<body style="font-family:arial; font-size:12pt">

<center>
<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>


<form action="login" method="post" >
<table>
    <tr>
      <td align="right"> Login:</td>
      <td align="left"> <input type="text" name="login"/> </td>
    </tr>
    <tr>
      <td align="right"> Senha:</td>
      <td align="left"> <input type="password" name="senha"/> </td>
    </tr>    
</table>
<br />
<input type="submit" value=" ENTRAR " style="height:40px; width:150px" />
</form>

</center>

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