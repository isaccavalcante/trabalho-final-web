<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Histórico de Caixa </title>
</head>
<body>

<h3>

<table border="1">
	<c:forEach var="caixa" items="${caixas}">
		<tr>
			<td> ${caixa.data} </td>		
		</tr>
	</c:forEach>
	
</table>

</h3>
</body>
</html>