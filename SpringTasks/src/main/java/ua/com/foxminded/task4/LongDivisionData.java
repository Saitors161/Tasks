package ua.com.foxminded.task4;

import java.util.Arrays;
import java.util.List;

public class LongDivisionData {

  private int dividend = 0;
  private int divisor = 0;
  private List<String> arrayNumbers;
  private int result;
  private int remainder;

  public LongDivisionData(int dividend, int divider) {
    this.setDividend(dividend);
    this.setDivisor(divider);
    this.setArrayNumbers();
    this.setResult();
    this.setRemainder();
  }

  public int getDividend() {
    return dividend;
  }

  private void setDividend(int dividend) {
    this.dividend = dividend;
  }

  public int getDivisor() {
    return divisor;
  }

  private void setDivisor(int divisor) {
    this.divisor = divisor;
  }

  public List<String> getArrayNumbers() {
    return arrayNumbers;
  }

  private void setArrayNumbers() {
    this.arrayNumbers = convertNumberToArray();
  }

  public int getResult() {
    return result;
  }

  private void setResult() {
    this.result = this.dividend / this.divisor;
  }

  public int getRemainder() {
    return remainder;
  }

  public void setRemainder() {
    this.remainder = this.dividend % this.divisor;
  }

  private List<String> convertNumberToArray() {
    String[] arrayOfSymbols = String.valueOf(Math.abs(this.dividend)).split("");
    return Arrays.asList(arrayOfSymbols);
  }

}
