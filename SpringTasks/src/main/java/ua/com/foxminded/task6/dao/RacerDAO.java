package ua.com.foxminded.task6.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ua.com.foxminded.task6.Racer;

public class RacerDAO {

  private DateTimeFormatter format = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd_hh:mm:ss.SSS")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();

  public List<Racer> findAllRacers()  {
    String pathAbbreviation = "/abbreviation.txt";

    InputStream streamAbbreviations = getClass().getResourceAsStream(pathAbbreviation);
    List<Racer> racers = new ArrayList<>();
    try (BufferedReader bufferedReaderAbbreviations = new BufferedReader(new InputStreamReader(streamAbbreviations))) {
      racers = bufferedReaderAbbreviations.lines().collect(Collectors.toList()).stream()
          .map(stringAbbreviation -> {
            String[] lineFull = stringAbbreviation.split("_");
            String[] nameAndLastName = lineFull[1].split(" ");
            String name = nameAndLastName[0];
            String lastName = nameAndLastName[1];
            String team = lineFull[2];
            return new Racer(name, lastName, team);
            })
          .collect(Collectors.toList());
    } catch (IOException e) {
      System.out.println("File with abbreviation not found");
    }
    return racers;

  }

  public LocalDateTime findStartTimeForRacer(Racer racer) {

    String pathStartFile = "/start.log";
    return getTimeResultRacerFromFile(pathStartFile, racer);
  }

  public LocalDateTime findFinishTimeForRacer(Racer racer) {
    String pathEndFile = "/end.log";
    return getTimeResultRacerFromFile(pathEndFile, racer);
  }

  private LocalDateTime getTimeResultRacerFromFile(String path, Racer racer) {

    InputStream streamStrings = getClass().getResourceAsStream(path);
    LocalDateTime result = null;
    try (BufferedReader bufferedReaderDataRacers = new BufferedReader(new InputStreamReader(streamStrings))) {
      result = bufferedReaderDataRacers.lines()
          .filter((string) -> (racer.getAbbreviation().equals(getFirstLetters(string)))).map(stringTime -> {
            return LocalDateTime.parse(stringTime.substring(3), this.format);
          }).findAny().orElse(null);
    } catch (IOException e) {
      System.out.println("File " + path + " not found");
    }

    return result;

  }

  private String getFirstLetters(String input) {
    return input.replaceAll("^([^\\d]+)(.*)$", "$1");
  }
}
