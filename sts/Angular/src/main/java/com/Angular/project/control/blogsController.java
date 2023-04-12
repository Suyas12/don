package com.Angular.project.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Angular.project.Api.blogs;
import com.Angular.project.service.blogsService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class blogsController {

	private blogsService BlogsService;
	
	@Autowired
	public blogsController(blogsService theBlogsService){
		BlogsService = theBlogsService;
	}
	
	@GetMapping("/blogs")
    public List<blogs> findAll() {
        return BlogsService.findAll();
    }
    
    @GetMapping("/blogs/{blogsId}")
    public blogs getBlogs(@PathVariable int blogsId) {

        blogs theBlogs = BlogsService.findById(blogsId);

        if (theBlogs == null) {
            throw new RuntimeException("Blog id not found - " + blogsId);
        }

        return theBlogs;
    }
    
    @PostMapping("/blogs")
    public blogs addBlogs(@RequestBody blogs theBlogs) {

        theBlogs.setId(0);

        blogs dbBlogs = BlogsService.save(theBlogs);

        return dbBlogs;
    }
    
    @PutMapping("/blogs")
    public blogs updateBlogs(@RequestBody blogs theBlogs) {

    	blogs dbBlogs = BlogsService.save(theBlogs);

        return dbBlogs;
    }
    
    @DeleteMapping("/blogs/{blogsId}")
    public String deleteBlogs(@PathVariable int blogsId) {

        blogs tempBlogs = BlogsService.findById(blogsId);


        if (tempBlogs == null) {
            throw new RuntimeException("Blog id not found - " + blogsId);
        }

        BlogsService.deleteById(blogsId);

        return "Deleted Blog id - " + blogsId;
    }
}
