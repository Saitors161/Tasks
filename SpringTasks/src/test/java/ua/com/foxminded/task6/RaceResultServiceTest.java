package ua.com.foxminded.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import ua.com.foxminded.task6.dao.RacerDAO;
import ua.com.foxminded.task6.services.RaceResultService;

class RaceResultServiceTest {

  @Test 
  void testResultRace() {
    RacerDAO racerDAO = Mockito.mock(RacerDAO.class);
    
    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");
    List<Racer> testRacers = new ArrayList<Racer>();
    testRacers.add(racerTest1); 
    List<ResultRacer> testResult = formDataTest(testRacers);
   
    Mockito.when(racerDAO.findStartTimeForRacer(testRacers.get(0))).thenReturn(testResult.get(0).getStart());
    Mockito.when(racerDAO.findFinishTimeForRacer(testRacers.get(0))).thenReturn(testResult.get(0).getFinish());    
    BDDMockito.when(racerDAO.findAllRacers()).thenReturn(testRacers);
    
    RaceResultService resultRace = new RaceResultService(racerDAO);
    List<ResultRacer> testCheck = resultRace.formResultRace();

    assertEquals(testResult, testCheck);
  }

  private List<ResultRacer> formDataTest(List<Racer> testRacers) {
    List<ResultRacer> testResult = new ArrayList<>();

    LocalDateTime racer2Start = LocalDateTime.of(2018, 5, 24, 0, 14, 12, 54000000);
    LocalDateTime racer2Finish = LocalDateTime.of(2018, 5, 24, 0, 15, 24, 67000000);
    Duration racer2Duration = Duration.between(racer2Start, racer2Finish);
    ResultRacer resultRacer2 = new ResultRacer(testRacers.get(0), racer2Start, racer2Finish, racer2Duration);


    testResult.add(resultRacer2);
    return testResult;
  }
  
  
  @Test 
  void testResultRaceNotFullData() {
    RacerDAO racerDAO = Mockito.mock(RacerDAO.class);
    
    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");
    Racer racerTest2 = new Racer("Sebastian", "Vettel", "FERRARI", "SVF");
    List<Racer> testRacers = new ArrayList<Racer>();
    testRacers.add(racerTest1); 
    testRacers.add(racerTest2); 
    List<ResultRacer> testResult = formDataTest(testRacers);
   
    Mockito.when(racerDAO.findStartTimeForRacer(testRacers.get(0))).thenReturn(testResult.get(0).getStart());
    Mockito.when(racerDAO.findFinishTimeForRacer(testRacers.get(0))).thenReturn(testResult.get(0).getFinish());    
    BDDMockito.when(racerDAO.findAllRacers()).thenReturn(testRacers);
    
    RaceResultService resultRace = new RaceResultService(racerDAO);
    List<ResultRacer> testCheck = resultRace.formResultRace();

    assertEquals(testResult, testCheck);
  }
  
  @Test 
  void testResultRaceNoRacers() {
    RacerDAO racerDAO = Mockito.mock(RacerDAO.class);
    
    List<ResultRacer> testResult = new ArrayList<ResultRacer>();
   
    BDDMockito.when(racerDAO.findAllRacers()).thenReturn(new ArrayList<Racer>());
    
    RaceResultService resultRace = new RaceResultService(racerDAO);
    List<ResultRacer> testCheck = resultRace.formResultRace();

    assertEquals(testResult, testCheck);
  }

}


