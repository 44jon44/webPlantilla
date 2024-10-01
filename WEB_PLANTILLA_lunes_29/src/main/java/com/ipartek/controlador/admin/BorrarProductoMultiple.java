package com.ipartek.controlador.admin;

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
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.V_Producto;

@WebServlet("/BorrarProductoMultiple")
public class BorrarProductoMultiple extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;

	public BorrarProductoMultiple() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int[] p_id = {};
		if (request.getParameterValues("p_id") != null) {
			try {
				String[] p_idArrayString = request.getParameterValues("p_id");

				p_id = new int[p_idArrayString.length];

				for (int i = 0; i < p_idArrayString.length; i++) {
					p_id[i] = Integer.parseInt(p_idArrayString[i]);
				}
			} catch (NumberFormatException e) {

				p_id = new int[0];
			}
		}

		// 3
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		// 4
		
		for (int i : p_id) {
			int resultadoBorrar = db.borrarProductoPorId(con, i);	
		}
		
		List<Categoria> listaCategorias = db.obtenerTodasCategorias(con);
		List<V_Producto> listaProductos = db.obtenerTodosProductos(con);

		// 5
		db.desconectar(con);

		// 6
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		request.setAttribute(ATR_LISTA_PRODUCTOS, listaProductos);

		// 7
		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
