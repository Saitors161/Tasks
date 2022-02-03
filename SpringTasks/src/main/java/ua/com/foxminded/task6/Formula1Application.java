package ua.com.foxminded.task6;

import ua.com.foxminded.task6.formatter.RaceResultFormatter;

public class Formula1Application {

  public static void main(String[] args) {

    int countRacerAfterQ1 = 15;
    RaceResultFormatter formatterF1 = new RaceResultFormatter();
    String result = formatterF1.getResultToString(countRacerAfterQ1);
    System.out.println(result);

  }

}
