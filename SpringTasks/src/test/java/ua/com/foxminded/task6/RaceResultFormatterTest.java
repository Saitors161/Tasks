package ua.com.foxminded.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.task6.formatter.RaceResultFormatter;
import ua.com.foxminded.task6.services.RaceResultService;

@ExtendWith(MockitoExtension.class)

class RaceResultFormatterTest {
  @Mock
  RaceResultService resultRace;
  
  @InjectMocks
  RaceResultFormatter formatterF1 = new RaceResultFormatter();
  
  
  @Test 
  void testRaceResultFormatterWithData() {
    
    Racer racerTest1 = new Racer("Daniel", "Ricciardo", "RED BULL RACING TAG HEUER", "DRR");
    List<Racer> testRacers = new ArrayList<Racer>();
    testRacers.add(racerTest1); 
    List<ResultRacer> testData = formDataTest(testRacers);
    
    List<ResultRacer> arrayResultRacers = new ArrayList<>();
    ResultRacer resultRacer = new ResultRacer(testData.get(0).getRacer(),
                                              testData.get(0).getStart(),
                                              testData.get(0).getFinish(),
                                              testData.get(0).getDuration());
    arrayResultRacers.add(resultRacer);
    arrayResultRacers.add(resultRacer);
    arrayResultRacers.add(resultRacer);
    
    Mockito.when(resultRace.formResultRace()).thenReturn(arrayResultRacers);
   
    int countRacerAfterQ1 = 2;

    String testCheck = formatterF1.getResultToString(countRacerAfterQ1);
    System.out.println(testCheck);
    String testResult = "1. Daniel Ricciardo |RED BULL RACING TAG HEUER  |1:12.013\r\n"
        + "2. Daniel Ricciardo |RED BULL RACING TAG HEUER  |1:12.013\r\n"
        + "---------------------------------------------------------\r\n"
        + "3. Daniel Ricciardo |RED BULL RACING TAG HEUER  |1:12.013";
    
    assertEquals(testResult, testCheck);
  }
  
  @Test 
  void testRaceResultFormatterWithoutData() {
    
    List<ResultRacer> arrayResultRacers = new ArrayList<>();
    
    Mockito.when(resultRace.formResultRace()).thenReturn(arrayResultRacers);
   
    int countRacerAfterQ1 = 2;

    String testCheck = formatterF1.getResultToString(countRacerAfterQ1);
    System.out.println(testCheck);
    String testResult = "";
    
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
}
