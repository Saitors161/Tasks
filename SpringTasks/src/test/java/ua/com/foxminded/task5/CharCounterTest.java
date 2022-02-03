package ua.com.foxminded.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharCounterTest {
  private CharCounter charCounter = new CharCounter();

  @Test
  void testString (){    
    
    String testInput = "three sheeps";
    String testRequest = "t:1\r\n"
        + "h:2\r\n"
        + "r:1\r\n"
        + "e:4\r\n"
        + " :1\r\n"
        + "s:2\r\n"
        + "p:1\r\n";
    
    assertEquals(testRequest, this.charCounter.formCharCountToString(testInput)); 
       
  }
  
  @Test
  void testNull() {

    String testInput = null;
    String testRequest = "";

    assertEquals(testRequest, this.charCounter.formCharCountToString(testInput));

  }
}
