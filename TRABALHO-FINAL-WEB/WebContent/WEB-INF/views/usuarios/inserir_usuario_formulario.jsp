<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir Usuário</title>
</head>
<body>

<form action="inserirUsuario" method="post" enctype="multipart/form-data">
	Nome: <input type="text" name="nome"><br />
	Login: <input type="text" name="login"><br />
	Senha: <input type="password" name="senha"><br />
	<input type="file" name="image" /><br />
	
	<input type="submit" value="OK">
</form>

</body>
</html>