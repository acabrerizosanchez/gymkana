package com.rating.app;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  private String userId;
  private String movieId;
  private Integer score;
  private Date date;
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getMovieId() {
    return movieId;
  }
  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

}
