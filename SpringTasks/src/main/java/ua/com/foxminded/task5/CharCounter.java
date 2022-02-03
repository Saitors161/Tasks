package ua.com.foxminded.task5;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter implements CounterBehavior {

  public String formCharCountToString(String inputString) {
    if (inputString==null) {
      return "";
    }
    
    StringBuilder request = new StringBuilder();

    Map<String, Integer> letterAndAmount = formMapDataCounting(inputString);

    request = formRequestString(letterAndAmount, request);

    return request.toString();
  }

  private Map<String, Integer> formMapDataCounting(String inputString) {
    char[] charArray = inputString.toCharArray();

    Map<String, Integer> letterAndAmount = new LinkedHashMap<>();
    for (char symbol : charArray) {
      Integer amount = 0;
      String letter = String.valueOf(symbol);
      amount = letterAndAmount.get(letter);
      if (amount == null) {
        amount = 0;
      }
      letterAndAmount.put(letter, amount + 1);
    }
    return letterAndAmount;

  }

  private StringBuilder formRequestString(Map<String, Integer> letterAndAmount, StringBuilder request) {
    letterAndAmount.forEach((letter, amount) -> {
      request.append(letter + ":" + amount + System.lineSeparator());
    });
    return request;

  }
}
