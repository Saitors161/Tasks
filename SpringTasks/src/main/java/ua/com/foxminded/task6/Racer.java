package ua.com.foxminded.task6;

import java.util.Objects;

public class Racer {

  private String name;
  private String lastName;
  private String abbreviation;
  private String team;

  public Racer(String name, String lastName, String team) {
    this.name = name;
    this.lastName = lastName;
    this.team = team;
    setAbbreviation();
  }

  public Racer(String name, String lastName, String team, String abbreviation) {
    this.name = name;
    this.lastName = lastName;
    this.team = team;
    this.abbreviation = abbreviation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation() {
    String abbreviation = "";
    if (needReplaceAbbreviation()) {
      if (this.name != null && !this.name.isEmpty()) {
        abbreviation = abbreviation + Character.toString(this.name.charAt(0)).toUpperCase();
      }
      if (this.lastName != null && !this.lastName.isEmpty()) {
        abbreviation = abbreviation + Character.toString(this.lastName.charAt(0)).toUpperCase();
      }
      if (this.team != null && !this.team.isEmpty()) {
        abbreviation = abbreviation + Character.toString(this.team.charAt(0)).toUpperCase();
      }
    }
    this.abbreviation = abbreviation;

  }

  public void setAbbreviation(String abbreviation) {

    this.abbreviation = abbreviation;

  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  @Override
  public int hashCode() {
    return Objects.hash(abbreviation, lastName, name, team);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Racer other = (Racer) obj;
    return Objects.equals(abbreviation, other.abbreviation) && Objects.equals(lastName, other.lastName)
        && Objects.equals(name, other.name) && Objects.equals(team, other.team);
  }

  private boolean needReplaceAbbreviation() {
    if (this.abbreviation == null) {
      return true;
    } else if (this.name.toUpperCase().charAt(0) != this.abbreviation.charAt(0)) {
      return true;
    } else if (this.lastName.toUpperCase().charAt(0) != this.abbreviation.charAt(1)) {
      return true;
    } else if (this.team.toUpperCase().charAt(0) != this.abbreviation.charAt(2)) {
      return true;
    }

    return false;

  }

}
