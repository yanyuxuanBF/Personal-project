package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.medol.Blog;
import com.example.demo.medol.User;
import com.example.demo.repository.BlogRepository;

@Service
public class BlogService {
	@Autowired 
	private BlogRepository blogRepository;

	public List<Blog> findBlogsByUserId(Long userId) {
		return blogRepository.findByUserId(userId);
	}

	public Blog addBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	public List<Blog> findBlogsByUsername(String username) {

		return blogRepository.findByUsername(username);
	}

}
