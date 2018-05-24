package com.antonio.romero;

import java.util.ArrayList;
import java.util.Date;

public class Film {
  private long id;
  private String title;
  private Boolean isAdult;
  private Date date;
  private ArrayList<String> genres;

  public Film(long id, String title, Boolean isAdult, Date date, ArrayList<String> genres) {
    this.id = id;
    this.title = title;
    this.isAdult = isAdult;
    this.date = date;
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

  @Override
  public String toString() {
    return "Pelicula [title=" + title + ", isAdult=" + isAdult + ", date=" + date + ", genres="
            + genres + "]";
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
    Film other = (Film) obj;
    if (id != other.id)
      return false;
    return true;
  }


	

}

