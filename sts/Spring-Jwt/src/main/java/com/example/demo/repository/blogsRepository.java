package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.blogs;

public interface blogsRepository extends JpaRepository<blogs,Integer>{
	public List<blogs> findAllByUsername(String username);
}
