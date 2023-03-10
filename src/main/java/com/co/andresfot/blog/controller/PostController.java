package com.co.andresfot.blog.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.andresfot.blog.model.dao.IPostDao;
import com.co.andresfot.blog.model.entity.Categoria;
import com.co.andresfot.blog.model.entity.Comentario;
import com.co.andresfot.blog.model.entity.Post;
import com.co.andresfot.blog.model.entity.UserLogin;
import com.co.andresfot.blog.model.service.ICategoriaService;
import com.co.andresfot.blog.model.service.IComentarioService;
import com.co.andresfot.blog.model.service.IPostService;
import com.co.andresfot.blog.model.service.IUploadFileService;
import com.co.andresfot.blog.model.service.IUserService;

@Controller
@RequestMapping({ "", "/", "index" })
@SessionAttributes("post")
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private IPostDao postDao;

	@Autowired
	private ICategoriaService categoriaService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private IComentarioService comentarioService;

	@GetMapping("")
	public String trendingPosts(Model model) {

		List<Post> posts = postService.findAllPosts();
		List<Categoria> categorias = categoriaService.findAllCategorias();

		//ultimos posts
		List<Post> listadoPosts = postDao.findAll(PageRequest.of(0, 3, Sort.by("fechaCreacion")
				.and(Sort.by("status"))
				.descending())).toList();

		model.addAttribute("tituloTrending", "Trending Posts");
		model.addAttribute("posts", posts);
		model.addAttribute("categorias", categorias);

		model.addAttribute("listadoPosts", listadoPosts);

		return "index";
	}

	@GetMapping("/listado-posts")
	public String listadoPosts(Model model) {
		List<Post> posts = postService.findAllPosts();

		model.addAttribute("titulo", "Listado de posts");
		model.addAttribute("posts", posts);

		return "posts/listado-posts";
	}

	@GetMapping("/posts-categoria/{id}")
	public String listarPostPorCategoria(@PathVariable Long id, Model model) {

		Categoria tituloPost = categoriaService.findCategoriaById(id);

		//post por categoria
		List<Post> listadoPosts = postService.findPostByCategoria(id);

		model.addAttribute("listadoPosts", listadoPosts);
		model.addAttribute("titulo", tituloPost.getCategoria());

		return "posts/post-categoria";
	}

	@GetMapping("/nuevo-post")
	public String crearPost(Model model) {

		List<Categoria> categorias = categoriaService.findAllCategorias();
		List<UserLogin> usuarios = userService.findAllUsers();

		model.addAttribute("titulo", "Crear nuevo post");
		model.addAttribute("post", new Post());
		model.addAttribute("categorias", categorias);
		model.addAttribute("usuarios", usuarios);
		return "posts/crear-post";
	}

	@PostMapping("/nuevo-post")
	public String guardarPost(@Valid Post post, BindingResult result, Model model,
			@RequestParam(name = "file") MultipartFile foto, @RequestParam(name = "categoria") Long idCategoria,
			@RequestParam(name = "usuario") Long idUsuario, SessionStatus status, RedirectAttributes flash) {

		List<Categoria> categorias = categoriaService.findAllCategorias();
		List<UserLogin> usuarios = userService.findAllUsers();

		Categoria categoria = categoriaService.findCategoriaById(idCategoria);
		UserLogin usuario = userService.findUserById(idUsuario);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear nuevo post");
			model.addAttribute("categorias", categorias);
			model.addAttribute("usuarios", usuarios);
			return "posts/crear-post";
		}

		if (!foto.isEmpty()) {

			if (post.getId() != null && post.getId() > 0 && post.getImagen() != null && post.getImagen().length() > 0) {
				uploadFileService.delete(post.getImagen());
			}

			String uniqueFileName = null;

			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName + "'");
			post.setImagen(uniqueFileName);
		}

		post.setCategoria(categoria);
		post.setUser(usuario);

		if (post.isStatus()) {
			post.setStatus(true);
		} else {
			post.setStatus(false);
		}

		postService.savePost(post);
		status.setComplete();
		flash.addFlashAttribute("success", "Post guardado con exito!!");

		return "redirect:/index";
	}

	@GetMapping("/uploads/{filename:.+}")
	public ResponseEntity<Resource> verImagenBlog(@PathVariable String filename) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping("/leer-post/{id}")
	public String leerPostPorId(@PathVariable Long id, Model model, RedirectAttributes flash) {

		Post post = null;

		Post comentarios = postService.findPostByIdWithComentarios(id);

		List<Categoria> categorias = categoriaService.findAllCategorias();
		
		List<Post> postsPopulares = postService.findDistintcPostsById(id); 

		if (id > 0) {
			post = postService.findPostById(id);

			if (post == null) {
				flash.addFlashAttribute("error", "El post no existe en la BBDD!!");
				return "redirect:/index";
			}
		} else {
			flash.addFlashAttribute("error", "El post no existe en la BBDD!!");
			return "redirect:/index";
		}

		model.addAttribute("titulo", post.getTitulo());
		model.addAttribute("post", post);
		model.addAttribute("comentario", comentarios);
		model.addAttribute("categorias", categorias);
		model.addAttribute("postsPopulares", postsPopulares);

		return "/posts/detalle-post";
	}

	@PostMapping("/comentario-post")
	public String comentarioPost(@RequestParam(name = "post", required = false) Long postId,
			@Valid Comentario comentario, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Agregar nuevo comentario");
			model.addAttribute("comentario", comentario);

			return "redirect:/comentarios/nuevo-comentario";
		}

		if (postId == null) {
			model.addAttribute("titulo", "Agregar nuevo comentario");
			model.addAttribute("error", "Error: el id no puede ser cero");
			return "redirect:/comentarios/nuevo-comentario";
		}

		Post post = postService.findPostById(postId);
		comentario.setPost(post);

		comentarioService.saveComentario(comentario);

		return "redirect:/index";
	}

	@GetMapping("/editar-post/{id}")
	public String editarAutor(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Post post = null;
		List<Categoria> categorias = categoriaService.findAllCategorias();
		List<UserLogin> usuarios = userService.findAllUsers();

		if (id > 0) {
			post = postService.findPostById(id);

			if (post == null) {
				flash.addFlashAttribute("error", "El post no existe en la BBDD!!");
				return "redirect:/index";
			}
		} else {
			flash.addFlashAttribute("error", "El post no existe en la BBDD!!");
			return "redirect:/index";
		}

		model.addAttribute("titulo", "Editar Post");
		model.addAttribute("post", post);
		model.addAttribute("categorias", categorias);
		model.addAttribute("usuarios", usuarios);

		return "posts/crear-post";
	}

	@GetMapping("/eliminar-post/{id}")
	public String eliminarPrestamo(@PathVariable Long id, RedirectAttributes flash) {

		if (id > 0) {
			postService.deletePostById(id);
			flash.addFlashAttribute("success", "Post eliminado con exito");
		}

		return "redirect:/listado-posts";
	}

}
