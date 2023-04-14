package com.example.demo.repository;

import com.example.demo.model.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<users,Long>{
	//username, it will return the user of the given username
	public users findByUsername(String username);
}
