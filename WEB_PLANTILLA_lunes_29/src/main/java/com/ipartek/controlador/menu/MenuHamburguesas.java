package com.ipartek.controlador.menu;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;

@WebServlet("/MenuHamburguesas")
public class MenuHamburguesas extends HttpServlet implements I_Conexion {
	private static final long serialVersionUID = 1L;

	public MenuHamburguesas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		List<V_Producto> listaProductos = db.obtenerProductosPorFKCategoria(con, 2);

		db.desconectar(con);

		request.setAttribute(ATR_LISTA_PRODUCTOS, listaProductos);

		request.getRequestDispatcher(JSP_HAMBURGUESAS).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
