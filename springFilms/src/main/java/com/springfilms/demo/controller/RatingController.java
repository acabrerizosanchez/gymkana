package com.springfilms.demo.controller;

import static com.springfilms.demo.utils.FormatConversorMethods.*;
import static com.springfilms.demo.utils.MyConstants.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springfilms.demo.beans.RatingBean;
import com.springfilms.demo.dao.FilmDaoImpl;
import com.springfilms.demo.dao.RatingDaoImpl;
import com.springfilms.demo.dao.UserDaoImpl;
import com.springfilms.demo.model.Rating;
import com.springfilms.demo.utils.RatingValidator;

@RestController
public class RatingController {

	private static final Logger logger = Logger.getLogger(RatingController.class);

	@Autowired
	private RatingDaoImpl ratingdaoimpl;

	@Autowired
	private UserDaoImpl userdaoimpl;

	@Autowired
	private FilmDaoImpl filmdaoimpl;

	@RequestMapping(value = "/new_rating", method = RequestMethod.POST)
	public ResponseEntity<RatingBean> create(@RequestBody RatingBean ratingbean) {

		try {
			if (ratingbean != null) {

				RatingValidator validator = new RatingValidator();
				String validatorResponse = validator.ratingIsValid(ratingbean);
				ratingbean.setErrorMessage(validatorResponse);

				if (validatorResponse.equals(NO_ERROR_STRING)) {

					Rating ratingTemp = new Rating();

					ratingTemp.setUser(userdaoimpl.getById(Long.parseLong(ratingbean.getUserId())));
					ratingTemp.setFilm(filmdaoimpl.getById(Long.parseLong(ratingbean.getMovieId())));
					ratingTemp.setScore(Integer.parseInt(ratingbean.getScore()));
					ratingTemp.setDate(stringDateToDateFormat(ratingbean.getDate()));

					ratingdaoimpl.create(ratingTemp);
					return new ResponseEntity<>(ratingbean, HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			logger.error("Error creating rating", e);
		}

		return new ResponseEntity<>(ratingbean, HttpStatus.BAD_REQUEST);
	}

}
