package ua.com.foxminded.task6.services;



import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import ua.com.foxminded.task6.Racer;
import ua.com.foxminded.task6.ResultRacer;
import ua.com.foxminded.task6.dao.RacerDAO;

public class RaceResultService {
  
  private RacerDAO racerDAO;
  
  private Comparator<ResultRacer> comparator = (ResultRacer1, ResultRacer2) -> ResultRacer1.getDuration()
      .compareTo(ResultRacer2.getDuration());

  public RaceResultService(RacerDAO racerDAO) {
    this.racerDAO = racerDAO;
  }

  public List<ResultRacer> formResultRace() {

    return racerDAO.findAllRacers().stream()
          .map(this::createResultRacer)
          .map(this::setRacerStartTime)
          .map(this::setRacerFinishTime)
          .filter((ResultRace)->ResultRace.getFinish()!= null && ResultRace.getStart()!=null)
          .map(this::setRacerDuration) 
          .sorted(comparator)
          .collect(Collectors.toList());

  }
  
  private ResultRacer createResultRacer(Racer racer) {
    return new ResultRacer(racer, null, null, null);
    
  }
  
  private ResultRacer setRacerStartTime (ResultRacer resultRacer) {
    resultRacer.setStart(racerDAO.findStartTimeForRacer(resultRacer.getRacer()));
    return resultRacer;
  }
  
  
  private ResultRacer setRacerFinishTime (ResultRacer resultRacer) {
    resultRacer.setFinish(racerDAO.findFinishTimeForRacer(resultRacer.getRacer()));   
    return resultRacer;
  }
  
  private ResultRacer setRacerDuration (ResultRacer resultRacer) {
    resultRacer.setDuration(Duration.between(resultRacer.getStart(), resultRacer.getFinish()));;
    return resultRacer;   
  }
  
}
