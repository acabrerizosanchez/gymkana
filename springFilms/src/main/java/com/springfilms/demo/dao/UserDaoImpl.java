package com.springfilms.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springfilms.demo.model.User;

@Repository
@Transactional
public class UserDaoImpl {


	@PersistenceContext
	private EntityManager entityManager;

	public void create(User usuario) {
		entityManager.persist(usuario);
		return;
	}

	public User getById(long id) {
		return entityManager.find(User.class, id);
	}

}
