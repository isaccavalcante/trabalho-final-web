<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos - Caixa </title>
</head>

<body>

<form action="cadastrarCaixa">

Data: ${data}
<br />

<br />
<br />
<table border="1">
		
		<tr>
			<td width="2%"> <center> <b>Item</b> </center> </td>
			<td width="35%"> <center> <b>Descrição</b> </center> </td>
			<td width="2%"> <center> <b>Entrada</b> </center> </td>
			<td width="35%"> <center> <b>Descrição</b> </center> </td>
			<td width="2%"> <center> <b>Saída</b> </center> </td>			
		</tr>
		
		
		<c:forEach var="i" begin="1" end="${param.linhas}">
		<tr>
			<td width="2%"> <center> ${i} </center> </td>
			
				
			</td>
			
			<td width="10%"> <input style="width:96%" type="text" name="descricao_entrada" >  </td>
			<td width="10%"> <input style="width:94%" type="text" name="entrada" >  </td>
			<td width="10%"> <input style="width:96%" type="text" name="descricao_saida" > </td>
			<td width="10%"> <input style="width:94%" type="text" name="saida" >  </td>
		
		</tr>
		</c:forEach>
		
		<tr>
		<td > </td>
		<td align="right"> Total de entrada: </td>
		<td > <input type="text" name="total_entrada" readonly/> </td>
		<td align="right"> Total de saída: </td>
		<td > <input type="text" name="total_saida" readonly/></td>
		</tr>

		<tr>
		<td> </td>
		<td> </td>
		<td> </td>
		<td align="right"> Saldo: </td>
		<td> <input type="text" name="saldo" readonly/> </td>
		</tr>
		
		
</table>

<div align="right">

<input type="submit" value=" SALVAR " style="height:30px;width:150px">
 </form>
<form action="menu"> <input type="submit" value=" VOLTAR " style="height:30px;width:150px" /> </form>
</div> 




</body>
</html>