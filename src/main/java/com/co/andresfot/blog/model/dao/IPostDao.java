package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.Post;

public interface IPostDao extends JpaRepository<Post, Long>{

}
