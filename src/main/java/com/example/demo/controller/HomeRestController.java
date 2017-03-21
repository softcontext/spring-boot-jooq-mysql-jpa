package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/")
	public Object home(){
		return "check out >>  com.example.demo.service.BlogServiceImplTest";
	}
}
