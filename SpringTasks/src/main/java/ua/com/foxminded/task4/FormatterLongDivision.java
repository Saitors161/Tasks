package ua.com.foxminded.task4;

import java.util.List;

public class FormatterLongDivision {
  private Divisor divisor;

  public FormatterLongDivision(Divisor divisor) {
    this.divisor = divisor;
  }

  public String formToString(LongDivisionData data) {
    StringBuilder totalString = new StringBuilder();
    List<DivisionStep> arraySteps = this.divisor.divide(data);
    totalString.append(formatHeadLongDivision(arraySteps, data));
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

  private String formatHeadLongDivision(List<DivisionStep> arraySteps, LongDivisionData longDivData) {
    DivisionStep firstString = arraySteps.get(0);
    String separator = "";
    StringBuilder HeadString = new StringBuilder();
    if (("" + longDivData.getDivisor()).length() > ("" + longDivData.getResult()).length()) {
      separator = multiplicationSymbol(("" + longDivData.getDivisor()).length(), "-");
    } else {
      separator = multiplicationSymbol(("" + longDivData.getResult()).length(), "-");
    }
    HeadString.append("_" + longDivData.getDividend() + "|" + longDivData.getDivisor() + System.lineSeparator() + " "
        + firstString.getStringLine());
    HeadString.append(
        multiplicationSymbol(("" + longDivData.getDividend()).length() - firstString.getStringLine().length(), " "));
    HeadString.append("|" + separator + System.lineSeparator() + " ");
    if (longDivData.getDividend() < longDivData.getDivisor()) {
      HeadString.append(multiplicationSymbol(("" + longDivData.getDividend()).length(), "-"));
    } else {
      HeadString.append(multiplicationSymbol(firstString.getStringLine().length(), "-"));
      HeadString.append(
          multiplicationSymbol(("" + longDivData.getDividend()).length() - firstString.getStringLine().length(), " "));
    }
    HeadString.append(("|" + longDivData.getResult()) + System.lineSeparator());
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
