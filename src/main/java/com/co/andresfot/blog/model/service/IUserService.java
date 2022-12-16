package com.co.andresfot.blog.model.service;

import java.util.List;

import com.co.andresfot.blog.model.entity.User;

public interface IUserService {

	public List<User> findAllUsers();
	
	public void saveUser(User user);
	
	public User findUserById(Long id);
	
	public void deleteUserById(Long id);
	
}
