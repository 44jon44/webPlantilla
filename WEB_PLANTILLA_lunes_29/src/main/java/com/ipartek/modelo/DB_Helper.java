package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.modelo.dto.Categoria;
import com.ipartek.modelo.dto.Producto;
import com.ipartek.modelo.dto.V_Producto;


public class DB_Helper implements I_Conexion, I_Metodos {

	@Override
	public Connection conectar() {
		
		Connection con= null;
		
		try {
			Class.forName(DRIVER);
			con= DriverManager.getConnection(CONEXION, USUARIO ,PASS );
			
			System.out.println("BD conectada");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se encontro el driver");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se pudo conectar a la BD");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE BD");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}
		
		return con;
	}
	
	
	
	@Override
	public void desconectar(Connection con) {
		
		try {
			con.close();
			System.out.println("BD desconectada");
		} catch (SQLException e) {
			System.out.println("ERROR DE BD");
			System.out.println("No se pudo desconectar de la BD");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR DE BD");
			System.out.println("Error desconocido");
			System.out.println(e.getMessage());
		}
	}


	@Override
	public List<V_Producto> obtenerTodosProductos(Connection con) {
		
		List<V_Producto> lista = new ArrayList<V_Producto>();
		
		try {
			CallableStatement cStmt = con.prepareCall(VISTA_PRODUCTOS);
			
			boolean tieneSelect = cStmt.execute();
			
			if (tieneSelect==true) {
				//cargar la lista
				
				ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {	
					V_Producto prod=new V_Producto();
					
					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setProducto(rs.getString(V_PRODUCTOS_PRODUCTO));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setFoto(rs.getString(V_PRODUCTOS_FOTO));
					prod.setCategoria(rs.getString(V_PRODUCTOS_CATEGORIA));
					
					lista.add(prod);
				}
				
				System.out.println("Lista de todos los Productos obtenida:");
				System.out.println(lista);
				
				return lista;
				
			}else {
				System.out.println("No se pudo obtener una lista de productos");
				System.out.println("El Stored procedure no tiene un RESULTSET");
				
				return new ArrayList<V_Producto>();	
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener la lista de todos los productos");
			System.out.println(e.getMessage());
			
			return new ArrayList<V_Producto>();
		}	
	}



	public List<V_Producto> obtenerProductosPorFKCategoria(Connection con, int fk_categ) {
		List<V_Producto> lista = new ArrayList<V_Producto>();
		
		try {
			CallableStatement cStmt = 
					con.prepareCall(SP_OBTENER_PRODUCTOS_POR_FK);
			
			cStmt.setInt(1, fk_categ);
			
			boolean tieneSelect = cStmt.execute();
			
			if (tieneSelect==true) {
				//cargar la lista
				
				ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {	
					V_Producto prod=new V_Producto();
					
					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setProducto(rs.getString(V_PRODUCTOS_PRODUCTO));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setFoto(rs.getString(V_PRODUCTOS_FOTO));
					prod.setCategoria(rs.getString(V_PRODUCTOS_CATEGORIA));
					
					
					lista.add(prod);
				}
				
				System.out.println("Lista de todos los Productos obtenida:");
				System.out.println(lista);
				
				return lista;
				
			}else {
				System.out.println("No se pudo obtener una lista de productos");
				System.out.println("El Stored procedure no tiene un RESULTSET");
				
				return new ArrayList<V_Producto>();	
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener la lista de todos los productos");
			System.out.println(e.getMessage());
			
			return new ArrayList<V_Producto>();
		}	
	}


	@Override
	public int insertarProducto(Connection con, Producto producInsertar) {
		
		try {
			//call sp_insertar_producto('prod', precio, fk_cat, 'foto');
			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_PRODUCTO);

			cStmt.setString(1, producInsertar.getProducto());
			cStmt.setDouble(2, producInsertar.getPrecio());
			cStmt.setInt(3, producInsertar.getFk_categoria());
			cStmt.setString(4, producInsertar.getFoto());
		
			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: INSERTAR");
			System.out.println("No se pudo insertar el producto");
			
			return 0;
		}
		
		
		
		
	}



	public List<Categoria> obtenerTodasCategorias(Connection con) {
		

		
		List<Categoria> lista = new ArrayList<Categoria>();
		
		try {
			CallableStatement cStmt = con.prepareCall(SP_OBTENER_TODAS_CATEGORIAS);
			
			boolean tieneSelect = cStmt.execute();
			
			if (tieneSelect==true) {
				//cargar la lista
				
				ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {	
					Categoria cat=new Categoria();
					
					cat.setId(rs.getInt(CATEGORIAS_ID));
					cat.setCategoria(rs.getString(CATEGORIAS_CATEGORIA));
					
					lista.add(cat);
				}
				
				System.out.println("Lista de todas las categorias obtenida:");
				System.out.println(lista);
				
				return lista;
				
			}else {
				System.out.println("No se pudo obtener una lista de categorias");
				System.out.println("El Stored procedure no tiene un RESULTSET");
				
				return new ArrayList<Categoria>();	
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener la lista de todas las categorias");
			System.out.println(e.getMessage());
			
			return new ArrayList<Categoria>();
		}	
	
		
		
		
	
	}



	public int borrarProductoPorId(Connection con, int p_id) {
		try {
			//call sp_borrar_producto(id);
			CallableStatement cStmt = con.prepareCall("call sp_borrar_producto(?);");

			cStmt.setInt(1,p_id);

			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: BORRAR");
			System.out.println("No se pudo borrar el producto");
			
			return 0;
		}
		
		
		
	}



	public V_Producto obtenerProductosPorId(Connection con, int p_id) {

		V_Producto prod = new V_Producto();
		
		try {
			CallableStatement cStmt = 
					con.prepareCall(SP_OBTENER_PRODUCTO_POR_ID);
			
			cStmt.setInt(1, p_id);
			
			boolean tieneSelect = cStmt.execute();
			
			if (tieneSelect==true) {
				
				ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {	
					prod.setId(rs.getInt(V_PRODUCTOS_ID));
					prod.setProducto(rs.getString(V_PRODUCTOS_PRODUCTO));
					prod.setPrecio(rs.getDouble(V_PRODUCTOS_PRECIO));
					prod.setFk_categoria(rs.getInt(V_PRODUCTOS_FK_CATEGORIA));
					prod.setFoto(rs.getString(V_PRODUCTOS_FOTO));
					prod.setCategoria(rs.getString(V_PRODUCTOS_CATEGORIA));
				}
				
				System.out.println("Producto por ID obtenido:");
				
				return prod;
				
			}else {
				System.out.println("No se pudo obtener el producto por ID ");
				System.out.println("El Stored procedure no tiene un RESULTSET");
				
				return new V_Producto();	
			}

		} catch (SQLException e) {
			System.out.println("ERROR DE BD: CONSULTA");
			System.out.println("Error al obtener el producto por ID");
			
			return new V_Producto();
		}	
	
	}



	public int modificarProducto(Connection con, Producto product) {
		
		try {
			
			CallableStatement cStmt = con.prepareCall(SP_MODIFICAR_PRODUCTO);

			cStmt.setInt(1, product.getId());
			cStmt.setString(2, product.getProducto());
			cStmt.setDouble(3, product.getPrecio());
			cStmt.setInt(4, product.getFk_categoria());
			
			System.out.println(product.toString());
			return cStmt.executeUpdate();	
		} catch (SQLException e) {
			System.out.println("ERROR DE BD: MODIFICAR");
			System.out.println("No se pudo Modificar el producto");
			
			return 0;
		}
		
	}
}
