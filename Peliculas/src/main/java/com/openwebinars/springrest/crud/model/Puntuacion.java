package com.openwebinars.springrest.crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Puntuacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private long userId;
	@Column
	private long movieId;
	@Column
	private long score;
	@Column
	private Date date;
	
	
	public long getMovieId() {
		return movieId;
	}


	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}


	public long getScore() {
		return score;
	}


	public void setScore(long score) {
		this.score = score;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}


	public Puntuacion() {}


	public Puntuacion(long id, long userId, long movieId, long score, Date date) {
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.score = score;
		this.date = date;
	}
	
	public Puntuacion(long userId, long movieId, long score, Date date) {
		this.userId = userId;
		this.movieId = movieId;
		this.score = score;
		this.date = date;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + (int) (movieId ^ (movieId >>> 32));
		result = prime * result + (int) (score ^ (score >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puntuacion other = (Puntuacion) obj;
		if (id != other.id)
			return false;
		if (score != other.score)
			return false;
		if (userId != other.userId)
			return false;
		if (movieId != other.movieId)
			return false;
		return true;
	}
	
	
	

}
