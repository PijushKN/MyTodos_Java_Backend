package com.webservices.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webservices.rest.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 4;
	static {
		todos.add(new Todo(1, "Study Angular 12", new Date(), false));
		todos.add(new Todo(2, "Study Spring Boot", new Date(), false));
		todos.add(new Todo(3, "Study Selenium", new Date(), false));
		todos.add(new Todo(4, "Study React", new Date(), false));
	}
	
	public Todo save(Todo todo) {
		if(todo.getId()==-1) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else {
			deleteTodoService(todo.getId());
			todos.add(todo);
		}
		
		return todo;
	}
	
	public Todo retrieveTodoService(int id) {
		for(Todo todo:todos) {
			if(todo.getId()==id)
				return todo;
		}
		
		return null;
	}

	public List<Todo> retrieveAllTodos() {
		return todos;
	}

	public Todo deleteTodoService(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				todos.remove(todo);
				return todo;
			}
		}
		
		return null;

	}

}
