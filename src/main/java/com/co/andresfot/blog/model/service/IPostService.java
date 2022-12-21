package com.co.andresfot.blog.model.service;

import java.util.List;

import com.co.andresfot.blog.model.entity.Post;

public interface IPostService {

public List<Post> findAllPosts();
	
	public void savePost(Post post);
	
	public Post findPostById(Long id);
	
	public Post findPostByIdWithComentarios(Long id);
	
	public List<Post> findPostByCategoria(Long id);
	
	public List<Post> findDistintcPostsById(Long id);
	
	public void deletePostById(Long id);
	
}
