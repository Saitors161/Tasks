package ua.com.foxminded.task7.service;

import java.util.Arrays;
import java.util.List;

import ua.com.foxminded.task7.Course;
import ua.com.foxminded.task7.dao.SchoolDAO;

public class CourseServices {
  private SchoolDAO schoolDAO;

  public CourseServices(SchoolDAO schoolDAO) {
    this.schoolDAO = schoolDAO;
  }

  public boolean createCoursesInDB() {

    List<Course> courses = Arrays.asList(new Course("Math", "Mathematics is ...."),
                                         new Course("Biology", "Biology is ...."),
                                         new Course("Drawing", "Drawing is ...."),
                                         new Course("Chemistry", "Chemistry is ...."),
                                         new Course("Geography", "Geography is ...."),
                                         new Course("Geometry", "Geometry is ...."),
                                         new Course("History", "History is ...."),
                                         new Course("Literature", "Literature is ...."),
                                         new Course("Music", "Music is ...."),
                                         new Course("Physics", "Physics is ...."));

    StringBuilder insertSQL = new StringBuilder();
    for (Course course : courses) {
      insertSQL.append(String.format("INSERT INTO courses (name,description) VALUES ('%s', '%s');",
          course.getCourseName(), course.getCourseDescription()));
    }
    return schoolDAO.executeSQL(insertSQL.toString());
  }

}
