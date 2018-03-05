package com.springfilms.demo.controller;

import static com.springfilms.demo.utils.MyConstants.*;
import static com.springfilms.demo.utils.FormatConversorMethods.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springfilms.demo.dao.UserDaoImpl;
import com.springfilms.demo.model.User;
import com.springfilms.demo.utils.UserValidator;
import com.springfilms.demo.beans.UserBean;

/**
 * Controlador del endpoint /new_user Valida y almacena usuarios en la bbdd de
 * la aplicacion, provenientes de un Json a través de POST
 * 
 * @author acosanchez
 *
 */
@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserDaoImpl userdaoimpl;

	@RequestMapping(value = "/usuario_nuevo", method = RequestMethod.POST)
	public ResponseEntity<UserBean> create(@RequestBody UserBean userbean) {

		try {
			if (userbean != null) {

				UserValidator validator = new UserValidator();
				String validatorResponse = validator.userIsValid(userbean);
				userbean.setErrorMessage(validatorResponse);

				if (validatorResponse.equals(NO_ERROR_STRING)) {

					User tempUser = new User();

					tempUser.setBirthDate(stringDateToDateFormat(userbean.getBirthDate(), DATE_USER_AND_RATING_FORMAT));
					tempUser.setName(userbean.getName());
					tempUser.setSurname(userbean.getSurname());
					userdaoimpl.create(tempUser);
					return new ResponseEntity<>(userbean, HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			logger.error("Error creating user", e);
		}

		return new ResponseEntity<>(userbean, HttpStatus.BAD_REQUEST);
	}

}
