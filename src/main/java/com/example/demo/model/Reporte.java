package com.example.demo.model;

public class Reporte {

	public String id;
	public String nombre;
	public String precio;
	
	public Reporte(String id, String nombre, String precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	
	
}
