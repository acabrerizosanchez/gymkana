package com.proyecto.AppFilms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * 
 * @author jmcaceres
 *
 *         Clase Rating que son las puntuaciones de las peliculas.
 */
@Entity
public class Rating {

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

	@Column
	@NotNull
	@Range(min = 1, max = 5, message = "El valor de la puntuacion debe ser entre 1 y 5")
	private int score;

	@Column
	@NotNull
	private Date date;

	private String errorMessage;

	public Rating() {
	}

	public Rating(long id, User userId, Film movieId, int score) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.score = score;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
