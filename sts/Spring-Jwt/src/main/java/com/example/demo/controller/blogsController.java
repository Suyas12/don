package com.example.demo.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourseNotFound;
import com.example.demo.model.blogs;
import com.example.demo.model.users;
import com.example.demo.repository.blogsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class blogsController {
	
	@Autowired
	private blogsRepository BlogsRepository;
	
	 @GetMapping("/blogs")
	    public List<blogs> getAllblogs(){
	    	return BlogsRepository.findAll();
	    }
	    
	    @PostMapping("/blogs")
	    public blogs saveBlogs(@RequestBody blogs Blogs) {
	    	return BlogsRepository.save(Blogs);
	    }
	    @GetMapping("/blogs/{id}")
	    public ResponseEntity<blogs> getBlogsByID(@PathVariable int id){
	    	blogs Blogs=BlogsRepository.findById(id)
	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
	    	return ResponseEntity.ok(Blogs);
	    } 
	    @GetMapping("/blog/{username}")
	    public ResponseEntity<List<blogs>> getBlogByUsername(@PathVariable("username") String username){
	    List<blogs> blog=BlogsRepository.findAllByUsername(username);
	    System.out.println(blog);
	    return ResponseEntity.ok(blog);
	    }
//	    @GetMapping("/blogs/{username}")
//	    public ResponseEntity<blogs> getBlogsByUsername(@PathVariable String username){
//	    	blogs Blogs=BlogsRepository.findByUsername(username)
//	    			.orElseThrow(()-> new ResourseNotFound("no record found with this username:"+username));
//	    	return ResponseEntity.ok(Blogs);
//	    } 
	    @PutMapping("/blogs/{id}")
	    public ResponseEntity<blogs> updateBlogs(@PathVariable int id,@RequestBody blogs Blogs){
	    	blogs blogs2=BlogsRepository.findById(id)
	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
	        blogs2.setTitle(Blogs.getTitle());
	        blogs2.setDescription(Blogs.getDescription());
	        blogs2.setUrl(Blogs.getUrl());
	        blogs updateBlogs=BlogsRepository.save(blogs2);	
	    	return ResponseEntity.ok(updateBlogs);
	    }
//	    @DeleteMapping("blogs/{id}")
//	    public ResponseEntity<Map<String, Boolean>> deleteBlogs(@PathVariable int id){
//	    	blogs Blogs=BlogsRepository.findById(id)
//	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
//	    	BlogsRepository.delete(Blogs);
//	    	Map<String, Boolean>response=new HashMap<>();
//	    	response.put("deleted",Boolean.TRUE);
//	    	return ResponseEntity.ok(response);
//	    }
	    @DeleteMapping("blogs/{username}")
	    public ResponseEntity<Map<String, Boolean>> deleteBlogs(@PathVariable("username") String username){
	    	List<blogs> Blogs=BlogsRepository.findAllByUsername(username);
	    	BlogsRepository.delete(Blogs);
	    	Map<String, Boolean>response=new HashMap<>();
	    	response.put("deleted",Boolean.TRUE);
	    	return ResponseEntity.ok(response);
	    }
	    
	}
