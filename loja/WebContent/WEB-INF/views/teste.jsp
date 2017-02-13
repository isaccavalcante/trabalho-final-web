<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Página de Teste </title>
</head>
<body background='<c:url value="/resources/images/bg.jpg"/>' style="font-family:arial; font-size:12pt;"> 

<div align="center">
<img width="200" height="150" src='<c:url value="/resources/images/logo.png" />' />
<br/>
${s}, ${funcionario_logado.nome}! <br />
<br />
</div>


</body>
</html>