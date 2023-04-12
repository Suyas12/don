package com.Angular.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.project.Api.blogs;
import com.Angular.project.dao.blogsRepository;

@Service
public class blogsServiceImpl implements blogsService{
	
	private blogsRepository BlogsRepository;
	
	@Autowired
	public blogsServiceImpl(blogsRepository theBlogsRepository) {
		BlogsRepository = theBlogsRepository;
	}

	@Override
	public List<blogs> findAll() {
		return BlogsRepository.findAll();
	}

	@Override
	public blogs findById(int theId) {
		Optional<blogs> result = BlogsRepository.findById(theId);
		
		blogs theBlogs = null;
		
		if (result.isPresent()) {
            theBlogs = result.get();
        }
        else {
            throw new RuntimeException("Did not find blog id - " + theId);
        }
		return theBlogs;
	}

	@Override
	public blogs save(blogs theblog) {
		return BlogsRepository.save(theblog);
	}

	@Override
	public void deleteById(int theId) {
		BlogsRepository.deleteById(theId);
		
	}

}
