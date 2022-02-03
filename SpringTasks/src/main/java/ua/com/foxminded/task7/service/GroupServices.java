package ua.com.foxminded.task7.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.task7.DataGenerator;
import ua.com.foxminded.task7.Group;
import ua.com.foxminded.task7.dao.SchoolDAO;

public class GroupServices {

  private SchoolDAO schoolDAO;
  private DataGenerator dataGenerator;

  public List<Group> getAllGroups() {
    List<Group> result = new ArrayList<>();

    Statement statement = schoolDAO.getStatement();
    try {

      ResultSet resultSet = statement.executeQuery("SELECT id,name FROM school.groups");

      while (resultSet.next()) {
        Group group = new Group(resultSet.getInt("id"), resultSet.getString("name"));
        result.add(group);
      }

    } catch (SQLException e) {
      System.out.println("Request Error (getAllGroups)");
      e.printStackTrace();

    } finally {
      this.schoolDAO.closeConnection(statement);
    }

    return result;
  }

  public boolean createGroupsInDB() {

    StringBuilder insertSQL = new StringBuilder();
    for (int idGroup = 1; idGroup <= 10; idGroup++) {
      Group group = new Group(idGroup, generateNameForGroup());
      insertSQL.append(String.format("INSERT INTO groups (name) VALUES ('%s');", group.getGroupName()));
    }
    return schoolDAO.executeSQL(insertSQL.toString());

  }
  
  public List<Group> getGroupsWithLessOrEqualsCount(int count) {
    List<Group> groups = new ArrayList<>();
    if (count < 10) {
      return groups;
    }

    Statement statement = schoolDAO.getStatement();
    try {
      ResultSet resultSet = statement.executeQuery(String.format(
          "SELECT id, name FROM groups WHERE id IN (SELECT groupID FROM students group by groupId having count(id) <= %d)",
          count));

      while (resultSet.next()) {
        Group group = new Group(resultSet.getInt("id"), resultSet.getString("name"));
        groups.add(group);
      }

    } catch (SQLException e) {
      System.out.println("Request Error (getAllGroups)");
      e.printStackTrace();

    } finally {
      this.schoolDAO.closeConnection(statement);
    }

    return groups;

  }

  private String generateNameForGroup() {
    int max = 90;
    int min = 65;
    char c1 = (char) dataGenerator.generateRandomNumber(min, max);
    char c2 = (char) dataGenerator.generateRandomNumber(min, max);
    int n1 = dataGenerator.generateRandomNumber(min, max);
    int n2 = dataGenerator.generateRandomNumber(min, max);

    return "" + c1 + c2 + "-" + n1 + n2;
  }

  public GroupServices(SchoolDAO schoolDAO, DataGenerator dataGenerator) {
    this.schoolDAO = schoolDAO;
    this.dataGenerator = dataGenerator;
  }

}
