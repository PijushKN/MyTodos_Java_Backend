package com.webservices.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.rest.model.HelloWorldBean;
import com.webservices.rest.model.Todo;
import com.webservices.rest.services.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

	@Autowired
	TodoService todoService;

	@GetMapping("/hello-world/{name}")
	public HelloWorldBean showMessage(@PathVariable String name) {
		return new HelloWorldBean("Hi, " + name + " welcome to our site");
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean showMessage2() {
		return new HelloWorldBean("Hi, welcome to our site");
	}

	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return todoService.retrieveAllTodos();
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
		Todo todo = todoService.deleteTodoService(id);
		
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
