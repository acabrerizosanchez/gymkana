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
  private Integer ratingId;
  private Integer userId;
  private Integer movieId;
  private Integer score;
  private Date date;
  
  public Integer getRatingId() {
    return ratingId;
  }
  public void setRatingId(Integer ratingId) {
    this.ratingId = ratingId;
  }
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public Integer getMovieId() {
    return movieId;
  }
  public void setMovieId(Integer movieId) {
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
