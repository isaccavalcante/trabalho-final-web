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
<title> Cadastrar Caixa </title>
</head>

<body style="font-family:arial; font-size:12pt">

<div align="center">

<h2> Caixa Diário</h2>

<a href="menu"> Voltar ao Menu </a>
</div>
<br />
<br />


<form action="cadastrarCaixa" class="form" autocomplete="off" >
<div align="center"> 
Data: <input type="text" size="8" readonly value="${data}">
Saldo de hoje: R$ <input type="text" size=5 class="saldo-diario" value="${caixa.saldoDiario}" readonly > 
Saldo total: R$ <input type="text" size=5 class="saldo-total" readonly value="${caixa.saldoTotal}">
<input type="hidden" size=0 class="saldo-total-aux" readonly value="${caixa.saldoTotal}">
Salvo por ultimo às ${param.salvo_as}


<input type="hidden" size=2 value="${caixa.id}" name="id"> <br />
<input type="hidden" size="2" value="${param.linhas}" readonly name="linhas"> <br/>
<input type="submit" style="height:40px; width:120px" value="Salvar" class="salvar">
</div>

<br />



<table border="1" >
		
		<tr>
			<td width="2%" align="center"> <b> Item </b> </td>
			<td width="35%" align="center"> <b> Descrição </b> </td>
			<td width="2%" align="center"> <b> Entrada </b> </td>
			<td width="35%" align="center"> <b> Descrição </b> </td>
			<td width="2%" align="center"> <b> Saída </b> </td>			
		</tr>
		

		<c:forEach var="i" begin="1" end="${param.linhas}">
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
    	
    		
		
			
		<td width="10%"> <input style="width:96%" type="text" value="${de}"  name="descricaoEntradaForm" >  </td>
		<td width="10%"> <input class="entr" data-index="${i}" style="width:94%" type="text" value="${e}" name="entradasForm" >  </td>
		<td width="10%"> <input style="width:96%" type="text" value="${ds}" name="descricaoSaidaForm" > </td>
		<td width="10%"> <input class="said" data-index="${i}" style="width:94%" type="text" value="${s}" name="saidasForm" >  </td>
		</tr>
		</c:forEach>

		<tr> 
		<td > </td>
		<td align="right"> <b>Total de entrada: R$ </b></td>
		<td > <input type="text" name="total_entrada" value="${caixa.totalEntrada}" class="total-entrada" readonly/> </td>
		<td align="right"> <b>Total de saída: R$ </b></td>
		<td > <input type="text" name="total_saida" value="${caixa.totalSaida}" class="total-saida" readonly/></td>
		</tr>

		<tr>
		<td> </td>
		<td> </td>
		<td> </td>
		<td align="right"> <b> Saldo de hoje: R$ </b></td>
		<td> <input type="text" name="saldo" class="saldo-diario" value="${caixa.saldoDiario}" readonly/> </td>
		</tr>		
</table>
</form>

<div align="center"> <h3> <a href="cadastrarCaixaFormulario?linhas=${param.linhas+1}&salvo_as=${param.salvo_as}"> Adicionar linha</a> </h3>
</div>

<div align="center">

<br />
<br />
<br />

Data: <input type="text" size="8" readonly value="${data}">
Saldo de hoje: R$ <input type="text" class="saldo-diario" size=5, value="${caixa.saldoDiario}" readonly > 
Saldo total: R$ <input type="text" size=5 class="saldo-total" readonly value="${caixa.saldoTotal}">
Salvo por ultimo às ${param.salvo_as}
</div>

<br/>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript" src="http://192.168.0.113/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">

	$(document).ready(function() {
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".entr[data-index='" + i +"']").val());
		}
		
		$(".total-entrada").val(s);
		$(".saldo-diario").val($(".total-entrada").val()-$(".total-saida").val());
		var t = $(".saldo-total-aux").val();
	
	});
		
	
	$(".entr").on("keyup",function(){
		var entr = $(this).val();
		//alert(entr);
		//alert($(".saldo-total").val())
		
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".entr[data-index='" + i +"']").val());
		}
		
		$(".total-entrada").val(s);
		$(".saldo-diario").val($(".total-entrada").val()-$(".total-saida").val());
		var t = $(".saldo-total-aux").val();
		//$(".saldo-total").val(s+Number(t));
		//$(".saldo-total").val(t + s);
	});
	
	$(".said").on("keyup",function(){	
		var said = $(this).val();
		
		var s = 0;
		for (i = 1; i < ${param.linhas}+1; i++){
			s += Number($(".said[data-index='" + i +"']").val());
		}
		
		$(".total-saida").val(s);
		$(".saldo-diario").val($(".total-entrada").val()-$(".total-saida").val());
	});
	
	</script>
	
</body>
</html>