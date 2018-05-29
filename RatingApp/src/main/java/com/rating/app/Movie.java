package com.rating.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Movie {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer movieId;
  private String title;
  private Boolean isAdult;
  private Date date;
  private ArrayList<String> genres;
  
  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setIsAdult(Boolean isAdult) {
    this.isAdult = isAdult;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public void setGenres(ArrayList<String> genres) {
    Integer min = Math.min(3, genres.size());
    this.genres = (ArrayList<String>) genres.subList(0, min);
  }
  public Integer getMovieId() {
    return movieId;
  }
  public String getTitle() {
    return title;
  }
  public Boolean getIsAdult() {
    return isAdult;
  }
  public Date getDate() {
    return date;
  }
  public ArrayList<String> getGenres() {
    return genres;
  }


}

