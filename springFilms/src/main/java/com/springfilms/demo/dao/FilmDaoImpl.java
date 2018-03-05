package com.springfilms.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springfilms.demo.model.Film;

@Repository
@Transactional
public class FilmDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Store film on BBDD
	 */
	public void create(Film film) {
		entityManager.persist(film);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Film> getAll() {
		return entityManager.createQuery("select p from Film p where *").getResultList();
	}

	public Film getById(long id) {
		return entityManager.find(Film.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Film> getFilmsByDate(String dateString) {
		List<Film> films = null;

		try {
			films = (List<Film>) entityManager
					.createNativeQuery("select * FROM Film where date = " + dateString, Film.class).getResultList();

		} catch (NoResultException e) {
			films = null;
		}
		return films;
	}

}
