package com.proyecto.AppFilms.model;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotNull
	@NotBlank
	private String title;

	@Column(name = "date")
	@NotNull
	private Date date;

	@Column(name = "genres")
	@NotNull
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

	public Film(long id, String title, Date date, String[] genres, String isAdult) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.genres = genres;
		this.isAdult = isAdult;
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
