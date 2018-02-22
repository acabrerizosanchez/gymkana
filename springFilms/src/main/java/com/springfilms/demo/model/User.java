package com.springfilms.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/*
 * ENTIDAD USUARIO
 */
@Entity
public class User {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "nombre")
	private String nombre;

	@Column
	private String apellido;

	@Column
	private Date fechaNacimiento;

	@OneToMany(mappedBy = "rated_user", fetch = FetchType.EAGER)
	private Set<Rating> ratingsSet = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<Rating> getRatingsSet() {
		return ratingsSet;
	}

	public void setRatingsSet(Set<Rating> ratingsSet) {
		this.ratingsSet = ratingsSet;
	}

	public User(String nombre, String apellido, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", ratingsSet=" + ratingsSet + "]";
	}

}
