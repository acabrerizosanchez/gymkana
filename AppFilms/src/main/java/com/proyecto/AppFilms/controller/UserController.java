package com.proyecto.AppFilms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.AppFilms.dao.UserDao;
import com.proyecto.AppFilms.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userdao;

	@RequestMapping(value = "/nuevo_usuario", method=RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {

		if (user != null) {
			userdao.create(user);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}