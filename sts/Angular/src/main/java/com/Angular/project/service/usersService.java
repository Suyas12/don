package com.Angular.project.service;

import com.Angular.project.Api.users;

import java.util.List;


public interface usersService {
	
	List<users> findAll();

    users findById(int theId);

    users save(users theuser);

    void deleteById(int theId);

}
