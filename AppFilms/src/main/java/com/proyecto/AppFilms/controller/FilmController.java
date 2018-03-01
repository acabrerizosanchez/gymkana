package com.proyecto.AppFilms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.AppFilms.dao.FilmDao;
import com.proyecto.AppFilms.model.Film;


@RestController
public class FilmController {

	@Autowired
	private FilmDao filmDao;
	
	@RequestMapping(value = "/nueva_pelicula", method=RequestMethod.POST)
	public ResponseEntity<Film> create(@RequestBody Film film){
		if(film != null) {
			
			filmDao.create(film);
		}
		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}
}
