package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

	List<Blog> findByUserId(Long userId);

	List<Blog> findByUsername(String username);

	void deleteById(Long userId);
}
