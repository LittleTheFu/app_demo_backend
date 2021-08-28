package com.fu.demo.controller;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(Principal principal, @RequestParam(value = "name", defaultValue = "World") String name) {
		if(principal!=null) {
			name = principal.getName();
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
