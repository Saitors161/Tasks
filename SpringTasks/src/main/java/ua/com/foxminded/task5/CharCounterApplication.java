package ua.com.foxminded.task5;

import java.util.Scanner;

public class CharCounterApplication {

  String getInputString(Scanner scanner) {
    System.out.println("Input a string: ");
    return scanner.nextLine();
  }

  boolean repeat(Scanner scanner) {
    System.out.println("Repeat?");
    String request = scanner.next().toUpperCase();
    if (request.equals("YES")) {
      return true;
    } else if (request.equals("NO")) {
      return false;
    } else {
      System.out.println("Incorrect request");
      repeat(scanner);
    }
    return false;
  }

}
