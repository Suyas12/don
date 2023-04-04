package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class DemoRestController {

	private List<Student> theStudents;
	
	//define @PostConstruct to load the student data only once
	@PostConstruct
	public void loadData(){
//		List<Student> theStudents = new ArrayList<>();
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Suyas","Awasthi"));
		theStudents.add(new Student("ABC","XYZ"));
		theStudents.add(new Student("DEF","UVW"));	
	}
	@GetMapping("/Students/{studentId}")
	public Student getStudents(@PathVariable int studentId){ 
//		List<Student> theStudents = new ArrayList<>();
		
		if((studentId >= theStudents.size()) || (studentId <0 )) {
			 
			throw new StudentNotFoundException("Student id not found" +studentId);
		}
		
		return theStudents.get(studentId);
	}
	
	//Add an exception handler using @ExceptionHandler
//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException (StudentNotFoundException exc){
//		
//		//create a StudentErrorResponse
//		StudentErrorResponse error = new StudentErrorResponse();
//		error.setStatus(HttpStatus.NOT_FOUND.value());
//		error.setMessage(exc.getMessage());
//		error.setTimestamp(System.currentTimeMillis());
//		
//		//Return responsibility
//		
//		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//	}
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException (Exception exc){
		
		//create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		//Return responsibility
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
//	@GetMapping("/Students")
//	public List<Student>getStudents(){ 
////		List<Student> theStudents = new ArrayList<>();
//		
//		return theStudents;
//	}
//	@GetMapping("/Students/{studentId}")
//	public Student getStudents(@PathVariable int studentId){ 
////		List<Student> theStudents = new ArrayList<>();
//		
//		return theStudents.get(studentId);
//	}
//}
	
//	@GetMapping("/Students")
//	public List<Student>getStudents(){ 
//		List<Student> theStudents = new ArrayList<>();
//		theStudents.add(new Student("Suyas","Awasthi")); 
//		theStudents.add(new Student("ABC","XYZ"));
//		theStudents.add(new Student("DEF","UVW"));
//		return theStudents;
//	}
//	
