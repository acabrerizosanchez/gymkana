package com.springfilms.demo.utils;

import static com.springfilms.demo.utils.MyConstants.*;
import static com.springfilms.demo.utils.ValidationMethods.*;

import org.apache.log4j.Logger;

import com.springfilms.demo.beans.UserBean;

/*
 * Valida el json de entrada para la insercion de usuarios
 * 1.Campos no nulos y no vacios
 * 2.name & surname maximo "MAX_LENGHT_STRING_JSON" caracteres 
 * 3.Formato de fecha
 * Devuelve String con informacion del error
 */
public class UserValidator {

	private static final Logger logger = Logger.getLogger(UserValidator.class);

	public String userIsValid(UserBean userbean) {

		try {
			if (stringIsBlankOrNull(userbean.getName()))
				return NAME_FIELD_ERROR;

			if (userbean.getName().length() > MAX_LENGHT_STRINGS_JSON)
				return NAME_FIELD_TOO_LONG;

			if (stringIsBlankOrNull(userbean.getSurname()))
				return SURNAME_FIELD_ERROR;

			if (userbean.getSurname().length() > MAX_LENGHT_STRINGS_JSON)
				return SURNAME_FIELD_TOO_LONG;

			if (!dateFormatIsValid(userbean.getBirthDate(), DATE_USER_AND_RATING_FORMAT))
				return INVALID_DATE_FORMAT;

			return NO_ERROR_STRING;
		} catch (Exception exception) {
			logger.error("Exception When Validating User", exception);
			return FIELD_MISSED;
		}
	}
}
