package ua.com.foxminded.task7;

import java.util.Objects;

public class Student {

  private int studentID;
  private int groupID;
  private String firstName;
  private String lastName;

  public Student(int studentID, int groupID, String firstName, String lastName) {
    this.studentID = studentID;
    this.groupID = groupID;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getStudentID() {
    return studentID;
  }

  public void setStudentID(int studentID) {
    this.studentID = studentID;
  }

  public int getGroupID() {
    return groupID;
  }

  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, groupID, lastName, studentID);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Student other = (Student) obj;
    return Objects.equals(firstName, other.firstName) && groupID == other.groupID
        && Objects.equals(lastName, other.lastName) && studentID == other.studentID;
  }

  @Override
  public String toString() {
    return "Student [studentID=" + studentID + ", groupID=" + groupID + ", firstName=" + firstName + ", lastName="
        + lastName + "]";
  }

}
