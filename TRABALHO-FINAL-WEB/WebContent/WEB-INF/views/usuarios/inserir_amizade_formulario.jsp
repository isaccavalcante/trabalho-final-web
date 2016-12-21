<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir amigos</title>
</head>
<body>
<h2>Inserir amigos para ${usuario.nome}</h2>

<form action="inserirAmizade" method="post">
	<table border="1">
		<c:forEach var="u" items="${potenciais_amigos}">
			<tr>
				<td>${u.id}</td>
				<td>${u.nome}</td>
				<td><img width="100" height="100" src='<c:url value="/resources/images/${u.login}.png" />' /></td>
				<td><form:checkbox path="amizade.amigos" value="${u.id }" /></td>
			</tr>
		</c:forEach>
		
	</table>
	<input type="submit" value="OK" />
</form>
<br/>
<a href="./">INDEX</a>
</body>
</html>