package com.co.andresfot.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.andresfot.blog.model.entity.Categoria;
import com.co.andresfot.blog.model.entity.Post;
import com.co.andresfot.blog.model.entity.User;
import com.co.andresfot.blog.model.service.ICategoriaService;
import com.co.andresfot.blog.model.service.IPostService;
import com.co.andresfot.blog.model.service.IUserService;

@Controller
@RequestMapping({"", "/", "index"})
public class PostController {
	
	@Autowired
	private IPostService postService;
	
	@Autowired 
	private ICategoriaService categoriaService;
	
	@Autowired 
	private IUserService userService;
	
	@GetMapping("")
	public String trendingPosts(Model model) {
		 
		List<Post> posts = postService.findAllPosts();
		
		model.addAttribute("titulo", "Trending Posts");
		model.addAttribute("posts", posts);
		
		return "index";
	}
	
	@GetMapping("/nuevo-post")
	public String crearPost(Model model) {
		
		List<Categoria> categorias = categoriaService.findAllCategorias();
		List<User> usuarios = userService.findAllUsers();
		
		model.addAttribute("titulo", "Crear nuevo post");
		model.addAttribute("post", new Post());
		model.addAttribute("categorias", categorias);
		model.addAttribute("usuarios", usuarios);
		return "posts/crear-post";
	}
	
	@PostMapping("/nuevo-post")
	public String guardarPost(@Valid Post post, BindingResult result, Model model, 
			@RequestParam(name= "file") MultipartFile foto, 
			@RequestParam(name = "categoria") Long idCategoria,
			@RequestParam(name = "usuario") Long idUsuario,
			RedirectAttributes flash) {
		
		List<Categoria> categorias = categoriaService.findAllCategorias();
		List<User> usuarios = userService.findAllUsers();
		
		Categoria categoria = categoriaService.findCategoriaById(idCategoria);
		User usuario = userService.findUserById(idUsuario);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear nuevo post");
			model.addAttribute("categorias", categorias);
			model.addAttribute("usuarios", usuarios);
			return "posts/crear-post";
		}
		
		if(!foto.isEmpty()) {
			Path directorioRecursos = Paths.get("src//main//resources//static/uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Has subido correctamente " + foto.getOriginalFilename());
				
				post.setImagen(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		post.setCategoria(categoria);
		post.setUser(usuario);
		
		if(post.isStatus()) {
			post.setStatus(true);
		} else {
			post.setStatus(false);
		}
		
		postService.savePost(post);
		flash.addFlashAttribute("success", "Post guardado con exito!!");
		
		return "redirect:/index";
	}
	
}
