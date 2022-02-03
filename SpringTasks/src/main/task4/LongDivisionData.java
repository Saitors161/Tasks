package ua.com.foxminded.task4;

import java.util.ArrayList;
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

  public List<DivisionStep> formListDataSteps() {
    List<DivisionStep> listDataStrings = new ArrayList<>();
    int index = 1;
    Integer spaces = 1;
    String number = "";
    boolean first = true;
    String lastNumber = "";

    if (SpecialCases(dividend, divisor)) {
      return formSpecialCases(dividend, divisor, listDataStrings, index, spaces);
    }
    for (String symbol : this.arrayNumbers) {
      number = number + symbol;
      int whole = Integer.parseInt(number) / this.divisor;
      int reminder = Integer.parseInt(number) % this.divisor;
      if (whole >= 1) {
        if (first) {
          first = false;
          number = "" + reminder;
          listDataStrings.add(new DivisionStep(index, spaces, "" + whole * this.divisor));
          lastNumber = "" + whole * this.divisor;
          index++;
          if (this.dividend == this.divisor) {
            spaces = spaces + ("" + this.dividend).length();
          }
          continue;
        }
        if (index > 0) {
          listDataStrings.add(new DivisionStep(index, spaces, number));
          index++;
        }
        if (number.length() - ("" + whole * this.divisor).length() > 0) {
          spaces = spaces + number.length() - ("" + whole * this.divisor).length();
        }
        listDataStrings.add(new DivisionStep(index, spaces, "" + whole * this.divisor));
        index++;
        lastNumber = "" + whole * this.divisor;
        if (reminder == 0) {
          spaces = spaces + number.length();
        } else if (number.length() - ("" + whole * this.divisor).length() == 0) {
          spaces = spaces + (number.length() - ("" + reminder).length());
        }
      }
      number = "" + reminder;
    }

    if (this.dividend % 10 == 0) {
      listDataStrings.add(new DivisionStep(index, spaces + (lastNumber.length() - number.length()), "" + number));
    } else {
      listDataStrings.add(new DivisionStep(index, spaces, "" + number));
    }

    return listDataStrings;
  }

  private boolean SpecialCases(int dividend, int divisor) {
    if (this.dividend == 0) {
      return true;
    } else if (this.dividend < this.divisor) {
      return true;
    } else if (this.dividend == this.divisor) {
      return true;
    } else {
      return false;
    }

  }

  private List<DivisionStep> formSpecialCases(int dividend, int divisor, List<DivisionStep> listDataStrings, int index,
      int spaces) {
    if (this.dividend == 0) {
      listDataStrings.add(new DivisionStep(index, spaces, "0"));
      index++;
      listDataStrings.add(new DivisionStep(index, spaces, "0"));

      return listDataStrings;

    } else if (this.dividend < this.divisor) {
      listDataStrings.add(new DivisionStep(index, spaces, "0"));
      index++;
      listDataStrings.add(new DivisionStep(index, spaces, "" + this.dividend));

      return listDataStrings;

    } else if (this.dividend == this.divisor) {
      listDataStrings.add(new DivisionStep(index, spaces, "" + this.dividend));
      index++;
      listDataStrings.add(new DivisionStep(index, ("" + this.dividend).length(), "0"));

      return listDataStrings;
    }
    return listDataStrings;

  }
}
