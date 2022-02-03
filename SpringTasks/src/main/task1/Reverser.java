package ua.com.foxminded.task1;

public class Reverser<string> {

  public String reverse(String input) {

    if (input == "" || input == null) {
      return "";
    }

    String inputWords[] = input.split(" ");
    String outputWords[] = new String[inputWords.length];
    int i = 0;

    for (String inputWord : inputWords) {

      String[] inputArraySymbol = inputWord.split("");
      String[] outputArraySymbol = new String[inputArraySymbol.length];
      outputArraySymbol = (String[]) reverseWord(inputArraySymbol, outputArraySymbol);
      outputWords[i] = String.join("", outputArraySymbol);
      i++;
    }

    return String.join(" ", outputWords);
  }

  private int checkEmptyOrNo(String array[], int index) {
    if (array[index] != null) {
      index--;
      index = checkEmptyOrNo(array, index);
    }
    return index;
  }

  private string[] reverseWord(String inputArraySymbol[], String[] outputArraySymbol) {
    int j = 0;

    for (String letter : inputArraySymbol) {
      char c = letter.charAt(0);
      if (Character.isLetter(c)) { 
      } else {
        outputArraySymbol[j] = letter;
      }
      j++;
    }

    int z = inputArraySymbol.length - 1;
    for (String letter : inputArraySymbol) {
      char c = letter.charAt(0);
      if (Character.isLetter(c)) { 
        z = checkEmptyOrNo(outputArraySymbol, z);
        outputArraySymbol[z] = letter;
        z--;
      }
    }
    return (string[]) outputArraySymbol;
  }

 
}
