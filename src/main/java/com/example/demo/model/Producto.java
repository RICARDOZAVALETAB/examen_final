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
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long id;
	
	@Column(name = "nombre", nullable = false)
	public String nombre;
	
	@Column(name = "precio", nullable = false)
	public BigDecimal precio;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa", nullable = false)
	public Empresa empresa;

	
}
