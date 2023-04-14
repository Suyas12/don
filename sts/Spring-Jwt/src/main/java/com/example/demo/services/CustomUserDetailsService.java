package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.users;
import com.example.demo.repository.usersRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private usersRepository UsersRepository;
	@Override
	//Database
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			final users user = this.UsersRepository.findByUsername(username);
			if(user==null) {
				throw new UsernameNotFoundException("Username not found");
			}else {
				return new CustomUserDetails(user);
			}
//		if(username.equals("Suyas")) {
//			return new User("Suyas","Suyas123",new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("User not found!!");
//		}
	}
}
