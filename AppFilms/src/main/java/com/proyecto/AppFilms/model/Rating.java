package com.proyecto.AppFilms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;
/**
 * 
 * @author jmcaceres
 *
 *Clase Rating que son las puntuaciones de las peliculas.
 */
@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_Id",updatable=false)
	private User user_Id;
	
	@ManyToOne
	@JoinColumn (name="film_Id",updatable=false)
	private Film film_Id;
	
	@Column
	@Range(min=1,max=5)
	private int score;

	public Rating() {
	}
	

	public Rating(long id, User user_Id, Film film_Id, int score) {
		super();
		this.id = id;
		this.user_Id = user_Id;
		this.film_Id = film_Id;
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser_Id() {
		return user_Id;
	}

	public void setUser_id(User user_Id) {
		this.user_Id = user_Id;
	}

	public Film getFilm_Id() {
		return film_Id;
	}

	public void setFilm_Id(Film film_Id) {
		this.film_Id = film_Id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
