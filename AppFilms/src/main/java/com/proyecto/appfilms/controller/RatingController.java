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

import com.proyecto.appfilms.bean.RatingBean;
import com.proyecto.appfilms.dao.RatingDao;
import com.proyecto.appfilms.model.Film;
import com.proyecto.appfilms.model.Rating;
import com.proyecto.appfilms.model.User;

@RestController
public class RatingController {

	private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
	@Autowired
	private RatingDao ratingDao;

	@RequestMapping(value = "/nueva_puntuacion", method = RequestMethod.POST)
	public ResponseEntity<RatingBean> create(@Valid @RequestBody RatingBean ratingbean, BindingResult result) {

		Rating puntuacion = new Rating();

		/**
		 * Con el siguiente if nos encargamos de las validaciones mediante las
		 * anotaciones en la clase UserBean En este if solo controlamos los errores que
		 * provienen de las anotaciones
		 */
		if (result.hasErrors()) {
			if (ratingbean.getScore() == null || Integer.parseInt(ratingbean.getScore()) < 1
					|| Integer.parseInt(ratingbean.getScore()) > 5) {
				ratingbean.setErrorMessage("El Score tiene que ser un valor comprendido entre 1 y 5");
				return new ResponseEntity(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getMovieId() == null) {
				ratingbean.setErrorMessage("El movieId no puede estar vacío");
				return new ResponseEntity(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getUserId() == null) {
				ratingbean.setErrorMessage("El userId no puede estar vacío");
				return new ResponseEntity(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getDate() == null || ratingbean.getDate().equals("")) {
				ratingbean.setErrorMessage("La fecha no puede estar vacía ni blanca");
				return new ResponseEntity(ratingbean, HttpStatus.BAD_REQUEST);
			} else {
				ratingDao.create(puntuacion);
				return new ResponseEntity(ratingbean, HttpStatus.OK);
			}

		}

		if (ratingbeanisvalid(ratingbean)) {
			puntuacion.setDate(stringToDateFormat(ratingbean.getDate(), ratingbean));
			puntuacion.setScore(Integer.parseInt(ratingbean.getScore()));
			puntuacion.setMovieId(new Film(Integer.parseInt(ratingbean.getMovieId())));
			puntuacion.setUserId(new User(Integer.parseInt(ratingbean.getUserId())));

			ratingDao.create(puntuacion);

			return new ResponseEntity(ratingbean, HttpStatus.OK);
		} else {
			ratingbean.setErrorMessage("Formato de fecha erróneo, el formato correcto es yyyy-MM-dd");
			return new ResponseEntity(ratingbean, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Este método realiza la validacion del formato de la fecha
	 */
	public static boolean ratingbeanisvalid(RatingBean ratingbean) {

		Date f = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			f = sdf.parse(ratingbean.getDate());
			if (!ratingbean.getDate().equals(sdf.format(f))) {

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
	public Date stringToDateFormat(String fechaValidada, RatingBean ratingbean) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date fechaDate = null;
		try {

			fechaDate = sdf.parse(fechaValidada);

		} catch (java.text.ParseException e) {
			ratingbean.setErrorMessage("fecha incorrecta");
			logger.error("No es valido el formato de la fecha");
		}

		return fechaDate;
	}

}
