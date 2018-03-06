package com.proyecto.AppFilms.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.proyecto.AppFilms.model.Film;
import com.proyecto.AppFilms.model.User;

/**
 * 
 * @author jmcaceres
 *
 *	Clase para poder validar la fecha
 */
public class RatingBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId", updatable = false)
	@NotNull
	private User userId;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "movieId", updatable = false)
	private Film movieId;

	@NotNull
	@Range(min = 1, max = 5, message = "El valor de la puntuacion debe ser entre 1 y 5")
	private int score;

	@NotNull
	private String date;

	private String errorMessage;

	public RatingBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Film getMovieId() {
		return movieId;
	}

	public void setMovieId(Film movieId) {
		this.movieId = movieId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "RatingBean [id=" + id + ", userId=" + userId + ", movieId=" + movieId + ", score=" + score + ", date="
				+ date + ", errorMessage=" + errorMessage + "]";
	}

}
