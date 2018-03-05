package com.springfilms.demo.controller;

import static com.springfilms.demo.utils.FormatConversorMethods.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springfilms.demo.beans.FilmBean;
import com.springfilms.demo.dao.FilmDaoImpl;
import com.springfilms.demo.model.Film;
import com.springfilms.demo.utils.FilmValidator;

@RestController
public class FilmController {

	private static final Logger logger = Logger.getLogger(FilmController.class);

	@Autowired
	private FilmDaoImpl filmdaoimpl;

	@RequestMapping(value = "/new_film", method = RequestMethod.POST)
	public ResponseEntity<FilmBean> create(@RequestBody FilmBean filmbean) {

		if (filmbean != null) {
			FilmValidator validator = new FilmValidator();

			String validatorResponse = validator.filmIsValid(filmbean);
			filmbean.setErrorMessage(validatorResponse);

			if (validatorResponse.equals("NO_ERROR")) {

				Film tempFilm = new Film();

				tempFilm.setTitle(filmbean.getTitle());
				tempFilm.setGenres(filmbean.getGenres());
				tempFilm.setDate(stringDateToDateFormat(filmbean.getDate()));

				try {
					if (((filmbean.getIsAdult()) != null)) {
						tempFilm.setAdult(Boolean.valueOf(filmbean.getIsAdult()));
					}
				} catch (NullPointerException e) {
					logger.info(e);
				}

				filmdaoimpl.create(tempFilm);
				return new ResponseEntity<>(filmbean, HttpStatus.OK);
			}

		}
		return new ResponseEntity<>(filmbean, HttpStatus.BAD_REQUEST);

	}

}
