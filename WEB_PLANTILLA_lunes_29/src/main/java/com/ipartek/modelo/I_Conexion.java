package com.ipartek.modelo;

import java.sql.Connection;

public interface I_Conexion {
	
	//Constantes para BD
	String BASE_DATOS = "db_txomin_barullo";
	String DRIVER = "com.mysql.jdbc.Driver";
	String CONEXION = "jdbc:mysql://localhost:3306/"+BASE_DATOS;
	String USUARIO = "root";
	String PASS = "1234";
	
	
	//Archivos JSP
	String JSP_TODOS="WEB-INF/index.jsp";
	String JSP_BOCADILLOS="WEB-INF/bocadillos.jsp";
	String JSP_HAMBURGUESAS="WEB-INF/hamburguesas.jsp";
	String JSP_TALOS="WEB-INF/talos.jsp";
	
	String JSP_ADMIN="WEB-INF/admin.jsp";
	String JSP_FORM_MODIFICAR="WEB-INF/modificar.jsp";
	
	
	//Tablas y vistas de la BD
	String TABLA_PRODUCTOS="productos";
	String PRODUCTOS_ID="id";
	String PRODUCTOS_PRODUCTO="producto";
	String PRODUCTOS_PRECIO="precio";
	String PRODUCTOS_FK_CATEGORIA="FK_categoria";
	String PRODUCTOS_FOTO="foto";
	
	String TABLA_CATEGORIAS="categorias";
	String CATEGORIAS_ID="id";
	String CATEGORIAS_CATEGORIA="categoria";
	
	String VISTA_PRODUCTOS="v_productos";
	String V_PRODUCTOS_ID="id";
	String V_PRODUCTOS_PRODUCTO="producto";
	String V_PRODUCTOS_PRECIO="precio";
	String V_PRODUCTOS_FK_CATEGORIA="FK_categoria";
	String V_PRODUCTOS_FOTO="foto";
	String V_PRODUCTOS_CATEGORIA="categoria";
	
	
	
	
	

	//stored procedures
	String SP_OBTENER_TODOS_PRODUCTOS="call sp_obtener_todos_productos();";  
	String SP_OBTENER_PRODUCTOS_POR_FK="call sp_obtener_productos_por_FK(?);";
	String SP_INSERTAR_PRODUCTO="call sp_insertar_producto(?, ?, ?, ?);";
	String SP_OBTENER_TODAS_CATEGORIAS="call sp_obtener_todas_categorias();";
	String SP_OBTENER_PRODUCTO_POR_ID="call sp_obtener_producto_por_id(?);";
	String SP_MODIFICAR_PRODUCTO="call sp_modificar_producto(?, ?, ?, ?);";
	
	//atributos de la mochila
	String ATR_LISTA_PRODUCTOS="atr_lista_productos";
	String ATR_LISTA_CATEGORIAS="atr_lista_categorias";
	String ATR_PRODUCTO="atr_producto";
	
	
	
	
	
	
	
	
	

}
