package com.ipartek.modelo.dto;

public class V_Producto extends Producto {
	
	
	private String categoria;

	public V_Producto(int id, String producto, double precio, int fk_categoria, String foto, String categoria) {
		super(id, producto, precio, fk_categoria, foto);
		this.categoria = categoria;
	}
	
	public V_Producto() {
		super();
		this.categoria = "";
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "V_Producto [categoria=" + categoria + ", toString()=" + super.toString() + "]";
	}


	
	
	
	
	
	
	
	
}
