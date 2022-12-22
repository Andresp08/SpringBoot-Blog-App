package com.co.andresfot.blog.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresfot.blog.model.dao.ICategoriaDao;
import com.co.andresfot.blog.model.entity.Categoria;
import com.co.andresfot.blog.model.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAllCategorias() {
		return categoriaDao.findAll();
	}

	@Override
	@Transactional
	public void saveCategoria(Categoria categoria) {
		categoriaDao.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findCategoriaById(Long id) {
		return categoriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteCategoriaById(Long id) {
		categoriaDao.deleteById(id);
	}

}
