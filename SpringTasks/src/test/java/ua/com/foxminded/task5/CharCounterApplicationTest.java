package ua.com.foxminded.task5;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class CharCounterApplicationTest {

  private CharCounterApplication application = new CharCounterApplication();
  private Scanner scanner = Mockito.mock(Scanner.class);

  @Test
  void testRepeatYes() {

    String test = "yes";
    Mockito.when(this.scanner.next()).thenReturn(test);
    assertEquals(true, application.repeat(this.scanner));

  }

  @Test
  void testRepeatNo() {

    String test = "no";
    Mockito.when(this.scanner.next()).thenReturn(test);
    assertEquals(false, application.repeat(this.scanner));

  }

  @Test
  void testRepeatOtherWords() {

    String test = "hi";
    Mockito.when(this.scanner.next()).thenReturn(test);
    assertThrows(StackOverflowError.class, () -> {
      assertEquals(false, application.repeat(this.scanner));
    });

    
    
  }
}
