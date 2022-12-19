package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.andresfot.blog.model.entity.Post;

public interface IPostDao extends JpaRepository<Post, Long>{
	
	@Query("SELECT p FROM Post p LEFT JOIN p.comentarios c WHERE p.id=?1")
	public Post fetchPostByIdWithComentarios(Long id);
	
}
