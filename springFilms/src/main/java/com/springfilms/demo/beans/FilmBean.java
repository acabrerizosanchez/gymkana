package com.springfilms.demo.beans;

import java.util.Arrays;

public class FilmBean {

	private String title;
	private String date;
	private String isAdult;
	private String errorMessage;
	private String[] genres;

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(String isAdult) {
		this.isAdult = isAdult;
	}

	@Override
	public String toString() {
		return "FilmBean [title=" + title + ", date=" + date + ", isAdult=" + isAdult + ", errorMessage=" + errorMessage
				+ ", genres=" + Arrays.toString(genres) + "]";
	}

}
