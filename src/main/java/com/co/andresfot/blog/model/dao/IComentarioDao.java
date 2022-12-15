package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.Comentario;

public interface IComentarioDao extends JpaRepository<Comentario, Long>{

}
