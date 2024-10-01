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
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;

/**
 * Servlet implementation class FrmModificarProducto
 */
@WebServlet("/FrmModificarProducto")
public class FrmModificarProducto extends HttpServlet implements I_Conexion {
	private static final long serialVersionUID = 1L;

	public FrmModificarProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1
		int p_id = 0;
		if (request.getParameter("p_id") != null) {
			try {
				p_id = Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				p_id = 0;
			}
		}
		
		
		//3
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		//4
		Producto producto = db.obtenerProductosPorId(con, p_id);
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		
		//5
		db.desconectar(con);
		
		//6
		request.setAttribute(ATR_PRODUCTO, producto);
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);

		request.getRequestDispatcher(JSP_FORM_MODIFICAR).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
