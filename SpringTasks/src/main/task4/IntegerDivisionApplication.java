package ua.com.foxminded.task4;

public class IntegerDivisionApplication {

  public static void main(String[] args) {
    FormatterLongDivision formatterLongDivision = new FormatterLongDivision();
    try {
      String result = formatterLongDivision.formToString(78954, 41);
      System.out.println(result);
    } catch (ArithmeticException a) {
      System.out.println("Incorrect data");
    }

  }

}
