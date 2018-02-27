package com.proyecto.AppFilms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.proyecto.AppFilms.model.Rating;

@Repository
@Transactional
public class RatingDao {

	@PersistenceContext
	private EntityManager entityManager;
    /*
     * Almacena la puntuación en la base de datos
     */
    public void create(Rating rating) {
    	entityManager.persist(rating);
    	return;
    }
}
