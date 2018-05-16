package com.openwebinars.springrest.crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String title;
	@Column
	private Date date;
	@Column
	private boolean isAdult;
	@Column
	private String[] genres;
	
	public Pelicula() {}


	public Pelicula(long id, String title, Date date, boolean isAdult, String[] genres) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.isAdult = isAdult;
		this.genres = genres;
	}
	
	public Pelicula(String title, Date date, boolean isAdult, String[] genres) {
		this.title = title;
		this.date = date;
		this.genres = genres;
	}


	public boolean isAdult() {
		return isAdult;
	}


	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}


	public String[] getGenres() {
		return genres;
	}


	public void setGenres(String[] genres) {
		this.genres = genres;
	}


	public String getTitle() {
		return title;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String geTitle() {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Pelicula other = (Pelicula) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	

}
