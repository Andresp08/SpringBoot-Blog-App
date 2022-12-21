package com.co.andresfot.blog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresfot.blog.model.dao.IPostDao;
import com.co.andresfot.blog.model.entity.Post;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDao postDao;

	@Override
	@Transactional(readOnly = true)
	public List<Post> findAllPosts() {
		return postDao.findAll();
	}

	@Override
	@Transactional
	public void savePost(Post post) {
		postDao.save(post);
	}

	@Override
	@Transactional(readOnly = true)
	public Post findPostById(Long id) {
		return postDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deletePostById(Long id) {
		postDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Post findPostByIdWithComentarios(Long id) {
		return postDao.fetchPostByIdWithComentarios(id);
	}

	@Override
	public List<Post> findPostByCategoria(Long id) {
		return postDao.fetchPostByCategory(id);
	}

	@Override
	public List<Post> findDistintcPostsById(Long id) {
		return postDao.findDistinctPostsById(id);
	}

}
