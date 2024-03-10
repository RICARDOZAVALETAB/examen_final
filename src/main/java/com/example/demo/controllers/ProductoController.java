package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Empresa;
import com.example.demo.model.Producto;
import com.example.demo.service.EmpresaService;
import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/productos")
	public String getAllProduct(Model model) {
		List<Producto> lisProductos = productoService.getAllProducts();
		model.addAttribute("productos", lisProductos);
        return "productList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("empresas", empresaService.getAllEmpresas());
		return "productRegister";
	}
	
	
	@PostMapping("/register")
	public String createProduct(@RequestParam("name") String name, 
			@RequestParam("precio") BigDecimal precio,
			@RequestParam("id") Long id, Model model) {
		
		Producto producto = new Producto();
		producto.nombre = name;
		producto.precio = precio;
		
		Empresa empresa = empresaService.getEmpresaById(id);

		producto.empresa = empresa;
		
		productoService.createProducto(producto);
		
		model.addAttribute("productos", productoService.getAllProducts());
		model.addAttribute("empresas", empresaService.getAllEmpresas());
		
		return "productList";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Producto producto = productoService.getProductoByID(id);
		
		model.addAttribute("producto", producto);
		model.addAttribute("empresas", empresaService.getAllEmpresas());
		
		return "productEdit";
	}
	
	
	@PostMapping("/edit")
	public String createProduct(@RequestParam("id") Long id, @RequestParam("name") String name, 
			@RequestParam("precio") BigDecimal precio,
			@RequestParam("idEmpresa") Long idEmpresa, Model model) {
		
		Producto producto = productoService.getProductoByID(id);
		producto.nombre = name;
		producto.precio = precio;
		
		Empresa empresa = empresaService.getEmpresaById(idEmpresa);

		producto.empresa = empresa;
		
		productoService.createProducto(producto);
		
		model.addAttribute("productos", productoService.getAllProducts());
		model.addAttribute("empresas", empresaService.getAllEmpresas());
		
		return "productList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		productoService.deleteProduct(id);
		
		model.addAttribute("productos", productoService.getAllProducts());
		model.addAttribute("empresas", empresaService.getAllEmpresas());
		
		return "productList";
	}
	
	
	
}







