package com.springfilms.demo.model;

import java.util.Arrays;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "titulo")
	private String title;

	@Column
	private String[] genres;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private boolean isAdult;

	@OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
	private Set<Rating> ratingsSet = new HashSet<>();

	public long getId() {
		return id;
	}

	public Film() {
		super();
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

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	public Set<Rating> getRatingsSet() {
		return ratingsSet;
	}

	public void setRatingsSet(Set<Rating> ratingsSet) {
		this.ratingsSet = ratingsSet;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", genres=" + Arrays.toString(genres) + ", date=" + date
				+ ", isAdult=" + isAdult + "]";
	}

}
