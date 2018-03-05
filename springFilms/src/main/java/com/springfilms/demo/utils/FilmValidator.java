package com.springfilms.demo.utils;

import org.apache.log4j.Logger;
import static com.springfilms.demo.utils.MyConstants.*;
import static com.springfilms.demo.utils.ValidationMethods.*;

import com.springfilms.demo.beans.FilmBean;

/**
 * Valida que los campos de las peliculas introducidas por json sean correctos
 * 
 * Validaciones: campos vacios,campos nulos,titulo max 30 char, fecha en formato
 * "yyyy",genero max 30char, 3 generos por pelicula, campo 'isAdult" sea true o
 * false.
 * 
 * 
 * 
 * @author acosanchez
 *
 */
public class FilmValidator {

	private static final Logger logger = Logger.getLogger(FilmValidator.class);

	public String filmIsValid(FilmBean filmbean) {

		try {
			if (stringIsBlankOrNull(filmbean.getTitle()))
				return TITLE_FIELD_ERROR;

			if (filmbean.getTitle().length() > MAX_LENGHT_STRINGS_JSON)
				return NAME_FIELD_TOO_LONG;

			if (!filmDateFormatIsValid(filmbean.getDate()))
				return INVALID_DATE_FORMAT;

			if (!(genresAreValid(filmbean.getGenres()).equals(NO_ERROR_STRING)))
				return genresAreValid(filmbean.getGenres());

			if (filmbean.getIsAdult() != null) {
				if (filmbean.getIsAdult().isEmpty())
					return ADULT_FIELD_EMPTY;
				else {
					if (!(filmbean.getIsAdult().equals("false") || filmbean.getIsAdult().equals("true")))
						return ADULT_FIELD_WRONG;
				}
			}

			return NO_ERROR_STRING;
		} catch (Exception exception) {
			logger.error("Exception When Validating Film", exception);
			return FIELD_MISSED;
		}
	}
}
