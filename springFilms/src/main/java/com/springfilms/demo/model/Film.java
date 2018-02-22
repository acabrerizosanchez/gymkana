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

@Entity
public class Film {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "titulo")
	private String titulo;

	@Column
	private String Genero;

	@Column
	private Date añoLanzamiento;

	
	@OneToMany(mappedBy = "rated_film", fetch = FetchType.EAGER)
	private Set<Rating> ratingsSet = new HashSet<>();

	
	public String getId() {
		return id;
	}

	public Film(String titulo, String genero, Date añoLanzamiento) {
		super();
		this.titulo = titulo;
		Genero = genero;
		this.añoLanzamiento = añoLanzamiento;
	}

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public Date getAñoLanzamiento() {
		return añoLanzamiento;
	}

	public void setAñoLanzamiento(Date añoLanzamiento) {
		this.añoLanzamiento = añoLanzamiento;
	}

	public Set<Rating> getRatingsSet() {
		return ratingsSet;
	}

	public void setRatingsSet(Set<Rating> ratingsSet) {
		this.ratingsSet = ratingsSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", titulo=" + titulo + ", Genero=" + Genero + ", añoLanzamiento=" + añoLanzamiento
				+ ", ratingsSet=" + ratingsSet + "]";
	}

	
	

}
