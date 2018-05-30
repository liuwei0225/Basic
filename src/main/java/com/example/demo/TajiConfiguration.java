package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;




@Configuration
@EnableConfigurationProperties(FooProperties.class)
public class TajiConfiguration {
	
	@Autowired
	FooProperties fooProperties;
	
	public String fooProperties() {
		return fooProperties.toString();
	}

}
