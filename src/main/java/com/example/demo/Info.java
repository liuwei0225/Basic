package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("myInfo")
public class Info {
	@Value("${spring.application.name}")
	private String appName;

	@Value("${app.count}")
	private int count;

}
