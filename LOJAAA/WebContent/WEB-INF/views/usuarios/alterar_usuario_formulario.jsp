<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Alterar Usuário</title>
</head>
<body>

<h2>Alterar Usuário</h2>

<img src='<c:url value="/resources/images/logo.png"/>' width=200, height=200>

<form action="alterarUsuario" method="post">
	<input type="hidden" name="id" value="${usuario.id}">
	Nome: <input type="text" name="nome" value="${usuario.nome}"><br />
	Login: <input type="text" name="login" value="${usuario.login}"><br />
	Senha: <input type="password" name="senha" value="${usuario.senha}"><br />
	<input type="file" name="image" /><br />
	<input type="submit" value="ALTERAR">
</form>

</body>
</html>