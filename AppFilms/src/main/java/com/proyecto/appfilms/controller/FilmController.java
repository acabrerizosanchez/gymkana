package com.proyecto.appfilms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.appfilms.bean.FilmBean;
import com.proyecto.appfilms.dao.FilmDao;
import com.proyecto.appfilms.model.Film;

@RestController
public class FilmController {

	private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
	@Autowired
	private FilmDao filmDao;

	@RequestMapping(value = "/nueva_pelicula", method = RequestMethod.POST)
	public ResponseEntity<FilmBean> create(@Valid @RequestBody FilmBean filmbean, BindingResult result) {

		Film movie = new Film();
		/**
		 * Con el siguiente if nos encargamos de las validaciones mediante las
		 * anotaciones en la clase UserBean En este if solo controlamos los errores que
		 * provienen de las anotaciones
		 */
		if (result.hasErrors()) {

			if (filmbean.getTitle() == null || filmbean.getTitle() == "") {
				filmbean.setErrorMessage("El titulo no puede estar vacío ni blanco");

				return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);

			} else if (filmbean.getGenres() == null) {
				filmbean.setErrorMessage("El genero no puede estar vacío");
				return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
			} else if (filmbean.getDate() == null || filmbean.getDate().equals("")) {
				filmbean.setErrorMessage("La fecha no puede estar vacío ni blanco");
				return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
			} else if (filmbean.getDate().length() < 4) {
				filmbean.setErrorMessage("La fecha debe tener el formato yyyy");
				return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
			} else {
				logger.error("No se ha podido crear la pelicula");
				return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
			}

		} else if (filmbean.getGenres().length > 3) {

			filmbean.setErrorMessage("No puede haber mas de 3 generos");
			return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
		} else if (filmbean.getIsAdult() != null && !filmbean.getIsAdult().equals("true")
				&& !filmbean.getIsAdult().equals("false")) {
			filmbean.setErrorMessage("El campo isAdult tiene que ser true o false");
			return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
		}

		if (filmbeanisvalid(filmbean)) {

			movie.setDate(stringToDateFormat(filmbean.getDate(), filmbean));
			movie.setTitle(filmbean.getTitle());
			movie.setGenres(filmbean.getGenres());
			movie.setErrorMessage(filmbean.getErrorMessage());
			movie.setIsAdult(filmbean.getIsAdult());

			filmDao.create(movie);

			return new ResponseEntity(filmbean, HttpStatus.OK);
		} else {
			filmbean.setErrorMessage("Formato de fecha erróneo, el formato correcto es yyyy");
			return new ResponseEntity(filmbean, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Este método realiza la validacion del formato de la fecha
	 */
	public static boolean filmbeanisvalid(FilmBean filmbean) {

		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			date = sdf.parse(filmbean.getDate());
			if (!filmbean.getDate().equals(sdf.format(date))) {

				return false;
			}
		} catch (java.text.ParseException ex) {
			logger.error("No es valido el formato de la fecha");
			return false;
		}

		return true;

	}

	/**
	 * Este método transforma el String fecha de UserBean en un Date fecha para
	 * poder introducirlo en User
	 */
	public Date stringToDateFormat(String fechaValidada, FilmBean filmbean) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		Date fechaDate = null;
		try {

			fechaDate = sdf.parse(fechaValidada);

		} catch (java.text.ParseException e) {
			filmbean.setErrorMessage("fecha incorrecta");
			logger.error("No es valido el formato de la fecha");
		}

		return fechaDate;
	}

}
