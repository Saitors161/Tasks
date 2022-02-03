package ua.com.foxminded.task5;

import java.util.Scanner;

public class CharCounterApplicationUse {

  public static void main(String[] args) {
    Scanner inputScan = new Scanner(System.in);
    CharCounterApplication app = new CharCounterApplication();
    CharCounter charCounter = new CharCounter();
    Cacher charCounterCache = new Cacher(charCounter);

    for (boolean repeat = true; repeat == true;) {

      String inputString = app.getInputString(inputScan);

      if (inputString == "") {
        System.out.println("Input string is empty");
      } else {
        System.out.println(
            inputString + " char count:" + System.lineSeparator() + charCounterCache.formCharCountToString(inputString));
      }
      if (app.repeat(inputScan)) {
        continue;
      } else {
        repeat = false;
      }
    }

    inputScan.close();

  }

}
