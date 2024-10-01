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

@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       
    public ModificarProducto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 recepcion de parametros
		
		int id=0;
		if(request.getParameter("p_id")!=null) {
			try {
				id=Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				id=0;
			}
		}
		
		String producto="";
		if(request.getParameter("p_prod")!=null) {
			producto=request.getParameter("p_prod");
			if (producto.length()>45) {
				producto=producto.substring(0, 45);
			}
		}
		
		double precio=0.0;
		if(request.getParameter("p_precio")!=null) {
			try {
				precio=Double.parseDouble(request.getParameter("p_precio"));
			} catch (NumberFormatException e) {
				precio=0.0;
			}
		}
		
		int fk_categoria=0;
		if(request.getParameter("p_fk_categoria")!=null) {
			try {
				fk_categoria=Integer.parseInt(request.getParameter("p_fk_categoria"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				fk_categoria=0;
			}
		}
	
		
		Producto product= new Producto(id, producto, precio, fk_categoria, "");
		
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		int resultadoModificar= db.modificarProducto(con, product);
		
		
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		List<V_Producto> listaProductos=db.obtenerTodosProductos(con);
				
		db.desconectar(con);
		
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		request.setAttribute(ATR_LISTA_PRODUCTOS, listaProductos);
		
		
		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
