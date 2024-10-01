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

@WebServlet("/BorrarProducto")
public class BorrarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       
 
    public BorrarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1
		int p_id=0;
		if(request.getParameter("p_id")!=null) {
			try {
				p_id=Integer.parseInt(request.getParameter("p_id"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				p_id=0;
			}
		}
		
		//2
		

		//3
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();
		
		//4
		int resultadoBorrar= db.borrarProductoPorId(con, p_id);
		
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		List<V_Producto> listaProductos=db.obtenerTodosProductos(con);
		
		//5 
		db.desconectar(con);
		
		//6
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		request.setAttribute(ATR_LISTA_PRODUCTOS, listaProductos);
		
		//7
		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
	
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
