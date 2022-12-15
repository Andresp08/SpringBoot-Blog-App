package com.co.andresfot.blog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresfot.blog.model.dao.IComentarioDao;
import com.co.andresfot.blog.model.entity.Comentario;

@Service
public class ComentarioServiceImpl implements IComentarioService {
	
	@Autowired
	private IComentarioDao comentarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comentario> findAllComentarios() {
		return comentarioDao.findAll();
	}

	@Override
	@Transactional
	public void saveComentario(Comentario comentario) {
		comentarioDao.save(comentario);
	}

	@Override
	@Transactional(readOnly = true)
	public Comentario findComentarioById(Long id) {
		return comentarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteComentarioById(Long id) {
		comentarioDao.deleteById(id);
	}

}
