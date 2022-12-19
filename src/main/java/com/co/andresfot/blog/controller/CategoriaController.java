package com.co.andresfot.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.andresfot.blog.model.entity.Categoria;
import com.co.andresfot.blog.model.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
@SessionAttributes("categoria")
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;
	
	@GetMapping("/listado-categorias")
	public String listarCategorias(Model model) {
		
		List<Categoria> categorias = categoriaService.findAllCategorias();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("titulo", "Listado de categorías");
		
		return "categorias/listado-categorias";
	}
	
	@GetMapping("/nueva-categoria")
	public String nuevaCategoria(Model model) {
		
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("titulo", "Agregar nueva categoria");
		
		return "categorias/nueva-categoria";
	}
	
	@PostMapping("/nueva-categoria")
	public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model model, 
			SessionStatus status, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("categoria", "categoria");
			model.addAttribute("titulo", "Agregar nueva categoria");
			return "categorias/nueva-categoria";
		}
		
		categoriaService.saveCategoria(categoria);
		status.setComplete();
		flash.addFlashAttribute("success", "Categoria creada con exito!!");
		
		return "redirect:/categorias/listado-categorias";
		
	}
	
	@GetMapping("/editar-categoria/{id}")
	public String editarCategoria(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		Categoria categoria = null;
		
		if(id > 0) {
			categoria = categoriaService.findCategoriaById(id);
			
			if(categoria == null) {
				flash.addFlashAttribute("error", "La categoría no existe en la BBDD!!");
				return "redirect:/categorias/listado-categorias";
			}
		} else {
			flash.addFlashAttribute("error", "La categoría no existe en la BBDD!!");
			return "redirect:/categorias/listado-categorias";
		}
		
		model.addAttribute("categoria", categoria);
		return "categorias/nueva-categoria";
	}
	
	@GetMapping("/eliminar-categoria/{id}")
	public String eliminarCategoria(@PathVariable Long id,RedirectAttributes flash) {
		
		if(id > 0) {
			categoriaService.deleteCategoriaById(id);
			flash.addFlashAttribute("success", "La categoría fue eliminada con exito!!");
		} else {
			flash.addFlashAttribute("error", "La categoría no existe en la BBDD!!");
			return "redirect:/categorias/listado-categorias";
		}
		
		return "redirect:/categorias/listado-categorias";
	}
	
}
