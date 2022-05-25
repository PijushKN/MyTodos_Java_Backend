package com.webservices.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webservices.rest.model.Todo;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(1, "Study Angular 12", new Date(), false));
		todos.add(new Todo(2, "Study Spring Boot", new Date(), false));
		todos.add(new Todo(3, "Study Selenium", new Date(), false));
		todos.add(new Todo(4, "Study React", new Date(), false));
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
