package com.webservices.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.rest.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@GetMapping("/hello-world/{name}")
	public HelloWorldBean showMessage(@PathVariable String name) {
		return new HelloWorldBean("Hi, "+name+" welcome to our site");
	}
}
