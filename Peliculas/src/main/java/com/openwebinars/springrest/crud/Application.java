package com.openwebinars.springrest.crud;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.openwebinars.springrest.crud.model.Pelicula;
import com.openwebinars.springrest.crud.repo.PeliculaRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	/*@Bean
	CommandLineRunner init(PeliculaRepository peliculaRepository) {
		
		String[][] data = {
				{"El septimo sello", "1975"},
				{"Barry Lyndon", "1978"},
				{"La mirada de Ulises", "1982"},
				{"El espejo", "1973"},
				{"El Sur", "02/12/1978"},
				{"El caballo de Turín", "1976"},
				{"La doble vida de Veronica", "1983"},
				{"La cinta blanca", "1978"}
		};
		
		final DateFormat df = new SimpleDateFormat("yyyy");
		
		return (evt) -> Arrays.asList(data)
				.forEach(a -> {
				
					try {
						peliculaRepository.save(new Pelicula(a[0], df.parse(a[1])));
					} catch (Exception e) {						
						e.printStackTrace();
					}
					
				});
				
		
	}*/
	
}
