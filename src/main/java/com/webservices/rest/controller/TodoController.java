package com.webservices.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservices.rest.model.HelloWorldBean;
import com.webservices.rest.model.Todo;
import com.webservices.rest.services.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

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
	
	@GetMapping("/todos/{id}")
	public Todo retrieveTodo(@PathVariable int id) {
		return todoService.retrieveTodoService(id);
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable int id,@RequestBody Todo todo){
		Todo updatedTodo = todoService.save(todo);
		return new ResponseEntity<Todo>(updatedTodo,HttpStatus.OK);
	}
	@PostMapping("/todos")
	public ResponseEntity<Void> createTodo(@RequestBody Todo todo){
		Todo createdTodo = todoService.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
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
