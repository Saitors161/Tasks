package ua.com.foxminded.task7;

import java.util.Random;

import ua.com.foxminded.task7.dao.SchoolDAO;
import ua.com.foxminded.task7.service.CourseServices;
import ua.com.foxminded.task7.service.GroupServices;
import ua.com.foxminded.task7.service.StudentServices;

public class DataGenerator {
  private SchoolDAO schoolDAO;
  private Random random = new Random();

  public boolean createTestData() {
    GroupServices groupServices = new GroupServices(schoolDAO, this);
    StudentServices studentServices = new StudentServices(schoolDAO, this, groupServices);
    CourseServices courseServices = new CourseServices(schoolDAO);
    return groupServices.createGroupsInDB() && courseServices.createCoursesInDB()
        && studentServices.createStudentsInDB();
  }

  public int generateRandomNumber(int min, int max) {

    return random.nextInt(max - min + 1) + min;

  }

  public DataGenerator(SchoolDAO schoolDAO) {
    this.schoolDAO = schoolDAO;
  }
}
