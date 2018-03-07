package com.proyecto.appfilms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.proyecto.appfilms.model.Film;

@Repository
@Transactional
public class FilmDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 * @param film
	 * 
	 *            Almacena las peliculas en la base de datos
	 */
	public void create(Film film) {
		entityManager.persist(film);
		return;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * 		Este método devuelve una pelicula mediante el id, esto se utilizará
	 *         para obtener el título de la pelicula mas y menos votada.
	 */
	public Film getById(long id) {
		return entityManager.find(Film.class, id);
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 * 		Este método obtiene el id de la pelicula mas votada en un periodo de
	 *         tiempo.
	 */
	public long getHotMovie(String startDate, String endDate) {
		long idMovie = 0;
		Query query = entityManager
				.createNativeQuery("SELECT avg(score) as rating,movieId FROM AppFilms.Rating where( date > " + startDate
						+ " and date < " + endDate + ") group by movieId  order by rating desc limit 0,1");

		List<Object[]> peliculas = query.getResultList();

		for (Object[] pelicula : peliculas) {
			Number id = (Number) pelicula[1];
			idMovie = id.longValue();

		}
		return idMovie;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 * 		Este método obtiene el id de la pelicula menos votada en un periodo
	 *         de tiempo.
	 */
	public long getColdMovie(String startDate, String endDate) {
		long idMovie = 0;
		Query query = entityManager
				.createNativeQuery("SELECT avg(score) as rating,movieId FROM AppFilms.Rating where( date > " + startDate
						+ " and date < " + endDate + ") group by movieId  order by rating asc limit 0,1");

		List<Object[]> peliculas = query.getResultList();

		for (Object[] pelicula : peliculas) {

			Number id = (Number) pelicula[1];
			idMovie = id.longValue();

		}
		return idMovie;
	}

}
