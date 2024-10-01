package com.ipartek.modelo.dto;

public class Producto {

	private int id;
	private String producto;
	private double precio;
	private int fk_categoria;
	private String foto;
	
	public Producto(int id, String producto, double precio, int fk_categoria, String foto) {
		super();
		this.id = id;
		this.producto = producto;
		this.precio = precio;
		this.fk_categoria = fk_categoria;
		this.foto = foto;
	}
	
	public Producto() {
		super();
		this.id = 0;
		this.producto = "";
		this.precio = 0.0;
		this.fk_categoria = 0;
		this.foto = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getFk_categoria() {
		return fk_categoria;
	}

	public void setFk_categoria(int fk_categoria) {
		this.fk_categoria = fk_categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", producto=" + producto + ", precio=" + precio + ", fk_categoria=" + fk_categoria
				+ ", foto=" + foto + "]";
	}
	
	
	

}
