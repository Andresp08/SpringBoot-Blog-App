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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.andresfot.blog.model.entity.Comentario;
import com.co.andresfot.blog.model.entity.Post;
import com.co.andresfot.blog.model.service.IComentarioService;
import com.co.andresfot.blog.model.service.IPostService;

@Controller
@RequestMapping("/comentarios")
@SessionAttributes("comentario")
public class ComentarioController {

	@Autowired
	private IComentarioService comentarioService;

	@Autowired
	private IPostService postService;

	@GetMapping("/listado-comentarios")
	public String listadoComentarios(Model model) {
		List<Comentario> comentarios = comentarioService.findAllComentarios();

		model.addAttribute("titulo", "Listado de comentarios");
		model.addAttribute("comentarios", comentarios);

		return "/comentarios/listado-comentarios";
	}

	@GetMapping("/nuevo-comentario")
	public String nuevoComentario(Model model) {

		List<Post> posts = postService.findAllPosts();

		model.addAttribute("titulo", "Agregar nuevo comentario");
		model.addAttribute("comentario", new Comentario());
		model.addAttribute("posts", posts);

		return "/comentarios/nuevo-comentario";
	}

	@PostMapping("/nuevo-comentario")
	public String guardarComentario(@RequestParam(name = "post", required = false) Long postId,
			@Valid Comentario comentario, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {

		List<Post> posts = postService.findAllPosts();
		
		if (result.hasErrors()) {
			model.addAttribute("comentario", comentario);
			model.addAttribute("posts", posts);

			return "/comentarios/nuevo-comentario";
		}

		Post post = postService.findPostById(postId);
		comentario.setPost(post);

		comentarioService.saveComentario(comentario);
		status.setComplete();
		flash.addFlashAttribute("success", "Comentario creado con exito!!");

		return "redirect:/comentarios/listado-comentarios";
	}
	
	@GetMapping("/editar-comentario/{id}")
	public String editarComentario(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		List<Post> posts = postService.findAllPosts();
		Comentario comentario = null;
		
		if(id > 0) {
			comentario = comentarioService.findComentarioById(id);
			
			if(comentario == null) {
				flash.addFlashAttribute("error", "El comentario no existe en la BBDD!!");
				return "redirect:/comentarios/listado-comentarios";
			}
		} else {
			flash.addFlashAttribute("error", "El comentario no existe en la BBDD!!");
			return "redirect:/comentarios/listado-comentarios";
		}
		
		model.addAttribute("titulo", "Editar Comentario");
		model.addAttribute("comentario", comentario);
		model.addAttribute("posts", posts);
		
		return "comentarios/nuevo-comentario";
	}

}
