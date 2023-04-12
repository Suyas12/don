package com.Angular.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angular.project.Api.users;
import com.Angular.project.dao.usersRepository;

@Service
public class usersServiceImpl implements usersService{
	
	private usersRepository UsersRepository;
	
	@Autowired
	public usersServiceImpl(usersRepository theUsersRepository) {
		UsersRepository = theUsersRepository;
	}

	@Override
	public List<users> findAll() {
		return UsersRepository.findAll();
	}

	@Override
	public users findById(int theId) {
		Optional<users> result = UsersRepository.findById(theId);
		
		users theUsers = null;
		
		if (result.isPresent()) {
            theUsers = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
		return theUsers;
	}

	@Override
	public users save(users theuser) {
		return UsersRepository.save(theuser);
	}

	@Override
	public void deleteById(int theId) {
		UsersRepository.deleteById(theId);
	}

}
