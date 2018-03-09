package com.proyecto.appfilms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class AppFilmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AppFilmsApplication.class, args);
	}
	/**
	 * Esto lo añadimos para poder desplegarlo en Tomcat
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppFilmsApplication.class);
    }
}