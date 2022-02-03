package ua.com.foxminded.task4;

import java.util.Objects;

class DivisionStep {

    private int numberLine;
    private int spaces;
    private String stringLine;
    private int number;

    public DivisionStep(int numberLine, int spaces, String stringLine) {
      this.numberLine = numberLine;
      this.spaces = spaces;
      this.stringLine = stringLine;
      this.number = Integer.parseInt(stringLine);
    }

  
    public int getNumberLine() {
      return numberLine;
    }

    public int getSpaces() {
      return spaces;
    }

    public String getStringLine() {
      return stringLine;
    }

    public int getNumber() {
      return number;
    }


    @Override
    public int hashCode() {
      return Objects.hash(number, numberLine, spaces, stringLine);
    }


    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DivisionStep other = (DivisionStep) obj;
      return number == other.number && numberLine == other.numberLine && spaces == other.spaces
          && Objects.equals(stringLine, other.stringLine);
    }

  
}
