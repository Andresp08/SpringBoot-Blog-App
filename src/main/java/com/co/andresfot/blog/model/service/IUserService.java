package com.co.andresfot.blog.model.service;

import java.util.List;

import com.co.andresfot.blog.model.entity.UserLogin;

public interface IUserService {

	public List<UserLogin> findAllUsers();
	
	public void saveUser(UserLogin user);
	
	public UserLogin findUserById(Long id);
	
	public void deleteUserById(Long id);
	
}
