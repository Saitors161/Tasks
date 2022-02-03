package ua.com.foxminded.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.task6.dao.RacerDAO;

class RacerDAOTest {
  private RacerDAO racerDAO = new RacerDAO();

  @Test
  void testFindRacers() {

    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");
    Racer racerTest2 = new Racer("Sebastian", "Vettel", "FERRARI", "SVF");
    List<Racer> testResult = new ArrayList<Racer>();
    testResult.add(racerTest1);
    testResult.add(racerTest2);

    List<Racer> testCheck = this.racerDAO.findAllRacers();

    assertEquals(testResult, testCheck);
  }

  @Test
  void testTimeStartRacer() {

    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");

    LocalDateTime testCheck = null;

    testCheck = this.racerDAO.findStartTimeForRacer(racerTest1);

    LocalDateTime testResult = LocalDateTime.of(2018, 5, 24, 00, 14, 12, 54000000);
    assertEquals(testResult, testCheck);
  }

  @Test
  void testTimeFinnishRacer() {

    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");

    LocalDateTime testCheck = null;

    testCheck = this.racerDAO.findFinishTimeForRacer(racerTest1);

    LocalDateTime testResult = LocalDateTime.of(2018, 5, 24, 00, 15, 24, 67000000);
    assertEquals(testResult, testCheck);
  }

}
