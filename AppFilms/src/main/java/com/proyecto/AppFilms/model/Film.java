package com.proyecto.AppFilms.model;

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
 *Clase Film que guarda las peliculas
 */
@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@Column(name="genres")
	private String [] genres;

	@OneToMany(mappedBy="film_Id", fetch= FetchType.EAGER)	
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

	public Film(long id, String title, String date, String[] genres) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.genres = genres;
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
	
	
}
