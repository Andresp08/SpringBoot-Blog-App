package com.co.andresfot.blog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresfot.blog.model.dao.IUserDao;
import com.co.andresfot.blog.model.entity.UserLogin;

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

}
