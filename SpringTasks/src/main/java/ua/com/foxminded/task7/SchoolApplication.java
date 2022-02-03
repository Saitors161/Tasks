package ua.com.foxminded.task7;

import ua.com.foxminded.task7.dao.SchoolDAO;
import ua.com.foxminded.task7.service.SchoolServices;

public class SchoolApplication {
  public static void main(String[] args) {

    SchoolDAO schoolDAO = new SchoolDAO();
    DataGenerator dataGenerator = new DataGenerator(schoolDAO);
    SchoolServices schoolServices = new SchoolServices(schoolDAO);
    schoolServices.initializedTable();
    dataGenerator.createTestData();

  }
}
