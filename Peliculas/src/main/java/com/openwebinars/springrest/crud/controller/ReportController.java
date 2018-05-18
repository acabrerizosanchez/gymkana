package com.openwebinars.springrest.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinars.springrest.crud.model.Pelicula;
import com.openwebinars.springrest.crud.repo.PuntuacionRepository;



@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	PuntuacionRepository puntuacionRepository;
	
	@GetMapping("/mejorValorada")
	public Pelicula mejorPelicula() {		
		Pelicula mejorValorada = null;
		if (mejorValorada != null)
			return mejorValorada;
		else
			throw new PuntuacionNotFoundException();
	}
	

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class PuntuacionNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 7295910574475009536L;

		public PuntuacionNotFoundException() {
			super("No existe");
		}

	}

}
