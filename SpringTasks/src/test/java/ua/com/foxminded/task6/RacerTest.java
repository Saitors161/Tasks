package ua.com.foxminded.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RacerTest {

  @Test
  void testFullDateWithLowCase() {

    Racer racer = new Racer("daniel", "ricciardo", "rED BULL RACING TAG HEUER");
    Object test = "DRR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testFullDateWithUpperCase() {

    Racer racer = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER");
    Object test = "DRR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testFullDateWithoutLastName() {

    Racer racer = new Racer("Daniel", "", "RED BULL RACING TAG HEUER");
    Object test = "DR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testFullDateWithoutName() {

    Racer racer = new Racer("", "Ricciardo", "RED BULL RACING TAG HEUER");
    Object test = "RR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testFullDateWithoutTeam() {

    Racer racer = new Racer("Daniel", "Ricciardo", "");
    Object test = "DR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testNullName() {

    Racer racer = new Racer(null, "Ricciardo", "RED BULL RACING TAG HEUER");
    Object test = "RR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testNullLastName() {

    Racer racer = new Racer("Daniel", null, "RED BULL RACING TAG HEUER");
    Object test = "DR";
    assertEquals(test, racer.getAbbreviation());

  }

  @Test
  void testNullTeam() {

    Racer racer = new Racer("Daniel", "Ricciardo", null);
    Object test = "DR";
    assertEquals(test, racer.getAbbreviation());

  }

}
