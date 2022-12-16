package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.User;

public interface IUserDao extends JpaRepository<User, Long>{

}
