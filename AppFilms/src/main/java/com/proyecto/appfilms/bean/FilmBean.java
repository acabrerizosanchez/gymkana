package com.proyecto.appfilms.bean;

import java.util.Arrays;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author jmcaceres
 *
 *         Clase para poder validar la fecha
 */
public class FilmBean {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	private String title;

	@NotEmpty
	private String date;

	@NotEmpty
	private String[] genres;

	private String isAdult;

	private String errorMessage;

	public FilmBean(long id, String title, String date, String[] genres, String isAdult, String errorMessage) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.genres = genres;
		this.isAdult = isAdult;
		this.errorMessage = errorMessage;
	}

	public FilmBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(String isAdult) {
		this.isAdult = isAdult;
	}

	@Override
	public String toString() {
		return "FilmBean [id=" + id + ", title=" + title + ", date=" + date + ", genres=" + Arrays.toString(genres)
				+ ", isAdult=" + isAdult + ", errorMessage=" + errorMessage + "]";
	}

}
