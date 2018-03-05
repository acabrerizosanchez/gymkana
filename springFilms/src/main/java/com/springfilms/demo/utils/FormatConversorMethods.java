package com.springfilms.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public final class FormatConversorMethods {

	private static final Logger logger = Logger.getLogger(FormatConversorMethods.class);

	private FormatConversorMethods() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Convierte String into Date format.
	 * 
	 * @param dateString
	 *            String para parsear
	 * @param dateFormat
	 *            Formato deseado
	 * @return
	 */
	public static Date stringDateToDateFormat(String dateString, String dateFormat) {

		try {
			DateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.parse(dateString);

		} catch (ParseException e) {
			logger.info(e);
		}

		return null;

	}
}
