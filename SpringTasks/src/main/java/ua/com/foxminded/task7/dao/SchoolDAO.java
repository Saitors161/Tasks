package ua.com.foxminded.task7.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SchoolDAO {
//  private Connection connection;

  public boolean executeSQL(String sqlString) {

    Statement statement = null;
    try {
      statement = getStatement();
      statement.execute(sqlString);
      return true;

    } catch (SQLException e) {
      System.out.println("Request Error executeSQL");
      e.printStackTrace();
      return false;

    } finally {
      closeConnection(statement);
    }
  }

  public boolean createTableSQL(String sqlExecute) {
    Statement statement = null;
    boolean initial = true;
    try {
      statement = getStatement();
      statement.execute(sqlExecute);

    } catch (SQLException e) {
      initial = false;
      System.out.println("Table initialization error");
      e.printStackTrace();

    } finally {
      closeConnection(statement);
    }
    return initial;
  }

  public void closeConnection(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        System.out.println("Connection closing error ");
        e.printStackTrace();
      }
    }

  }

  public Connection getConnection() {
//    if (this.connection != null) {
//      return this.connection;
//    }
    Connection connection = null;
    Properties property = new Properties();

    InputStream streamProperties = getClass().getResourceAsStream("/connectionDB.properties");

    try {
      property.load(streamProperties);
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      connection = DriverManager.getConnection(property.getProperty("db.host"), property.getProperty("db.login"),
          property.getProperty("db.password"));
    } catch (SQLException e) {
      System.out.println("Connection failure.");
      e.printStackTrace();
    }
//    if (connectionLocal != null) {
//      this.connection = connectionLocal;
//    }
    return connection;
  }
  
  public Statement getStatement() {
    Connection connectionLocal = getConnection();
    Statement statement = null;
    if (connectionLocal != null) {
      try {
        statement = connectionLocal.createStatement();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return statement;
    
  }

}
