package com.proyecto.appfilms.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author jmcaceres
 *
 *         Clase Film que guarda las peliculas
 */
@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "date")

	private Date date;

	@Column(name = "genres")

	private String[] genres;

	@Column(name = "isAdult")
	private String isAdult;

	private String errorMessage;

	@OneToMany(mappedBy = "movieId", fetch = FetchType.EAGER)
	private Set<Rating> rating = new HashSet<>();

	public Set<Rating> getRating() {
		return rating;
	}

	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}

	public Film() {
	}

	public Film(long id) {
		super();
		this.id = id;
	}

	public Film(String title, Date date, String[] genres, String errorMessage) {
		super();
		this.title = title;
		this.date = date;
		this.genres = genres;
		this.errorMessage = errorMessage;
	}

	public Film(String title, Date date, String[] genres, String isAdult, String errorMessage) {
		super();
		this.title = title;
		this.date = date;
		this.genres = genres;
		this.isAdult = isAdult;
		this.errorMessage = errorMessage;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

}
