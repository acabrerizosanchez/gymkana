package com.antonio.romero;

import java.util.Date;

public class Rate {
  private long id;
  private String userId;
  private String movieId;
  private Integer score;
  private Date date;

  public Rate(long id, String userId, String movieId, Integer score, Date date) {
    this.id = id;
    this.userId = userId;
    this.movieId = movieId;
    this.score = score;
    this.date = date;
  }

  public long getId() {
    return id;
  }
  
  public String getUserId() {
    return userId;
  }

  public String getMovieId() {
    return movieId;
  }

  public Integer getScore() {
    return score;
  }

  public Date getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "Rating [userId=" + userId + ", movieId=" + movieId + ", score=" + score + ", date="
        + date + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
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
    Rate other = (Rate) obj;
    if (id != other.id)
      return false;
    return true;
  }



}
