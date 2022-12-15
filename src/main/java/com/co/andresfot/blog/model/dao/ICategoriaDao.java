package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.Categoria;

public interface ICategoriaDao extends JpaRepository<Categoria, Long>{

}
