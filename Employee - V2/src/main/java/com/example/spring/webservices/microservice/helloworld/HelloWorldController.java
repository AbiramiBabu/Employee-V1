package com.example.spring.webservices.microservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/helloWorldBean/{name}")
	public helloWorldBean helloWorldPathVariable(@PathVariable  String name) {
		return new helloWorldBean(String.format("helloWorld, %s", name));
	}
}
