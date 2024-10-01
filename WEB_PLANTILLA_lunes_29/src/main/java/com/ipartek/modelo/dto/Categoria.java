package com.ipartek.modelo.dto;

public class Categoria {
	
	private int id;
	private String categoria;
	
	public Categoria(int id, String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}
	
	public Categoria() {
		super();
		this.id = 0;
		this.categoria = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", categoria=" + categoria + "]";
	}
	
	
	

}
