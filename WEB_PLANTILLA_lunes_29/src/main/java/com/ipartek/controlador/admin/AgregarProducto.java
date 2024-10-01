package com.ipartek.controlador.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.I_Conexion;
import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;


@WebServlet("/AgregarProducto")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 2,      // 2 MB
		  maxRequestSize = 1024 * 1024 * 10   // 10 MB
		)
public class AgregarProducto extends HttpServlet implements I_Conexion{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		//guardado de la imagen
		Part filePart = request.getPart("p_foto");
		String fileName = filePart.getSubmittedFileName();
		
		
		//1 recepcion de parametros
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
		
		String foto="";
		if (fileName!=null) {
			foto=fileName;
		}
		
		
		if (foto!="" && fk_categoria!=0 && precio!=0.0 && producto!="" ) {
			for (Part part : request.getParts()) {
				part.write("C:\\Users\\Java\\Desktop\\WS_Java\\WEB_PLANTILLA\\src\\main\\webapp\\imagenes\\" +fileName);
			
			
			
			}
		}
		
		//2 maquetar a objetos
		Producto producInsertar= new Producto(0, producto, precio, fk_categoria, foto);
		
		//3
		DB_Helper db = new DB_Helper();
		Connection con = db.conectar();

		//4
		int resultadoInsert=db.insertarProducto(con, producInsertar);
		List<Categoria> listaCategorias=db.obtenerTodasCategorias(con);
		List<V_Producto> listaProductos=db.obtenerTodosProductos(con);
		
		//5
		db.desconectar(con);
		
		//6
		request.setAttribute(ATR_LISTA_CATEGORIAS, listaCategorias);
		request.setAttribute(ATR_LISTA_PRODUCTOS, listaProductos);
		//7 viaje
		request.getRequestDispatcher(JSP_ADMIN).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
