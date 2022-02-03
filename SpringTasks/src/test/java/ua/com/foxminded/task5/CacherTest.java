package ua.com.foxminded.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CacherTest {
  
  @Test
  void testNewValue (){ 
    
    CharCounter charCounter = Mockito.mock(CharCounter.class);
    Cacher charCounterCache = new Cacher(charCounter);
    String testInput = "three sheeps";
    String test = "t:1\r\n"
        + "h:2\r\n"
        + "r:1\r\n"
        + "e:4\r\n"
        + " :1\r\n"
        + "s:2\r\n"
        + "p:1";
    
    Mockito.when(charCounter.formCharCountToString(testInput)).thenReturn(test);
    assertEquals(test, charCounterCache.formCharCountToString(testInput)); 
    Mockito.verify(charCounter).formCharCountToString(Mockito.any());   
  }
  
  @Test
  void testRepeatValue (){    
    CharCounter charCounter = Mockito.mock(CharCounter.class);
    Cacher charCounterCache = new Cacher(charCounter);
    String testInput = "three sheeps";
    String testCache = "t:1\r\n"
        + "h:2\r\n"
        + "r:1\r\n"
        + "e:4\r\n"
        + " :1\r\n"
        + "s:2\r\n"
        + "p:1";

    Mockito.when(charCounter.formCharCountToString(testInput)).thenReturn(testCache);
    charCounterCache.formCharCountToString(testInput);
    
    String test = testCache;
    
    assertEquals(test, charCounterCache.formCharCountToString(testInput)); 
    Mockito.verify(charCounter).formCharCountToString(Mockito.any());
       
  }
  
  @Test
  void testNull (){    
    CharCounter charCounter = Mockito.mock(CharCounter.class);
    Cacher charCounterCache = new Cacher(charCounter);
    String testInput = null;
    String testCache = "";
    Mockito.when(charCounter.formCharCountToString(testInput)).thenReturn(testCache);
    
    String test = "";
    
    assertEquals(test, charCounterCache.formCharCountToString(testInput)); 
    Mockito.verify(charCounter).formCharCountToString(Mockito.any());
       
  }
  
}
