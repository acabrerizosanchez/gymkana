package com.proyecto.appfilms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.appfilms.bean.UserBean;
import com.proyecto.appfilms.dao.UserDao;
import com.proyecto.appfilms.model.User;

/**
 * 
 * @author jmcaceres
 *
 *         Clase User que realiza la validacion y creacion.
 */

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserDao userdao;

	@RequestMapping(value = "/nuevo_usuario", method = RequestMethod.POST)
	public ResponseEntity<UserBean> create(@Valid @RequestBody UserBean userbean, BindingResult result) {

		userbean.toString();

		User usuario = new User();
		/**
		 * Con el siguiente if nos encargamos de las validaciones mediante las
		 * anotaciones en la clase UserBean En este if solo controlamos los errores que
		 * provienen de las anotaciones
		 */
		if (result.hasErrors()) {

			if (userbean.getName() == null || userbean.getName() == "") {
				userbean.setErrorMessage("El nombre no puede estar vacío ni blanco");
				return new ResponseEntity(userbean, HttpStatus.BAD_REQUEST);

			} else if (userbean.getSurname() == null || userbean.getSurname() == "") {
				userbean.setErrorMessage("El apellido no puede estar vacío ni blanco");
				return new ResponseEntity(userbean, HttpStatus.BAD_REQUEST);

			} else if (userbean.getBirthDate() == null || userbean.getBirthDate() == "") {
				userbean.setErrorMessage("La fecha de nacimiento no puede estar vacío ni blanco");
				return new ResponseEntity(userbean, HttpStatus.BAD_REQUEST);

			} else {

				logger.error("No se ha podido crear el usuario");
				userbean.setErrorMessage("Algo falló");
				return new ResponseEntity(userbean, HttpStatus.BAD_REQUEST);
			}
		}

		if (userbeanisvalid(userbean)) {
			usuario.setBirthDate(stringToDateFormat(userbean.getBirthDate(), userbean));
			usuario.setName(userbean.getName());
			usuario.setSurname(userbean.getSurname());
			usuario.setErrorMessage(userbean.getErrorMessage());

			userdao.create(usuario);

			return new ResponseEntity(userbean, HttpStatus.OK);
		} else {
			userbean.setErrorMessage("Formato de fecha erróneo, el formato correcto es yyyy-MM-dd");
			return new ResponseEntity(userbean, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Este método realiza la validacion del formato de la fecha
	 */
	public static boolean userbeanisvalid(UserBean userbean) {

		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(userbean.getBirthDate());
			if (!userbean.getBirthDate().equals(sdf.format(date))) {

				return false;
			}
		} catch (java.text.ParseException ex) {
			logger.error("No es valido el formato de la fecha");
			return false;
		}

		return true;

	}

	/**
	 * Este método transforma el String fecha de UserBean en un Date fecha para
	 * poder introducirlo en User
	 */
	public Date stringToDateFormat(String fechaValidada, UserBean userbean) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date fechaDate = null;
		try {

			fechaDate = sdf.parse(fechaValidada);

		} catch (java.text.ParseException e) {
			userbean.setErrorMessage("fecha incorrecta");
			logger.error("No es valido el formato de la fecha");
		}

		return fechaDate;
	}

}
