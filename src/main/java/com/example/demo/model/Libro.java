package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

	
	@Entity
	@Table(name = "libro")
	public class Libro {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		public Long id;
		
		@Column(name = "nombre", nullable = false)
		public String nombre;
		
		@Column(name = "autor", nullable = false)
		public String autor;

		@Column(name = "fecha", nullable = false)
		public String fecha;
		
		@ManyToOne
		@JoinColumn(name = "idGenero", nullable = false)
		public Genero genero;

		public Libro() {
		}

		public Libro(String nombre, String autor, String fecha, Genero genero) {
			this.nombre = nombre;
			this.autor = autor;
			this.fecha = fecha;
			this.genero = genero;
		}
		
	}
	
	
	
	
	

	

