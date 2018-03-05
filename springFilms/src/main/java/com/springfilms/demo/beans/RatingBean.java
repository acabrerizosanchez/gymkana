package com.springfilms.demo.beans;

public class RatingBean {

	private long id;
	private String score;
	private String userId;
	private String movieId;
	private String date;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@Override
	public String toString() {
		return "RatingBean [id=" + id + ", score=" + score + ", userId=" + userId + ", movieId=" + movieId + ", date="
				+ date + ", errorMessage=" + errorMessage + "]";
	}

}
