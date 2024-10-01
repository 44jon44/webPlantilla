<%@page import="com.ipartek.modelo.dto.V_Producto"%>
<%@page import="com.ipartek.modelo.I_Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Categoria"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<Categoria> listaCategorias= new ArrayList<Categoria>();
if(request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIAS)!=null){
	listaCategorias=(List<Categoria>)request.getAttribute(I_Conexion.ATR_LISTA_CATEGORIAS);
}else{
	//TAREA PARA METER EN EL LOG
}


V_Producto producto= new V_Producto();
if(request.getAttribute(I_Conexion.ATR_PRODUCTO)!=null){
	producto=(V_Producto)request.getAttribute(I_Conexion.ATR_PRODUCTO);
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
</head>
<body>
	
	<%@ include file="includes/cabecera_admin.jsp" %>
	
	<%@ include file="includes/menu_admin.jsp" %>
	
	
	<main>
		<section>
			
		
			<form method="get" action="ModificarProducto" >
				<h2>Insertar producto</h2>
				
				<label for="p_id">ID:</label><br>
				<input type="text" name="p_id" id="p_id" readonly required value="<%=producto.getId()%>"><br>
				
				<label for="p_prod">Producto:</label><br>
				<input type="text" name="p_prod" id="p_prod" maxlength="45" required value="<%=producto.getProducto()%>"><br>
				
				<label for="p_precio">Precio</label><br>
				<input type="number" name="p_precio" id="p_precio" min="0" step ="0.01" required value="<%=producto.getPrecio()%>"><br>
				
				<label for="p_fk_categoria">Categoria</label><br>
				<select name="p_fk_categoria" id="p_fk_categoria">
					<%for(Categoria elem: listaCategorias) {
						if(elem.getId()!=producto.getFk_categoria()){
							//mostrar el option sin selected%>
							<option value="<%=elem.getId()%>"><%=elem.getCategoria()%></option>
						<%
						}else{
							//ponerle un selected al option
						%>	
							<option selected value="<%=elem.getId()%>"><%=elem.getCategoria()%></option>	
					<%	}
					}%>
					
				</select><br>
				
<!-- 				<label for="p_foto">Producto:</label><br> -->
<!-- 				<input type="file" name="p_foto" id="p_foto"  required accept="image/*"><br> -->
				
				<input type="submit" value="Modificar" >
			</form>
		
		</section>
			
	
	</main>
	
	
	<%@ include file="includes/pie.jsp" %>

	
	




</body>
</html>