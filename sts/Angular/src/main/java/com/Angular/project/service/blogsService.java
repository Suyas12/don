package com.Angular.project.service;

import com.Angular.project.Api.blogs;

import java.util.List;

public interface blogsService {

	List<blogs> findAll();

    blogs findById(int theId);

    blogs save(blogs theblog);

    void deleteById(int theId);
    
}
