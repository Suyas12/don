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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.usersController;
import com.example.demo.exception.ResourseNotFound;
import com.example.demo.helper.JwtHelper;
import com.example.demo.model.users;
import com.example.demo.repository.usersRepository;
import com.example.demo.services.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class usersController {
	
	@Autowired
	private usersRepository UsersRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired                                    
	private JwtHelper jwtHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	 @GetMapping("/users")
	    public List<users> getAllusers(){
	    	return UsersRepository.findAll();
	    }
//	    @GetMapping("/users")
//	    public List<users> getAllusers(@RequestParam(value = "/role") String role ){
//	    	return UsersRepository.findAll();
//	    }
	    
	    @PostMapping("/users")
	    public users saveUsers(@RequestBody users Users) {
	    	return UsersRepository.save(Users);
	    }
	    @GetMapping("/users/{id}")
	    public ResponseEntity<users> getUsersByID(@PathVariable int id){
	    	users Users=UsersRepository.findById(id)
	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
	    	return ResponseEntity.ok(Users);
	    } 
	    @PutMapping("/users/{id}")
	    public ResponseEntity<users> updateUsers(@PathVariable int id,@RequestBody users Users){
	    	users users2=UsersRepository.findById(id)
	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
	        users2.setUsername(Users.getUsername());
	        users2.setPassword(Users.getPassword());
	        users2.setRole(Users.getRole());
	        users updateUsers=UsersRepository.save(users2);	
	    	return ResponseEntity.ok(updateUsers);
	    }
	    @DeleteMapping("users/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteUsers(@PathVariable int id){
	    	users Users=UsersRepository.findById(id)
	    			.orElseThrow(()-> new ResourseNotFound("no record found with this id:"+id));
	    	UsersRepository.delete(Users);
	    	Map<String, Boolean>response=new HashMap<>();
	    	response.put("deleted",Boolean.TRUE);
	    	return ResponseEntity.ok(response);
	    }
	}