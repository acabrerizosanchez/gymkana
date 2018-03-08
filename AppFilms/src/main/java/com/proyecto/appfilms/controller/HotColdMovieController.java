package com.proyecto.appfilms.controller;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.appfilms.dao.FilmDao;
import com.proyecto.appfilms.model.Film;

@Controller
public class HotColdMovieController {

	private static final Logger logger = LoggerFactory.getLogger(HotColdMovieController.class);

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private FilmDao filmdao;

	/**
	 * 
	 * @param param
	 * @return
	 * 
	 * 		Con este método devolvemos un String que nos indica la pelicula mas o
	 *         menos votada en un tramo temporal
	 */
	@RequestMapping(value = "/HotColdMovie", method = RequestMethod.GET)
	public ResponseEntity<String> getMovie(@RequestParam Map<String, String> param) {

		String report = param.get("report");
		String startDate = "'" + param.get("startDate") + "'";
		String endDate = "'" + param.get("endDate") + "'";

		Film movie = null;

		try {
			if (startDate.isEmpty()) {
				return new ResponseEntity<>("La fecha de inicio es incorrecta", HttpStatus.BAD_REQUEST);
			} else if (endDate.isEmpty()) {
				return new ResponseEntity<>("La fecha de inicio es incorrecta", HttpStatus.BAD_REQUEST);
			} else if (report.isEmpty()) {
				return new ResponseEntity<>("El report debe ser hot o cold", HttpStatus.BAD_REQUEST);
			}

			if (report.equals("hot")) {
				long idMovie = filmdao.getHotMovie(startDate, endDate);
				movie = filmdao.getById(idMovie);
				return new ResponseEntity<>("La pelicula mas votada es: " + movie.getTitle(), HttpStatus.OK);
			} else if (report.equals("cold")) {
				long idMovie = filmdao.getColdMovie(startDate, endDate);
				movie = filmdao.getById(idMovie);
				return new ResponseEntity<>("La pelicula menos votada es: " + movie.getTitle(), HttpStatus.OK);
			}
		} catch (NullPointerException e) {
			logger.error("Campos nulos");
			e.getMessage();
			return new ResponseEntity<>("Hay campos nulos o no validos", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("Todo funciona correctamente", HttpStatus.OK);
	}
}
