package com.example.demo;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@SpringBootApplication
public class BasicApplication {

	private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BasicApplication.class, args);
	}
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig =new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("git.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }
	
	@Autowired
	TajiConfiguration taiji;

	@Bean
	public ApplicationRunner appRunner() {
		return args -> {
			System.out.println(taiji.fooProperties);
		};
	}

	@Autowired
	private CounterService counterService;

	@Bean
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}

	@Bean
	public HealthIndicator myHealth() {
		return () -> {
			int x=1+(int)(Math.random()*50);
			if (x%2==0) {
				return Health.down().withDetail("Error Code1", 500).build();
			} else {
				return Health.down().withDetail("Error Code2", 404).build();
			}
//			return Health.up().build();
		
		};
	}
}
