package com.springfilms.demo.utils;

import static com.springfilms.demo.utils.MyConstants.DATE_USER_AND_RATING_FORMAT;

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

	public static Date stringDateToDateFormat(String dateString) {

		try {
			DateFormat formatter = new SimpleDateFormat(DATE_USER_AND_RATING_FORMAT);
			return formatter.parse(dateString);

		} catch (ParseException e) {
			logger.info(e);
		}

		return null;

	}
}
