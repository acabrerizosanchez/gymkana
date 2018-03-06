package com.proyecto.AppFilms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.AppFilms.bean.RatingBean;
import com.proyecto.AppFilms.dao.RatingDao;
import com.proyecto.AppFilms.model.Rating;

@RestController
public class RatingController {

	@Autowired
	private RatingDao ratingDao;

	@RequestMapping(value = "/nueva_puntuacion", method = RequestMethod.POST)
	public ResponseEntity<RatingBean> create(@Valid @RequestBody RatingBean ratingbean, BindingResult result) {

		Rating puntuacion = new Rating();

		/**
		 * Con el siguiente if nos encargamos de las validaciones mediante las anotaciones en la clase UserBean
		 * En este if solo controlamos los errores que provienen de las anotaciones
		 */
		if (result.hasErrors()) {
			if (ratingbean.getScore() < 1 || ratingbean.getScore() > 5) {
				ratingbean.setErrorMessage("El Score tiene que ser un valor comprendido entre 1 y 5");
				return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getMovieId() == null) {
				ratingbean.setErrorMessage("El movieId no puede estar vacío");
				return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getUserId() == null) {
				ratingbean.setErrorMessage("El userId no puede estar vacío");
				return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.BAD_REQUEST);
			}
			if (ratingbean.getDate() == null || ratingbean.getDate().equals("")) {
				ratingbean.setErrorMessage("La fecha no puede estar vacía ni blanca");
				return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.BAD_REQUEST);
			} else if (ratingbean != null) {
				ratingDao.create(puntuacion);
				return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.OK);
			}
		}

		if (ratingbeanisvalid(ratingbean)) {
			puntuacion.setDate(stringToDateFormat(ratingbean.getDate(), ratingbean));
			puntuacion.setScore(ratingbean.getScore());
			puntuacion.setMovieId(ratingbean.getMovieId());
			puntuacion.setUserId(ratingbean.getUserId());

			ratingDao.create(puntuacion);

			return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.OK);
		} else {
			ratingbean.setErrorMessage("Formato de fecha erróneo, el formato correcto es yyyy-MM-dd");
			return new ResponseEntity<RatingBean>(ratingbean, HttpStatus.BAD_REQUEST);
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
				f = null;
			}
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		if (f == null) {
			return false;
		} else {
			return true;
		}

	}
	/**
	 * Este método transforma el String fecha de UserBean en un Date fecha para poder introducirlo en User
	 */
	public Date stringToDateFormat(String fecha_validada, RatingBean ratingbean) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha_date = null;
		try {

			fecha_date = sdf.parse(fecha_validada);

		} catch (java.text.ParseException e) {
			ratingbean.setErrorMessage("fecha incorrecta");
			e.printStackTrace();
		}

		return fecha_date;
	}

}
