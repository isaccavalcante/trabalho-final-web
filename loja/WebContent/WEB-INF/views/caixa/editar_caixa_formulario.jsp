<%@page import="javassist.bytecode.stackmap.BasicBlock.Catch"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Formatter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Caixa </title>
</head>

<body style="font-family:arial; font-size:12pt">

<div align="center">
<h2> Detalhes Caixa </h2>

<a href="menu"> Voltar ao Menu </a>
</div>
<br />

Saldo do dia ${caixa.data} : <input type="text" size=5, readonly value="${caixa.saldoDiario}">
<br />
<br />
<br />
<br />



<table border="1">	
		<tr>
			<td width="2%" align="center"> <b> Item </b> </td>
			<td width="35%" align="center"> <b> Descrição </b> </td>
			<td width="2%" align="center"> <b> Entrada </b> </td>
			<td width="35%" align="center"> <b> Descrição </b> </td>
			<td width="2%" align="center"> <b> Saída </b> </td>			
		</tr>
		
		<c:forEach var="i" begin="1" end="${linhas}">
		<tr>
		<td width="2%" align="center"> ${i} </td>
			
			<c:catch var="descricaoEntradaException">
				<p hidden="true"> ${caixa.descricaoEntrada.get(i-1)} </p> 
    		</c:catch>
    		
    											
			<c:choose>
		      <c:when test="${descricaoEntradaException != null}">
					<c:set var="de" scope="session" value=""/>
		      </c:when>
		      
		      <c:otherwise>
		      	<c:set var="de" scope="session" value="${caixa.descricaoEntrada.get(i-1)}"/>
				</c:otherwise>
		    </c:choose>
    		
    		<c:catch var="entradaException">
				<p hidden="true"> ${caixa.entradas.get(i-1)} </p> 
    		</c:catch>
    		
    											
			<c:choose>
		      <c:when test="${entradaException != null}">
					<c:set var="e" scope="session" value=""/>
		      </c:when>
		      
		      <c:otherwise>
		      	<c:set var="e" scope="session" value="${caixa.entradas.get(i-1)}"/>
				</c:otherwise>
		    </c:choose>
    	
    		<c:catch var="descricaoSaidaException">
				<p hidden="true"> ${caixa.saidaEntrada.get(i-1)} </p> 
    		</c:catch>
    		
    											
			<c:choose>
		      <c:when test="${descricaoSaidaException != null}">
					<c:set var="ds" scope="session" value=""/>
		      </c:when>
		      
		      <c:otherwise>
		      	<c:set var="ds" scope="session" value="${caixa.descricaoSaida.get(i-1)}"/>
				</c:otherwise>
		    </c:choose>
    		
    		<c:catch var="saidaException">
				<p hidden="true"> ${caixa.saidas.get(i-1)} </p>
    		</c:catch>
    		
    											
			<c:choose>
		      <c:when test="${saidaException != null}">
					<c:set var="s" scope="session" value=""/>
		      </c:when>
		      
		      <c:otherwise>
		      	<c:set var="s" scope="session" value="${caixa.saidas.get(i-1)}"/>
				</c:otherwise>
		    </c:choose>
    	
		<td width="10%"> <input style="width:96%" type="text" value="${de}"  name="descricaoEntradaForm" readonly >  </td>
		<td width="10%"> <input style="width:94%" type="text" value="${e}" name="entradasForm" readonly>  </td>
		<td width="10%"> <input style="width:96%" type="text" value="${ds}" name="descricaoSaidaForm" readonly> </td>
		<td width="10%"> <input style="width:94%" type="text" value="${s}" name="saidasForm" readonly>  </td>
		</tr>
		</c:forEach>

		<tr> 
		<td > </td>
		<td align="right"> Total de entrada: </td>
		<td > <input type="text" name="total_entrada" value="${caixa.totalEntrada}" readonly/> </td>
		<td align="right"> Total de saída: </td>
		<td > <input type="text" name="total_saida" readonly value="${caixa.totalSaida}"/></td>
		</tr>

		<tr>
		<td> </td>
		<td> </td>
		<td> </td>
		<td align="right"> Saldo: </td>
		<td> <input type="text" name="saldo" readonly/> </td>
		</tr>
</table>

<br/>
 	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
 	$(".salvar").click(function(){
  		
 		
 		$(".helper").text( )
 		alert("Clique em OK para Salvar");
 		var h = new Date(new Date().getTime()).toLocaleTimeString();
 	  	
 		$(".helper").text(h); //Nao deu tempo ajeitar isso
 	});
	
	</script>
	
</body>
</html>