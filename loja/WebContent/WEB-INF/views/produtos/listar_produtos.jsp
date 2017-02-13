<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title> Produtos </title>
</head>

<link href="http://192.168.0.113/select2.min.css" rel="stylesheet" />
<script src="http://192.168.0.113/select2.min.js"></script>


<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<body style="font-family:arial; font-size:12pt">

<center> 
<h2> Produtos </h2>
<br/>
<a href="menu"> Voltar ao Menu</a> <br /> <br />
<a href="cadastrarProdutoFormulario"> Cadastrar Produto </a> <br /> <br /> 
</center>
<br />
<center>Aperte Ctrl F para pesquisar <br /> ou clique abaixo</center>
<br />



<div id="table-container">
<table border="1" id="tei">
		<thead style="background-color: #4169E1;;">
		
		<tr>
		<th colspan="7">
		<select style="width:100%" class="select_lista_prod" name="prods" id="testeisac">
	  		<optgroup label="Produtos">
	  		<option value="" selected> </option>
	  		<c:forEach var="produto" items="${produtos}">
	    		<option value="${produto.precoVenda}#${produto.id}"> |
	    		${produto.id} |
			    ${produto.nome} ${produto.referencia} |
			    ${produto.fornecedor} |
			    ${produto.local} |
			    ${produto.codigo} | 
			    Preço: ${produto.precoVenda} |
				 </option>
	    	</c:forEach>
	  		</optgroup>
		</select>
		</th>
		
		
		<th colspan="2">
		<a href="#topo"> <font color="white">Ir para o topo</font></a>
		</th>
		
		</tr>
		
		<tr style="color: white;">
			<th width="4%" height="5"> <center> <b> ID </b> </center> </th>
			<th width="25%"> <center> <b> Nome </b> </center> </th>
			<th width="15%"> <center> <b> Fornecedor </b> </center> </th>
			<th width="15%"> <center> <b> Local </b> </center> </th>
			<th width="15%"> <center> <b> Código </b> </center> </th>
			<th width="2%"> <center> <b> Preço Compra </b> </center> </th>
			<th width="2%"> <center> <b> Preço Venda </b> </center> </th>
			<th width="5%"> <center> <b> Qtd. </b> </center> </th>
			<th width="5%"> <center> <b> Editar </b> </center></th>		
		</tr>
		
		
		</thead>
	<tbody>
	<c:forEach var="produto" items="${produtos}">
		<tr>
			<td height="100"> <center>${produto.id} </center></td>
			<td> ${produto.nome} ${produto.referencia} </td>
			<td> <center> ${produto.fornecedor} </center> </td>
			<td> <center> ${produto.local} </center> </td>
			<td> <center> ${produto.codigo} </center> </td>
			<td> <center>  ${produto.precoCompra}</center> </td>
			<td> <center>  ${produto.precoVenda} </center> </td>
			<td> <center> ${produto.quantidade} </center> </td>
			<td> <center> <a href="editarProdutoFormulario?id=${produto.id}"> Editar</a> </center></td>	
			
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>



<br/>

	<script type="text/javascript" src="http://192.168.0.113/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript" src="http://localhost/jquery-3.1.1.min.js"></script>
 	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/floatthead/2.0.1/jquery.floatThead.min.js"></script>
 	<script type="text/javascript" src="http://192.168.0.113/jquery.floatThead.min.js"></script>
	<script type="text/javascript" src="http://192.168.0.113/select2.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
	  $(".select_lista_prod").select2();
	
	
	});
	
	
	
	$(".lista_produtos").click(function(){
		var id = $(this).attr("data-index");
		var r = confirm("Tem certeza que deseja excluir?");
		if (r == true) {
			window.location.replace("excluirProduto?id="+id);
		}else{

		}
	});
		
	
	var table = $('#tei');
	table.floatThead();
	
	
	$("a[href='#topo']").click(function() {
		  $("html, body").animate({ scrollTop: 0 }, "slow");
		  return false;
		});
	
	</script>
	


</body>
</html>