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

import com.Angular.project.Api.users;
import com.Angular.project.dao.usersRepository;
import com.Angular.project.service.usersService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class usersController {
	
	private usersService UsersService;
	
	@Autowired
	public usersController(usersService theUsersService){
		UsersService = theUsersService;
	}

	@GetMapping("/users")
    public List<users> findAll() {
        return UsersService.findAll();
    }
    
    @GetMapping("/users/{usersId}")
    public users getUsers(@PathVariable int usersId) {

        users theUsers = UsersService.findById(usersId);

        if (theUsers == null) {
            throw new RuntimeException("User id not found - " + usersId);
        }

        return theUsers;
    }
    
    @PostMapping("/users")
    public users addUsers(@RequestBody users theUsers) {

        theUsers.setId(0);

        users dbUsers = UsersService.save(theUsers);

        return dbUsers;
    }
    
    @PutMapping("/users")
    public users updateUsers(@RequestBody users theUsers) {

    	users dbUsers = UsersService.save(theUsers);

        return dbUsers;
    }
//    @PutMapping("/users")
//    public List<users> updateUsers(@PathVariable int usersId,@RequestBody users usersDetails) throws Exception {
//        users updateUsers;
//		try {
//			updateUsers = usersRepository.findById(usersId)
//			        .orElseThrow();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        updateUsers.setUsername(usersDetails.getUsername());
//        updateUsers.setRole(usersDetails.getRole());
//        updateUsers.setPassword(usersDetails.getPassword());
//
//        users dbUsers = UsersService.save(usersDetails);
//
//        return (List<users>) dbUsers;
//    }
    

    @DeleteMapping("/users/{usersId}")
    public String deleteUsers(@PathVariable int usersId) {

        users tempUsers = UsersService.findById(usersId);


        if (tempUsers == null) {
            throw new RuntimeException("User id not found - " + usersId);
        }

        UsersService.deleteById(usersId);

        return "Deleted User id - " + usersId;
    }
}
