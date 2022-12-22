package com.co.andresfot.blog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresfot.blog.model.entity.UserLogin;

public interface IUserDao extends JpaRepository<UserLogin, Long>{
	public UserLogin findByUsername(String username);
}
