package com.springfilms.demo.utils;

import static com.springfilms.demo.utils.MyConstants.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.log4j.Logger;

public final class ValidationMethods {

	private static final Logger logger = Logger.getLogger(ValidationMethods.class);

	private ValidationMethods() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean stringIsBlankOrNull(String str) {
		try {
			if (!((str == null) || (str.equals(""))))
				return false;

		} catch (NullPointerException e) {
			logger.error("NullPointer Exception when validating null fields", e);
			return false;
		}
		return true;
	}

	public static boolean dateFormatIsValid(String inDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);

		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			logger.error(pe);
			return false;
		}

		return true;
	}

	public static String genresAreValid(String[] genres) {

		try {
			if (genres.length <= 0 || genres.length > 3)
				return INVALID_NUMBER_GENRES;

			else {
				for (int i = 0; i < genres.length; i++) {
					if (genres[i].isEmpty())
						return INVALID_GENRE_BLANK;
					if (genres[i].length() > MAX_LENGHT_STRINGS_JSON)
						return GENRE_FIELD_TOO_LONG;
				}
			}

		} catch (Exception e) {
			logger.error("Error validando generos", e);
		}

		return NO_ERROR_STRING;
	}

	public static boolean userIdOrFilmIdAreValid(String str) {

		try {
			long l = Long.parseLong(str);

			if (l > 0)
				return true;
		} catch (NumberFormatException e) {
			logger.error("Error al validar userId of filmId", e);
		}
		return false;

	}

	public static boolean scoreIsValid(String str) {

		try {
			int i = Integer.parseInt(str);
			if (i >= MIN_SCORE && i <= MAX_SCORE)
				return true;

		} catch (NumberFormatException e) {

			logger.error("NumberFormatException: " + e.getMessage());
		}
		return false;
	}

	/*
	 * QUEDA VALIDAR QUE LOS ARGUMENTOS DE ENTRADA SON ?report&startDate&endDate Por
	 * ahora lo que veo es leer el requestParams.getSetKeys y validarla
	 */

	public static boolean paramsAreValid(Map<String, String> requestParams) {

		try {
			if (requestParams.size() != NUMBER_OF_PARAMS_ON_GET)
				return false;
		} catch (NullPointerException e) {
			logger.error("Error comprobando parametros de entrada del GET", e);
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_USER_AND_RATING_FORMAT);
			dateFormat.setLenient(false);
			dateFormat.parse(requestParams.get(START_DATE).trim());
			dateFormat.parse(requestParams.get(END_DATE).trim());
		} catch (ParseException pe) {
			logger.error("Error parseando fechas", pe);
			return false;
		}

		try {
			return (HOT.equals(requestParams.get(REPORT)) || COLD.equals(requestParams.get(REPORT)));
		} catch (NullPointerException e) {
			logger.error("Error al comprobar report param", e);
		}
		return true;
	}
}
