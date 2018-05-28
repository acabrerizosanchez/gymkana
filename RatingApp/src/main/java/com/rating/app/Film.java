package com.rating.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Film {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  private String title;
  private Boolean isAdult;
  private Date date;
  private ArrayList<String> genres;
  
  public void setId(long id) {
    this.id = id;
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
    this.genres = genres;
  }
  public long getId() {
    return id;
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

