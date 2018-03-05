package com.springfilms.demo.model;

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

/*
 * ENTIDAD USUARIO
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Temporal(TemporalType.DATE)
	@Column
	private Date birthDate;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Rating> ratingsSet = new HashSet<>();

	public long getId() {
		return id;
	}

	public User() {
		super();
	}

	public User(String nombre, String apellido, Date fechaNacimiento) {
		super();
		this.name = nombre;
		this.surname = apellido;
		this.birthDate = fechaNacimiento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + "]";
	}

	

}
