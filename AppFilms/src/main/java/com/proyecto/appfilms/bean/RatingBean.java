package com.proyecto.appfilms.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * 
 * @author jmcaceres
 *
 *         Clase para poder validar la fecha
 */
public class RatingBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	private String userId;

	@NotEmpty
	private String movieId;

	@NotEmpty
	@Range(min = 1, max = 5)
	private String score;

	@NotEmpty
	private String date;

	private String errorMessage;

	public RatingBean() {
		super();
	}

	public RatingBean(long id, String userId, String movieId, String score, String date, String errorMessage) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
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
