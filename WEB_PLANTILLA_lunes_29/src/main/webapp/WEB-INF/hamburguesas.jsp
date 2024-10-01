<%@page import="com.ipartek.modelo.dto.V_Producto"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Producto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <% 
List<V_Producto> listaProductos= new ArrayList<V_Producto>();
if(request.getAttribute(I_Conexion.ATR_LISTA_PRODUCTOS)!=null){
	listaProductos=(List<V_Producto>)request.getAttribute(I_Conexion.ATR_LISTA_PRODUCTOS);
}else{
	//TAREA PARA METER EN EL LOG
}
%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/cuadricula_prod.css">
	
	<script>
	    function increment(id) {
	        let quantity = document.getElementById('prod'+id);
	        quantity.value = parseInt(quantity.value) + 1;
	    }
	
	    function decrement(id) {
	        let quantity = document.getElementById('prod'+id);
	        if (quantity.value > 0) {
	            quantity.value = parseInt(quantity.value) - 1;
	        }
	    }
	</script>
</head>
<body>
	
	<%@ include file="includes/cabecera.jsp" %>
	
	<%@ include file="includes/menu.jsp" %>
	
	
	<main>
		<section class="cuadricula">
		
				<%for(Producto elem:listaProductos ){%>

			<article class="producto">
				<div class="nombre">
					<h2><%=elem.getProducto()%></h2>
				</div>
				<div class="precio">
					<h3><%=elem.getPrecio()%>
						€
					</h3>
				</div>
				<div class="imagen">
					<img src="imagenes/producto001.jpg" width="100%">
				</div>
				<div class="botonera">
					<form method="get" action="">
						<button type="button" onclick="decrement(<%=elem.getId()%>)">-</button>
						<input type="number" name="prod<%=elem.getId()%>"
							id="prod<%=elem.getId()%>" value="0" min="0" readonly>
						<button type="button" onclick="increment(<%=elem.getId()%>)">+</button>
						<input type="submit" value="OK">
					</form>
				</div>
			</article>
			<%
			}
			%>
			
		
		</section>
		
		<aside>
		parte de la pagina con el desglose
		</aside>
	
	</main>
	
	<%@ include file="includes/pie.jsp" %>
	
	




</body>
</html>