package com.proyecto.AppFilms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.AppFilms.dao.RatingDao;
import com.proyecto.AppFilms.model.Rating;

@RestController
public class RatingController {

	@Autowired
	private RatingDao ratingDao;
	
	@RequestMapping(value = "/nueva_puntuacion", method=RequestMethod.POST)
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		
		if(rating != null) {
			ratingDao.create(rating);
		}
		return new ResponseEntity<Rating>(rating, HttpStatus.OK);
	}
	
}
