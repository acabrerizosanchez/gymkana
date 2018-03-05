package com.springfilms.demo.utils;

public final class MyConstants {

	private MyConstants() {
		throw new IllegalStateException("Utility class");
	}

	/*
	 * GET PARAMS CONSTANTS
	 */
	public static final int NUMBER_OF_PARAMS_ON_GET = 3;
	public static final String REPORT = "report";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String HOT = "hot";
	public static final String COLD = "cold";

	/*
	 * VALIDATION CONSTANTS
	 */
	public static final String DATE_USER_AND_RATING_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FILM_FORMAT = "yyyy";
	public static final int MAX_LENGHT_STRINGS_JSON = 30;
	public static final int MAX_SCORE = 5;
	public static final int MIN_SCORE = 1;

	/*
	 * ERROR MESSAGES
	 */

	public static final String NO_ERROR_STRING = "NO_ERROR";
	public static final String INVALID_DATE_FORMAT = "Invalid Date Format";
	public static final String FIELD_MISSED = "Field missed on Json";

	public static final String NAME_FIELD_ERROR = "'Name' Field doesnt exist or blank";
	public static final String NAME_FIELD_TOO_LONG = "'Name' Field max characters is 30";
	public static final String SURNAME_FIELD_ERROR = "'Surname' Field doesnt exist or blank";
	public static final String SURNAME_FIELD_TOO_LONG = "'Surname' Field max characters is 30";
	public static final String TITLE_FIELD_ERROR = "'Title' Field doesnt exist or blank";
	public static final String TITLE_FIELD_TOO_LONG = "'Title' Field max characters is 30";
	public static final String ADULT_FIELD_EMPTY = "'isAdult' Fieldis empty";
	public static final String ADULT_FIELD_WRONG = "is Adult is wrong";
	public static final String INVALID_USERID = "Invalid userId field";
	public static final String INVALID_FILMID = "Invalid filmId field";
	public static final String INVALID_SCORE = "Invalid score";
	public static final String SCORE_FIELD_ERROR = "'Score' field blank or null";
	public static final String INVALID_NUMBER_GENRES = "Invalid number of genres";
	public static final String INVALID_GENRE_BLANK = "Any genre is blank";
	public static final String GENRE_FIELD_TOO_LONG = "Genre max characters is 30";

}
