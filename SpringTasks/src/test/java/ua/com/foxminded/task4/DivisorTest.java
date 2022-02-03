package ua.com.foxminded.task4;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DivisorTest {
   
  @Test
  void testSingleDigitDivision (){    
    List<DivisionStep> test = new ArrayList<DivisionStep>();
    test.add(new DivisionStep(1, 1,"4"));
    test.add(new DivisionStep(2, 1,"38"));
    test.add(new DivisionStep(3, 1,"36"));
    test.add(new DivisionStep(4, 2,"29"));
    test.add(new DivisionStep(5, 2,"28"));
    test.add(new DivisionStep(6, 3,"15"));
    test.add(new DivisionStep(7, 3,"12"));
    test.add(new DivisionStep(8, 4,"34"));
    test.add(new DivisionStep(9, 4,"32"));
    test.add(new DivisionStep(10, 5,"2"));
    LongDivisionData dataTest = new LongDivisionData(78954, 4);
    assertEquals(test, new Divisor().divide(dataTest)); 
  }
  
  @Test
  void testDivisionByTwoDigits (){    
    List<DivisionStep> test = new ArrayList<DivisionStep>();
    test.add(new DivisionStep(1, 1,"41"));
    test.add(new DivisionStep(2, 1,"379"));
    test.add(new DivisionStep(3, 1,"369"));
    test.add(new DivisionStep(4, 2,"105"));
    test.add(new DivisionStep(5, 3,"82"));
    test.add(new DivisionStep(6, 3,"234"));
    test.add(new DivisionStep(7, 3,"205"));
    test.add(new DivisionStep(8, 4,"29"));
    LongDivisionData dataTest = new LongDivisionData(78954, 41);
    assertEquals(test, new Divisor().divide(dataTest)); 
  }

    
 @Test
 void testDivisionBySameNumber(){
   List<DivisionStep> test = new ArrayList<DivisionStep>();
   test.add(new DivisionStep(1, 1,"78954"));
   test.add(new DivisionStep(2, 5,"0"));
   LongDivisionData dataTest = new LongDivisionData(78954, 78954);

   assertEquals(test, new Divisor().divide(dataTest)); 
 }

 @Test
 void testDivisionByZero() {

   assertThrows(ArithmeticException.class, () -> {
     List<DivisionStep> test = new ArrayList<DivisionStep>();
     LongDivisionData dataTest = new LongDivisionData(78954, 0);
     assertEquals(test, new Divisor().divide(dataTest));
   });

 }

  @Test
  void testDivisionZero(){
    List<DivisionStep> test = new ArrayList<DivisionStep>();
    test.add(new DivisionStep(1, 1,"0"));
    test.add(new DivisionStep(2, 1,"0"));
    LongDivisionData dataTest = new LongDivisionData(0, 78954);

    assertEquals(test, new Divisor().divide(dataTest)); 
  }
  
  
  @Test
  void testDivisorGreaterThanDividend(){
    List<DivisionStep> test = new ArrayList<DivisionStep>();
    test.add(new DivisionStep(1, 1,"0"));
    test.add(new DivisionStep(2, 1,"100"));
    LongDivisionData dataTest = new LongDivisionData(100, 78954);

    assertEquals(test, new Divisor().divide(dataTest));  
  }
  
  
  @Test
  void testDivisionWithZero(){
    List<DivisionStep> test = new ArrayList<DivisionStep>();
    test.add(new DivisionStep(1, 1,"10"));
    test.add(new DivisionStep(2, 2,"0"));
    LongDivisionData dataTest = new LongDivisionData(100, 2);

    assertEquals(test, new Divisor().divide(dataTest)); 
   
  }
}
