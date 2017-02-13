<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Neto Motos | Histórico do Cliente </title></head>


<style type="text/css" media="print">
  @page { size: landscape; }
</style>

<body style="font-family:arial; font-size:12pt">

<center> 
<h2> Histórico do Cliente</h2>

<table border="2" >
  <tr>
    <td width=160 height=160><center><img width="150" height="150" src='<c:url value="/resources/images/${cliente.cpf}.png" />' /></center></td>
    <td width=160 height=160> ${cliente.nome} ${cliente.sobrenome} </td>
    <td width=160 height=160> ${cliente.endereco} <br/><br/> ${cliente.telefone} <br/><br/> RG: ${cliente.rg} <br/><br/> CPF: ${cliente.cpf} </td>  
  </tr>
   
  <tr>
    <td align="center"> Débito: R$ ${cliente.divida} </td>
    <td align="center"> <a href="menu"> Voltar ao Menu </a> <br /></td>
    <td align="center"> <a href="listarClientes"> Voltar aos Clientes </a></td>
  </tr>
</table>
<br />

<div style="width:350px;background:#93969b;border:1px;border-style: solid;
border-color: black;text-align:center">
<form action="realizarPagamento?id=${cliente.cliId}" method="post" enctype="multipart/form-data">	
<font color="white"> Realizar pagamento: </font>
<br/><br/>
<font color="white"> Descrição: </font> <input type="text" name="descricao" value="Parcela ${cliente.nome}"/> <br/>
<font color="white"> Valor: </font> <input type="text" name="valor" value="0" size="7" />
<input type="submit" value="Efetuar" style="height:40px; width:150px">
</form>

</div>

</center>


<br />

<table border="1">
		<tr>
			<td width="5%"> <center> <b> Data </b> </center> </td>
			<td width="20%"> <center> <b> Funcionário </b> </center> </td>
			<td width="20%"> <center> <b> Descrição </b> </center> </td>
			<td width="5%"> <center> <b> Moto </b> </center> </td>
			<td width="5%"> <center> <b> Placa </b> </center> </td>
			<td width="5%"> <center> <b> Total </b> </center> </td>
			<td width="5%"> <center> <b> Pago </b> </center> </td>
			<td width="5%"> <center> <b> Débito </b> </center> </td>
		</tr>

	<c:forEach var="atendimento" items="${atendimentos}">
		<tr>
			<td> <center> <a href="editarAtendimentoFormulario?id=${atendimento.ateId}&linhas=${atendimento.produtos.size()}"> ${atendimento.data} </a> </center> </td>
			<td> <center> ${atendimento.funcionario.nome} </center> </td>
			<td> <center> ${atendimento.descricao} </center> </td>
			<td> <center> ${atendimento.moto} </center> </td>
			<td> <center> ${atendimento.placa} </center> </td>
			<td> <center> ${atendimento.valorTotal} </center> </td>
			<td> <center> ${atendimento.valorPago} </center> </td>
			<td> <center> ${atendimento.debito} </center> </td>
		</tr>
					
	</c:forEach>
	
		<tr>
			<td> </td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td> </td>
			<td> <center> Debito Total </center> </td>
			<td> <center> <input type="text" style="height:25px;width:50px" name="debito" readonly class="texto_debito" value="${cliente.divida}"> <br /></center> </td>
		</tr>	
</table>

<br/>

<center> <button onclick="Imprimir()" style="height:45px;width:100px"> Imprimir</button> </center>

<script>
function Imprimir() {
    window.print();
}
</script>

<br/>



</body>
</html>