package com.co.andresfot.blog.model.service;

import java.util.List;

import com.co.andresfot.blog.model.entity.Comentario;

public interface IComentarioService {

	public List<Comentario> findAllComentarios();
	
	public void saveComentario(Comentario comentario);
	
	public Comentario findComentarioById(Long id);
	
	public void deleteComentarioById(Long id);
	
}
