package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class XyzController {
	
	@GetMapping("/xyz")
	public String hello() {
		return "helloworld";
	}

}
