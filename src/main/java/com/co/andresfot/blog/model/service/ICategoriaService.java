package com.co.andresfot.blog.model.service;

import java.util.List;

import com.co.andresfot.blog.model.entity.Categoria;

public interface ICategoriaService {

	public List<Categoria> findAllCategorias();
	
	public void saveCategoria(Categoria categoria);
	
	public Categoria findCategoriaById(Long id);
	
	public void deleteCategoriaById(Long id);
	
}
