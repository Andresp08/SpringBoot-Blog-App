package com.co.andresfot.blog.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresfot.blog.model.dao.IUserDao;
import com.co.andresfot.blog.model.entity.UserLogin;
import com.co.andresfot.blog.model.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<UserLogin> findAllUsers() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void saveUser(UserLogin user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserLogin findUserById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public UserLogin findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public Optional<UserLogin> findFirstByUsername(String username) {
		return userDao.findFirstByUsername(username);
	}

}
