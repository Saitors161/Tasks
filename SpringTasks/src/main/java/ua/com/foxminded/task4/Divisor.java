package ua.com.foxminded.task4;

import java.util.ArrayList;
import java.util.List;

public class Divisor {
  
  List<DivisionStep> divide (LongDivisionData data) {
    
    List<DivisionStep> listDataStrings = new ArrayList<>();
    int index = 1;
    Integer spaces = 1;
    String number = "";
    boolean first = true;
    String lastNumber = "";

    if (SpecialCases(data.getDividend(), data.getDivisor())) {
      return formSpecialCases(data.getDividend(), data.getDivisor(), listDataStrings, index, spaces);
    }
    for (String symbol : data.getArrayNumbers()) {
      number = number + symbol;
      int whole = Integer.parseInt(number) / data.getDivisor();
      int reminder = Integer.parseInt(number) % data.getDivisor();
      if (whole >= 1) {
        if (first) {
          first = false;
          number = "" + reminder;
          listDataStrings.add(new DivisionStep(index, spaces, "" + whole * data.getDivisor()));
          lastNumber = "" + whole * data.getDivisor();
          index++;
          if (data.getDividend() == data.getDivisor()) {
            spaces = spaces + ("" + data.getDividend()).length();
          }
          continue;
        }
        if (index > 0) {
          listDataStrings.add(new DivisionStep(index, spaces, number));
          index++;
        }
        if (number.length() - ("" + whole * data.getDivisor()).length() > 0) {
          spaces = spaces + number.length() - ("" + whole * data.getDivisor()).length();
        }
        listDataStrings.add(new DivisionStep(index, spaces, "" + whole * data.getDivisor()));
        index++;
        lastNumber = "" + whole * data.getDivisor();
        if (reminder == 0) {
          spaces = spaces + number.length();
        } else if (number.length() - ("" + whole * data.getDivisor()).length() == 0) {
          spaces = spaces + (number.length() - ("" + reminder).length());
        }
      }
      number = "" + reminder;
    }

    if (data.getDividend() % 10 == 0) {
      listDataStrings.add(new DivisionStep(index, spaces + (lastNumber.length() - number.length()), "" + number));
    } else {
      listDataStrings.add(new DivisionStep(index, spaces, "" + number));
    }

    return listDataStrings;
    
  }
  private boolean SpecialCases(int dividend, int divisor) {
    if (dividend == 0) {
      return true;
    } else if (dividend < divisor) {
      return true;
    } else if (dividend == divisor) {
      return true;
    } else {
      return false;
    }

  }

  private List<DivisionStep> formSpecialCases(int dividend, int divisor, List<DivisionStep> listDataStrings, int index,
      int spaces) {
    if (dividend == 0) {
      listDataStrings.add(new DivisionStep(index, spaces, "0"));
      index++;
      listDataStrings.add(new DivisionStep(index, spaces, "0"));

      return listDataStrings;

    } else if (dividend < divisor) {
      listDataStrings.add(new DivisionStep(index, spaces, "0"));
      index++;
      listDataStrings.add(new DivisionStep(index, spaces, "" + dividend));

      return listDataStrings;

    } else if (dividend == divisor) {
      listDataStrings.add(new DivisionStep(index, spaces, "" + dividend));
      index++;
      listDataStrings.add(new DivisionStep(index, ("" + dividend).length(), "0"));

      return listDataStrings;
    }
    return listDataStrings;

  }

}
