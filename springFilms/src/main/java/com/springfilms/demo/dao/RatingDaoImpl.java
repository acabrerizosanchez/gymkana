package com.springfilms.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.springfilms.demo.beans.ConcreteFilmBean;
import com.springfilms.demo.model.Rating;

/*
 * Data Acces Object - Rating Class
 */
@Repository
@Transactional
public class RatingDaoImpl {

	private static final Logger logger = Logger.getLogger(RatingDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Store a Rating on BBDD
	 */
	public void create(Rating rating) {
		entityManager.persist(rating);
		return;
	}

	/*
	 * Obtiene la pelicula mejor valorada en un rango de tiempo indicado
	 */
	public ConcreteFilmBean getBestOrWorstRatedMovieThroughConcreteTime(String dateStringStart, String dateStringEnd,
			String report) {

		String startDateForQuery = "'" + dateStringStart + "'";
		String endDateForQuery = "'" + dateStringEnd + "'";
		String orderType = null;

		ConcreteFilmBean result = new ConcreteFilmBean();

		switch (report) {
		case "hot":
			orderType = "desc";
			break;

		case "cold":
			orderType = "asc";
			break;

		default:
			break;
		}

		String query = "select avg(score) as Rating,film from Rating where (date > " + startDateForQuery
				+ " and date < " + endDateForQuery + " ) group by film order by rating " + orderType + " limit 0,1";

		try {

			@SuppressWarnings("unchecked")
			List<Object[]> lista = entityManager.createNativeQuery(query).getResultList();

			for (Object[] item : lista) {
				Number campo1 = (Number) item[0];
				Number campo2 = (Number) item[1];

				result.setTotalRate(campo1.toString());
				result.setId(campo2.longValue());

			}
		} catch (Exception e) {
			logger.info(e);
		}

		return result;

	}

}
