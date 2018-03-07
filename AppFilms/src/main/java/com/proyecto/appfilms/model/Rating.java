package com.proyecto.appfilms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private User userId;

	@ManyToOne
	@JoinColumn(name = "movieId", updatable = false)
	private Film movieId;

	@Column
	private int score;

	@Column
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

	public Rating(long id, User userId, Film movieId, int score, Date date, String errorMessage) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.score = score;
		this.date = date;
		this.errorMessage = errorMessage;
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
