package ua.com.foxminded.task5;

import java.util.HashMap;
import java.util.Map;

public class Cacher implements CounterBehavior {
  private Map<String, String> stringAndCharRequest;
  private CharCounter charCounter;

  public Cacher(CharCounter charCounter) {
    this.stringAndCharRequest = new HashMap<String, String>();
    this.charCounter = charCounter;
  }

  @Override
  public String formCharCountToString(String inputString) {
    String response = getCashingValue(inputString);

    if (response == null) {
      response = this.charCounter.formCharCountToString(inputString);
      setCashingValue(inputString, response);
    }
    return response;
  }

  String getCashingValue(String inputString) {
    return this.stringAndCharRequest.get(inputString);
  }
  
  void setCashingValue(String inputString, String value) {
    if (inputString==null) {
      return;
    }
    this.stringAndCharRequest.put(inputString, value);
  }
}
