package com.proyecto.appfilms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.proyecto.appfilms.model.User;

@Repository
@Transactional
public class UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;
    /*
     * Almacena el usuario en la base de datos
     */
    public void create(User usuario) {
    	entityManager.persist(usuario);
    	return;
    }
}
