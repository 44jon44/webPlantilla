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
</head>
<body>
	
	<%@ include file="includes/cabecera_admin.jsp" %>
	
	<%@ include file="includes/menu_admin.jsp" %>
	
	
	<main>
		<section>
			
		
			<form method="post" action="AgregarProducto" enctype="multipart/form-data">
				<h2>Insertar producto</h2>
				
				<label for="p_prod">Producto:</label><br>
				<input type="text" name="p_prod" id="p_prod" maxlength="45" required><br>
				
				<label for="p_precio">Precio</label><br>
				<input type="number" name="p_precio" id="p_precio" min="0" step ="0.01" required><br>
				
				<label for="p_fk_categoria">Categoria</label><br>
				<select name="p_fk_categoria" id="p_fk_categoria">
					<%for(Categoria elem: listaCategorias) {%>
						<option value="<%=elem.getId()%>"><%=elem.getCategoria()%></option>
					<%}%>
				</select><br>
				
				<label for="p_foto">Producto:</label><br>
				<input type="file" name="p_foto" id="p_foto"  required accept="image/*"><br>
				
				<input type="submit" value="Agregar" >
			</form>
		
		</section>
			<form action="BorrarProductoMultiple" method="Get">
				<table>
					<thead>
						<th>
							<input type="submit" value="Borrar seleccion">
						</th>
						<th>ID</th>
						<th>Producto</th>
						<th>Precio</th>
						<th>Categoria</th>
						<th>Foto</th>
						<th>Opciones</th>
					</thead>
					
					<tbody>
					
					<%for(V_Producto elem: listaProductos) {%>
						<tr>
							<td>
							<input type="checkbox" value="<%=elem.getId()%>" name="p_id">
							</td>
							<td><%=elem.getId()%></td>
							<td><%=elem.getProducto()%></td>
							<td><%=elem.getPrecio() %></td>
							<td><%=elem.getCategoria()%></td>
							<td>
								<img alt="" src="imagenes/<%=elem.getFoto()%>" height="48px" >
							</td>
							<td>
								<a href="BorrarProducto?p_id=<%=elem.getId()%>">Borrar</a>
								<a href="FrmModificarProducto?p_id=<%=elem.getId()%>">Modificar</a>
							</td>
						<tr>
					<%}%>
					
					</tbody>
				
				
				</table>
			</form>
		<section>
		
		
		
		</section>
	
	</main>
	
	
	<%@ include file="includes/pie.jsp" %>

	
	




</body>
</html>