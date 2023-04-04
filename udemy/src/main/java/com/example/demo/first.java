package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class first {
	
	@Value("${coach.name}")
	private String coachname;
	
	@Value("${team.name}")
	private String teamname;
	
	@GetMapping("/team-info")
	public String info() {
		return "Coach is : " + coachname + " and Team name is : " + teamname;
	}
	
	@GetMapping("/hello")
	public String program() {
		return "Hello universe";
	}
}
