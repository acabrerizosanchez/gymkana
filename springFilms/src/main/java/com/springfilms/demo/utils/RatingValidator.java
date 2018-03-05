package com.springfilms.demo.utils;

import org.apache.log4j.Logger;
import static com.springfilms.demo.utils.MyConstants.*;
import static com.springfilms.demo.utils.ValidationMethods.*;
import com.springfilms.demo.beans.RatingBean;

public class RatingValidator {

	private static final Logger logger = Logger.getLogger(RatingValidator.class);

	public String ratingIsValid(RatingBean ratingbean) {

		try {
			if (!userIdOrFilmIdAreValid(ratingbean.getUserId()))
				return INVALID_USERID;

			if (!userIdOrFilmIdAreValid(ratingbean.getMovieId()))
				return INVALID_FILMID;

			if (stringIsBlankOrNull(ratingbean.getScore()))
				return SCORE_FIELD_ERROR;

			if (!scoreIsValid(ratingbean.getScore()))
				return INVALID_SCORE;

			if (!dateFormatIsValid(ratingbean.getDate(), DATE_USER_AND_RATING_FORMAT))
				return INVALID_DATE_FORMAT;

			return NO_ERROR_STRING;

		} catch (Exception e) {
			logger.error("Error validando Rating", e);
		}

		return FIELD_MISSED;
	}

}
