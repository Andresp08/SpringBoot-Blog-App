package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.Comentario;

public interface IComentarioDao extends JpaRepository<Comentario, Long>{
	
	/*@Query("SELECT c FROM Comentario c LEFT JOIN c.post p WHERE c.id=?1")
	public List<Comentario> fetchComentariosByIdWithPost(Long id);*/
}
