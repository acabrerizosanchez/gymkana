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

import com.openwebinars.springrest.crud.model.Usuario;
import com.openwebinars.springrest.crud.repo.UsuarioRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(PeliculaRepository peliculaRepository) {
		
		String[][] data = {
				{"El septimo sello", "01/01/1975"},
				{"Barry Lyndon", "10/10/1978"},
				{"La mirada de Ulises", "18/09/1982"},
				{"El espejo", "03/03/1973"},
				{"El Sur", "02/12/1978"},
				{"El caballo de Turín", "04/05/1976"},
				{"La doble vida de Veronica", "14/07/1983"},
				{"La cinta blanca", "28/08/1978"}
		};
		
		final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		return (evt) -> Arrays.asList(data)
				.forEach(a -> {
				
					try {
						peliculaRepository.save(new Pelicula(a[0], df.parse(a[1])));
					} catch (Exception e) {						
						e.printStackTrace();
					}
					
				});
				
		
	}
	
	/*@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		
		String[][] data = {
				{"Alberto", "alberto"},
				{"Alvaro", "alvaro"}
		};

		
		return (evt) -> Arrays.asList(data)
				.forEach(a -> {
				
					try {
						usuarioRepository.save(new Usuario(a[0], a[1]));
					} catch (Exception e) {						
						e.printStackTrace();
					}
					
				});
				
		
	}*/
	
}
