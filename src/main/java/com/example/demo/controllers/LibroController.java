package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibros(Model model) {
		List<Libro> lisLibros = libroService.getAllLibro();
		model.addAttribute("libros", lisLibros);
        return "libroList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("genero", generoService.getAllGeneros());
		return "libroRegister";
	}
	
	
	@PostMapping("/register")
	public String createLibro(@RequestParam("name") String name, 
			@RequestParam("autor") String autor,
			@RequestParam("fecha") String fecha,
			@RequestParam("id") Long id, Model model) {
		
		
		Libro libro = new Libro();
		libro.nombre = name;
		libro.autor = autor;
		libro.fecha = fecha;
		
		Genero genero = generoService.getGeneroById(id);

		libro.genero = genero;
		
		libroService.createLibro(libro);
		
	
		List<Libro> lisLibros = libroService.getAllLibro();
		model.addAttribute("libros", lisLibros);
        return "libroList";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Libro libro = libroService.getLibroByID(id);
		
		model.addAttribute("libros", libro);
		model.addAttribute("genero", generoService.getAllGeneros());
		
		return "librosEdit";
	}
	
	
	@PostMapping("/edit")
	public String createLibro(@RequestParam("id") Long id, @RequestParam("name") String name, 
			@RequestParam("autor") String autor,
			@RequestParam("fecha") String fecha,
			@RequestParam("idGenero") Long idGenero, Model model) {
		
		Libro libro = libroService.getLibroByID(id);
		libro.nombre = name;
		libro.autor = autor;
		
		Genero genero = generoService.getGeneroById(idGenero);

		libro.genero = genero;
		
		libroService.createLibro(libro);
		
		model.addAttribute("libros", libroService.getAllLibro());
		model.addAttribute("genero", generoService.getAllGeneros());
		
		return "libroList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable Long id, Model model) {
		libroService.deleteLibro(id);
		
		model.addAttribute("libros", libroService.getAllLibro());
		model.addAttribute("genero", generoService.getAllGeneros());
		
		return "libroList";
	}
	
	
	
}
