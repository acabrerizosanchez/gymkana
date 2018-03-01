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


/**
 * 
 * @author jmcaceres
 *
 *Clase User que guarda a los usuarios.
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column
	private Date birthDate;

	@OneToMany(mappedBy="user_Id", fetch= FetchType.EAGER)	
	private Set<Rating> rating = new HashSet<>();
	
	
	
	public Set<Rating> getRating() {
		return rating;
	}



	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}



	public User() {
	}
	
	



	public User(long id) {
		super();
		this.id = id;
	}



	public User(long id, String name, String lastName, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
