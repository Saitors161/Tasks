package ua.com.foxminded.task6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class ResultRacer {

  private Racer racer;
  private LocalDateTime start;
  private LocalDateTime finish;
  private Duration duration;
  
  public ResultRacer(Racer racer, LocalDateTime start, LocalDateTime finish, Duration duration) {
    this.racer = racer;
    this.start = start;
    this.finish = finish;
    this.duration = duration;
  }
  
  public Racer getRacer() {
    return racer;
  }
  
  public void setRacer(Racer racer) {
    this.racer = racer;
  }
  
  public LocalDateTime getStart() {
    return start;
  }
  
  public void setStart(LocalDateTime start) {
    this.start = start;
  }
  
  public LocalDateTime getFinish() {
    return finish;
  }
  
  public void setFinish(LocalDateTime finish) {
    this.finish = finish;
  }
  
  public Duration getDuration() {
    return duration;
  }
  
  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, finish, racer, start);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResultRacer other = (ResultRacer) obj;
    return Objects.equals(duration, other.duration) && Objects.equals(finish, other.finish)
        && Objects.equals(racer, other.racer) && Objects.equals(start, other.start);
  }
  
}
