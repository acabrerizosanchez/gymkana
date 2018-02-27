package com.proyecto.AppFilms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.proyecto.AppFilms.model.Film;


@Repository
@Transactional
public class FilmDao {

	@PersistenceContext
	private EntityManager entityManager;
    /*
     * Almacena las peliculas en la base de datos
     */
    public void create(Film film) {
    	entityManager.persist(film);
    	return;
    }
}
