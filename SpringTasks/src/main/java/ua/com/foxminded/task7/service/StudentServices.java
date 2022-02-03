package ua.com.foxminded.task7.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.com.foxminded.task7.DataGenerator;
import ua.com.foxminded.task7.Group;
import ua.com.foxminded.task7.Student;
import ua.com.foxminded.task7.dao.SchoolDAO;

public class StudentServices {

  private SchoolDAO schoolDAO;
  private DataGenerator dataGenerator;
  private GroupServices groupServices;

  public boolean createStudentsInDB() {
    List<String> names = createNames();
    List<String> lastNames = createLastNames();
    Map<Integer, List<Student>> groupsAndStudents = new HashMap<>();

    List<Group> groups = this.groupServices.getAllGroups();
    List<Student> allStudents = new ArrayList<>();
    for (int i = 0; i < 200; i++) {
      Student student = new Student(i, pickGroupForStudent(groupsAndStudents, groups),
          names.get(this.dataGenerator.generateRandomNumber(0, 19)),
          lastNames.get(this.dataGenerator.generateRandomNumber(0, 19)));
      if (groupsAndStudents.containsKey(student.getGroupID())) {
        groupsAndStudents.get(student.getGroupID()).add(student);
      } else if (student.getGroupID() != 0) {
        List<Student> listStudents = new ArrayList<>();
        listStudents.add(student);
        groupsAndStudents.put(student.getGroupID(), listStudents);
      }
      allStudents.add(student);
    }

    groupsAndStudents = checkAndChangeCountStudentsInGroups(groupsAndStudents, groups);

    int sum = 0;
    for (Map.Entry<Integer, List<Student>> pair : groupsAndStudents.entrySet()) {
      System.out.println(
          pair.getKey() + System.lineSeparator() + pair.getValue() + System.lineSeparator() + pair.getValue().size());
      sum = sum + pair.getValue().size();
    }
    System.out.println(sum);
    return addStudents(allStudents);
  }

  public boolean addStudents(List<Student> students) {
    StringBuilder stringBuilderSQL = new StringBuilder();
    for (Student student : students) {
      stringBuilderSQL.append(formStringAddStudent(student));
    }
    return schoolDAO.executeSQL(stringBuilderSQL.toString());
  }

  private Object formStringAddStudent(Student student) {

    return String.format("INSERT INTO students(groupId,firstName,lastName) VALUES (%d, '%s', '%s');",
        student.getGroupID(), student.getFirstName(), student.getLastName());
  }

  private Map<Integer, List<Student>> checkAndChangeCountStudentsInGroups(Map<Integer, List<Student>> groupsAndStudents,
      List<Group> groups) {
    List<Student> studentsInSmallGroups = new ArrayList<>();
    groupsAndStudents.forEach((group, listStudents) -> {
      if (listStudents.size() < 10) {
        studentsInSmallGroups.addAll(listStudents);
      }
    });

    if (!studentsInSmallGroups.isEmpty()) {
      for (Student student : studentsInSmallGroups) {
        int group = pickGroupForStudent(groupsAndStudents, groups);
        List<Student> listStudents = groupsAndStudents.get(group);
        listStudents.add(student);
        student.setGroupID(group);
      }

    }

    return groupsAndStudents;
  }

  private int pickGroupForStudent(Map<Integer, List<Student>> groupsAndStudents, List<Group> groups) {

    List<Integer> groupAccepted = new ArrayList<>();
    for (Group group : groups) {
      groupAccepted.add(group.getGroupID());
    }

    for (; !groupAccepted.isEmpty();) {
      int idGroup = groupAccepted.get(this.dataGenerator.generateRandomNumber(0, groupAccepted.size() - 1));
      if (groupsAndStudents.containsKey(idGroup)) {
        if (groupsAndStudents.get(idGroup).size() == 30) {
          groupAccepted.remove(groupAccepted.indexOf(idGroup));
        } else {
          return idGroup;
        }

      } else {
        return idGroup;
      }
    }
    return 0;
  }

  private List<String> createLastNames() {
    return Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
        "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson",
        "Martin");
  }

  private List<String> createNames() {
    return Arrays.asList("James", "Robert", "John", "Michael", "Mary", "Charles", "Elizabeth", "Paul", "Lisa", "Betty",
        "Margaret", "Sandra", "Kimberly", "Joshua", "Andrew", "Thomas", "Donna", "Nancy", "Christopher", "Mark");
  }

  public List<Student> getAllStudents() {
    List<Student> result = new ArrayList<>();

    Statement statement = schoolDAO.getStatement();
    try {
      ResultSet resultSet = statement.executeQuery("SELECT id,groupId,firstName,lastName FROM students");

      while (resultSet.next()) {
        Student student = new Student(resultSet.getInt("student_id"), resultSet.getInt("group_id"),
            resultSet.getString("first_name"), resultSet.getString("last_name"));
        result.add(student);
      }

    } catch (SQLException e) {
      System.out.println("Request Error (getAllStudents)");
      e.printStackTrace();

    } finally {
      this.schoolDAO.closeConnection(statement);
    }

    return result;
  }

  public List<Student> getStudentByCourse(String nameCourse) {

    List<Student> students = new ArrayList<>();
    if (nameCourse.equals("")) {
      return students;
    }

    Statement statement = schoolDAO.getStatement();
    try {
      ResultSet resultSet = statement.executeQuery(String.format(
          "SELECT studentId FROM students_courses WHERE courseId IN (SELECT id FROM courses WHERE name = '%s')",
          nameCourse));

      while (resultSet.next()) {
        Student student = new Student(resultSet.getInt("id"), resultSet.getInt("groupId"),
            resultSet.getString("firstName"), resultSet.getString("lastName"));
        students.add(student);
      }

    } catch (SQLException e) {
      System.out.println("Request Error (getAllGroups)");
      e.printStackTrace();

    } finally {
      this.schoolDAO.closeConnection(statement);
    }

    return students;

  }

  public StudentServices(SchoolDAO schoolDAO, DataGenerator dataGenerator, GroupServices groupServices) {
    this.schoolDAO = schoolDAO;
    this.dataGenerator = dataGenerator;
    this.groupServices = groupServices;
  }

}
