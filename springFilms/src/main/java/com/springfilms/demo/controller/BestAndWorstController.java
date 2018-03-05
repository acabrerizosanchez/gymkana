package com.springfilms.demo.controller;

import static com.springfilms.demo.utils.MyConstants.*;
import static com.springfilms.demo.utils.ValidationMethods.*;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springfilms.demo.beans.ConcreteFilmBean;
import com.springfilms.demo.dao.FilmDaoImpl;
import com.springfilms.demo.dao.RatingDaoImpl;

@RestController
public class BestAndWorstController {

	private static final Logger logger = Logger.getLogger(BestAndWorstController.class);

	@Autowired
	private FilmDaoImpl filmdao;

	@Autowired
	private RatingDaoImpl ratingdao;

	@RequestMapping(value = "/show_stats", method = RequestMethod.GET)
	public ResponseEntity<ConcreteFilmBean> showBestAndWorst(@RequestParam Map<String, String> requestParams) {

		if (!paramsAreValid(requestParams)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {

			try {
				String startDate = requestParams.get(START_DATE);
				String endDate = requestParams.get(END_DATE);
				String report = requestParams.get(REPORT);

				ConcreteFilmBean filmBean = ratingdao.getBestOrWorstRatedMovieThroughConcreteTime(startDate, endDate,
						report);

				filmBean.setTitle(filmdao.getById(filmBean.getId()).getTitle());
				return new ResponseEntity<>(filmBean, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Error desconocido en el metodo showBestAndWorst", e);
			}
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
