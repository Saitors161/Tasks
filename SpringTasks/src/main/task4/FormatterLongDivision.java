package ua.com.foxminded.task4;

import java.util.List;

public class FormatterLongDivision {

  public String formToString(int dividend, int divisor) {

    LongDivisionData data = new LongDivisionData(dividend, divisor);
    return formatBodyLongDivision(data);

  }

  public String formatBodyLongDivision(LongDivisionData data) {
    StringBuilder totalString = new StringBuilder();
    List<DivisionStep> arraySteps = data.formListDataSteps();
    totalString.append(formatHeadLongDivision(data, arraySteps));
    int index = 1;
    for (; index < arraySteps.size(); index++) {
      arraySteps.get(index);
      if (index % 2 == 0) {
        totalString.append(multiplicationSymbol(arraySteps.get(index).getSpaces(), " "));
        totalString.append(arraySteps.get(index).getStringLine() + System.lineSeparator());
        totalString.append(multiplicationSymbol(arraySteps.get(index).getSpaces(), " "));
        totalString.append(multiplicationSymbol(arraySteps.get(index).getStringLine().length(), "-"));
        totalString.append(System.lineSeparator());
      } else {
        totalString.append(multiplicationSymbol(arraySteps.get(index).getSpaces() - 1, " "));
        if (index == arraySteps.size() - 1) {
          totalString.append(" " + arraySteps.get(index).getStringLine());
        } else {
          totalString.append("_" + arraySteps.get(index).getStringLine() + System.lineSeparator());
        }

      }

    }

    return totalString.toString();

  }

  private String formatHeadLongDivision(LongDivisionData data, List<DivisionStep> ArraySteps) {
    DivisionStep firstString = ArraySteps.get(0);
    String separator = "";
    StringBuilder HeadString = new StringBuilder();
    if (("" + data.getDivisor()).length() > ("" + data.getResult()).length()) {
      separator = multiplicationSymbol(("" + data.getDivisor()).length(), "-");
    } else {
      separator = multiplicationSymbol(("" + data.getResult()).length(), "-");
    }
    HeadString.append("_" + data.getDividend() + "|" + data.getDivisor() + System.lineSeparator() + " "
        + firstString.getStringLine());
    HeadString
        .append(multiplicationSymbol(("" + data.getDividend()).length() - firstString.getStringLine().length(), " "));
    HeadString.append("|" + separator + System.lineSeparator() + " ");
    if (data.getDividend() < data.getDivisor()) {
      HeadString.append(multiplicationSymbol(("" + data.getDividend()).length(), "-"));
    } else {
      HeadString.append(multiplicationSymbol(firstString.getStringLine().length(), "-"));
      HeadString
          .append(multiplicationSymbol(("" + data.getDividend()).length() - firstString.getStringLine().length(), " "));
    }
    HeadString.append(("|" + data.getResult()) + System.lineSeparator());
    return HeadString.toString();
  }

  public String multiplicationSymbol(int quantity, String string) {

    StringBuilder sb = new StringBuilder();
    while (sb.length() < quantity) {
      sb.append(string);
    }

    return sb.toString();
  }
}
