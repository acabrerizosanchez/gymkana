package com.springfilms.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;




@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name="user_id", updatable=false)
	private User rated_user;

	@ManyToOne
	@JoinColumn(name="post_id", updatable=false)
	private Film rated_film;

	@Column
	@CreationTimestamp
	private Date fecha;
	
	@Column
	private int rate;

	public Rating(int rate) {
		super();
		this.rate = rate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getRated_user() {
		return rated_user;
	}

	public void setRated_user(User rated_user) {
		this.rated_user = rated_user;
	}

	public Film getRated_film() {
		return rated_film;
	}

	public void setRated_film(Film rated_film) {
		this.rated_film = rated_film;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rated_user=" + rated_user + ", rated_film=" + rated_film + ", fecha=" + fecha
				+ ", rate=" + rate + "]";
	}

	

}
