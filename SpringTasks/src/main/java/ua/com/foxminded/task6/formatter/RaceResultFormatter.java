package ua.com.foxminded.task6.formatter;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import ua.com.foxminded.task6.Racer;
import ua.com.foxminded.task6.ResultRacer;
import ua.com.foxminded.task6.dao.RacerDAO;
import ua.com.foxminded.task6.services.RaceResultService;

public class RaceResultFormatter {
  private RaceResultService resultRace;

  public RaceResultFormatter() {
    this.resultRace = new RaceResultService(new RacerDAO());
  }

  public String getResultToString(int countRacerAfterQ1) {
    List<ResultRacer> arrayResultRacers = resultRace.formResultRace();
    String result = "";
    if (arrayResultRacers != null && !arrayResultRacers.isEmpty()) {
      Map<String, Integer> maxCountSymbolData = countingMaxCountSymbol(arrayResultRacers);
      int maxLengthTeam = maxCountSymbolData.get("maxLengthTeam");
      int maxLengthNamePlusLastName = maxCountSymbolData.get("maxLengthNamePlusLastName");
      AtomicInteger place = new AtomicInteger();
      place.addAndGet(1);

      result = arrayResultRacers.stream().map(resultRacer -> {
        String resultString = formPlaceNameAndLastName(resultRacer.getRacer(), place, maxLengthNamePlusLastName)
                              + formTeam(maxLengthTeam, resultRacer.getRacer()) 
                              + formTimeResultString(resultRacer);
        if (place.get() == countRacerAfterQ1) {
          resultString = resultString + System.lineSeparator() + (multiplicationSymbol(resultString.length(), "-"));
        }
        place.addAndGet(1);
        return resultString;
      }).collect(Collectors.joining(System.lineSeparator()));

    }
    return result;
  }

  private String formTimeResultString(ResultRacer resultRacer) {
    return formStringTimeResultRacer(resultRacer.getDuration());
  }

  private String formPlaceNameAndLastName(Racer racer, AtomicInteger place, int maxLengthNamePlusLastName) {
    String nameAndLastName = racer.getName() + " " + racer.getLastName();
    String placePlusNameAndLastName = place + ". " + nameAndLastName;
    String placeNameAndLastNameFormat = String.format("%-" + maxLengthNamePlusLastName + "s", placePlusNameAndLastName);
    return placeNameAndLastNameFormat;
  }

  private String formTeam(int maxLengthTeam, Racer racer) {
    return String.format("%-" + maxLengthTeam + "s", "|" + racer.getTeam()) + "|";
  }

  private Map<String, Integer> countingMaxCountSymbol(List<ResultRacer> arrayResultRacers) {
    int maxLengthTeam = 0;
    int maxLengthNamePlusLastName = 0;
    for (ResultRacer resultRacer : arrayResultRacers) {
      Racer racer = resultRacer.getRacer();
      int lengthNamePlusLastName = (racer.getName() + " " + racer.getLastName()).length();

      if (lengthNamePlusLastName > maxLengthNamePlusLastName) {
        maxLengthNamePlusLastName = lengthNamePlusLastName + (("" + arrayResultRacers.size()).length() + 3);
      }

      int teamLength = racer.getTeam().length();
      if (teamLength > maxLengthTeam) {
        maxLengthTeam = teamLength + 3;
      }
    }
    Map<String, Integer> lenghtsStrings = new HashMap<>();
    lenghtsStrings.put("maxLengthNamePlusLastName", maxLengthNamePlusLastName);
    lenghtsStrings.put("maxLengthTeam", maxLengthTeam);

    return lenghtsStrings;
  }

  private static String formStringTimeResultRacer(Duration duration) {
    return (duration.toString().substring(2, duration.toString().length() - 1)).replace("M", ":");
  }

  private String multiplicationSymbol(int quantity, String string) {

    StringBuilder sb = new StringBuilder();
    while (sb.length() < quantity) {
      sb.append(string);
    }

    return sb.toString();
  }

}
