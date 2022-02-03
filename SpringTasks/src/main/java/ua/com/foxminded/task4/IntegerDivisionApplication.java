package ua.com.foxminded.task4;


public class IntegerDivisionApplication {

  public static void main(String[] args) {
    int dividend = 100;
    int divisor = 2;
    try {
      LongDivisionData data = new LongDivisionData(dividend, divisor);
      Divisor div = new Divisor();
      FormatterLongDivision formatterLongDivision = new FormatterLongDivision(div);
      String result = formatterLongDivision.formToString(data);
      System.out.println(result);
    } catch (ArithmeticException a) {
      System.out.println("Incorrect data");
    }

  }

}
