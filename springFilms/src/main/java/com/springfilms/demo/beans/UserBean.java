package com.springfilms.demo.beans;

/**
 * Bean utilizado como enlace entre el Json de entrada y la capa de persistencia.
 * Utilizado para la validacion de los campos
 * 
 * @author acosanchez
 *
 */
public class UserBean {

	private long id;
	private String name;
	private String surname;
	private String birthDate;
	private String errorMessage;

	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
				+ ", errorMessage=" + errorMessage + "]";
	}
	

}
