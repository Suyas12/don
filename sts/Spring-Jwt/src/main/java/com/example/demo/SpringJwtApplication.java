package com.example.demo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.users;
import com.example.demo.repository.usersRepository;

@SpringBootApplication
public class SpringJwtApplication{
	
//	@Autowired
//	private usersRepository UsersRepository;

//	Random random = new Random();
	
//	public void createUsers() {
//		users User = new users();
//		int id = new Integer(random.nextInt(100));
//		User.setId(id);
//		User.setUsername("user"+id);
//		User.setRole("user");
//		User.setPassword("user"+id);
//		
//		users save = UsersRepository.save(User);
//		System.out.println(save);
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		createUsers();
//		
//	}

}
