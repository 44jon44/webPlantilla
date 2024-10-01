package com.ipartek.modelo;

import java.sql.Connection;
import java.util.List;

import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;

public interface I_Metodos {

	
	public Connection conectar();
	public void desconectar(Connection con);
	
	
	public List<V_Producto> obtenerTodosProductos(Connection con);
	int insertarProducto(Connection con, Producto producInsertar);
	

	
}
