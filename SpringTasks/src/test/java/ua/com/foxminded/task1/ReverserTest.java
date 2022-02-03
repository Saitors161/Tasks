package ua.com.foxminded.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



class ReverserTest {

  @Test
  void testHelloWorld() {
    Reverser rev = new Reverser();
    assertEquals("olleH dlroW", rev.reverse("Hello World"));
  }

  @Test
  void testWorld() {
    Reverser rev = new Reverser();
    assertEquals("dlroW", rev.reverse("World"));
  }

  @Test
  void testEmptyString() {
    Reverser rev = new Reverser();
    assertEquals("", rev.reverse(""));
  }

  @Test
  void testHelloMyWorld() {
    Reverser rev = new Reverser();
    assertEquals("olleH yM dlroW", rev.reverse("Hello My World"));
  }

  @Test
  void testLettersAndSymbols() {
    Reverser rev = new Reverser();
    assertEquals("d1cba hgf!e", rev.reverse("a1bcd efg!h"));
  }

  @Test
  void testNumbers() {
    Reverser rev = new Reverser();
    assertEquals("777722 002", rev.reverse("777722 002"));
  }

  @Test
  void testLettersAndSymbolsNumberInRow() {
    Reverser rev = new Reverser();
    assertEquals("777722 002qwassss222221", rev.reverse("777722 002ssssawq222221"));
  }

  @Test
  void testNull() {
    Reverser rev = new Reverser();
    assertEquals("", rev.reverse(null));
  }

}
