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

	/**
	 * 
	 * Comrpueba que el string existe y no es nulo
	 * 
	 * @param str
	 * @return true if valid
	 */
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

	/**
	 * 
	 * Comprueba si el formato de fecha (String) es valido segun el formato elegido
	 * 
	 * @param inDate
	 * @param format
	 * @return true if valid
	 */
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

	/**
	 * 
	 * Comprueba si el formato de fecha de FilmBean es válido
	 * 
	 * @param inDate
	 * @return true if valid
	 */
	public static boolean filmDateFormatIsValid(String inDate) {

		try {

			int anio = Integer.parseInt(inDate);
			if ((anio > MIN_FILM_YEAR) && (anio <= MAX_FILM_YEAR))
				return true;
		} catch (Exception e) {
			logger.error("Error parseando el año de la pelicula", e);
			return false;
		}

		return false;

	}

	/**
	 * Comprueba: Generos introducidos están entre MIN_GENRES y MAX_GENRES Generos
	 * existen y no blancos Generos con un maximo de caracteres:
	 * MAX_LENGHT_STRINGS_JSON
	 * 
	 * @param genres
	 *            String[] con los generos
	 * @return String con la informacion del error
	 */
	public static String genresAreValid(String[] genres) {

		try {
			if (genres.length < MIN_GENRES && genres.length > MAX_GENRES)
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

	/**
	 * Comprueba que str es parseable a numero y que es positivo
	 * 
	 * @param str
	 * @return true if valid
	 */
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

	/**
	 * Comprueba que score es parseable a numero y que su valor comprende entre los
	 * valores MIN_SCORE y MAX_SCORE
	 * 
	 * @param str
	 * @return true if valid
	 */
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

	/**
	 * Comprueba que los arguementos que enviamos a través del controlador
	 * BestAndWorstController son los correctos:
	 * 
	 * Coprueba:
	 * 
	 * - Que sean 3 argumentos - START_DATE y END_DATE sean del formato correcto
	 * ("yyyy-MM-dd") - REPORT sea hot o cold
	 * 
	 * QUEDA VALIDAR QUE LOS ARGUMENTOS DE ENTRADA SON ?report&startDate&endDate Por
	 * ahora lo que veo es leer el requestParams.getSetKeys y validarla
	 * 
	 * @param requestParams
	 * @return
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
