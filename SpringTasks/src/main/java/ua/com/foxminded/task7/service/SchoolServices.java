package ua.com.foxminded.task7.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;
import ua.com.foxminded.task7.dao.SchoolDAO;

public class SchoolServices {
  private SchoolDAO schoolDAO;
  private boolean tableInitialized;

  public SchoolServices(SchoolDAO schoolDAO) {
    this.schoolDAO = schoolDAO;
  }

  public void initializedTable() {
    String pathInitialTable = "/CreateTablesSchool";
    String executeSQL = "";

    InputStream streamInitialTable = getClass().getResourceAsStream(pathInitialTable);

    try (BufferedReader bufferedReaderAbbreviations = new BufferedReader(new InputStreamReader(streamInitialTable))) {
      executeSQL = bufferedReaderAbbreviations.lines().collect(Collectors.joining(System.lineSeparator()));
    } catch (IOException e) {
      System.out.println("File with abbreviation not found");
    }

    if (!executeSQL.equals("")) {
      this.tableInitialized = this.schoolDAO.createTableSQL(executeSQL);
    }
  }

  public boolean isTableInitialized() {
    return this.tableInitialized;
  }

  @Override
  public int hashCode() {
    return Objects.hash(schoolDAO);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SchoolServices other = (SchoolServices) obj;
    return Objects.equals(schoolDAO, other.schoolDAO);
  }

}
