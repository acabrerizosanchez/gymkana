package com.antonio.romero;

import java.util.Date;

public class User {
  private long id;
  private String name;
  private String surname;
  private Date birthDate;

  public User(long id, String name, String surname, Date birthDate) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  @Override
  public String toString() {
    return "Usuario [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + "]";
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
    User other = (User) obj;
    if (id != other.id)
      return false;
    return true;
  }

  


}
