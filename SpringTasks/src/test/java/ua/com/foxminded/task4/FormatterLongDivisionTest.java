package ua.com.foxminded.task4;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FormatterLongDivisionTest {
  
  private Divisor divisor = Mockito.mock(Divisor.class); 
  private FormatterLongDivision formatterLongDivision = new FormatterLongDivision(divisor);
  
  
  
  @Test
  void testSingleDigitDivision (){
  LongDivisionData data = new LongDivisionData(78954, 4);  
  List<DivisionStep> listDataStrings = new ArrayList<>();
  listDataStrings.add(new DivisionStep(1, 1, "4"));
  listDataStrings.add(new DivisionStep(2, 1, "38"));
  listDataStrings.add(new DivisionStep(3, 1, "36"));
  listDataStrings.add(new DivisionStep(4, 2, "29"));
  listDataStrings.add(new DivisionStep(5, 2, "28"));
  listDataStrings.add(new DivisionStep(6, 3, "15"));
  listDataStrings.add(new DivisionStep(7, 3, "12"));
  listDataStrings.add(new DivisionStep(8, 4, "34"));
  listDataStrings.add(new DivisionStep(9, 4, "32"));
  listDataStrings.add(new DivisionStep(10, 5, "2"));
  
  Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
  
  String test = 
      "_78954|4\r\n"
      + " 4    |-----\r\n"
      + " -    |19738\r\n"
      + "_38\r\n"
      + " 36\r\n"
      + " --\r\n"
      + " _29\r\n"
      + "  28\r\n"
      + "  --\r\n"
      + "  _15\r\n"
      + "   12\r\n"
      + "   --\r\n"
      + "   _34\r\n"
      + "    32\r\n"
      + "    --\r\n"
      + "     2";
    
  assertEquals(test, this.formatterLongDivision.formToString(data)); 
  }
  
  @Test
  void testDivisionByTwoDigits (){
    LongDivisionData data = new LongDivisionData(78954, 41);  
    List<DivisionStep> listDataStrings = new ArrayList<>();
    listDataStrings.add(new DivisionStep(1, 1, "41"));
    listDataStrings.add(new DivisionStep(2, 1, "379"));
    listDataStrings.add(new DivisionStep(3, 1, "369"));
    listDataStrings.add(new DivisionStep(4, 2, "105"));
    listDataStrings.add(new DivisionStep(5, 3, "82"));
    listDataStrings.add(new DivisionStep(6, 3, "234"));
    listDataStrings.add(new DivisionStep(7, 3, "205"));
    listDataStrings.add(new DivisionStep(8, 4, "29"));

    Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
    String test = 
        "_78954|41\r\n"
      + " 41   |----\r\n"
      + " --   |1925\r\n"
      + "_379\r\n"
      + " 369\r\n"
      + " ---\r\n"
      + " _105\r\n"
      + "   82\r\n"
      + "   --\r\n"
      + "  _234\r\n"
      + "   205\r\n"
      + "   ---\r\n"
      + "    29";
   
    assertEquals(test, this.formatterLongDivision.formToString(data)); 
  }
    
  @Test
  void testDivisionBySameNumber(){
    LongDivisionData data = new LongDivisionData(78954, 78954);  
    List<DivisionStep> listDataStrings = new ArrayList<>();
    listDataStrings.add(new DivisionStep(1, 1, "78954"));
    listDataStrings.add(new DivisionStep(2, 5, "0"));
    Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
    String test = 
        "_78954|78954\r\n"
      + " 78954|-----\r\n"
      + " -----|1\r\n"
      + "     0";
       
    assertEquals(test, this.formatterLongDivision.formToString(data));
  }
    
  @Test
  void testDivisionByZero() {

    assertThrows(ArithmeticException.class, () -> {
      LongDivisionData data = new LongDivisionData(78954, 0);
      Mockito.when(this.divisor.divide(data)).thenReturn(null);
      this.formatterLongDivision.formToString(data);
    });
    
  }
    

  @Test
  void testDivisionZero(){
    LongDivisionData data = new LongDivisionData(0, 78954);  
    List<DivisionStep> listDataStrings = new ArrayList<>();
    listDataStrings.add(new DivisionStep(1, 1, "0"));
    listDataStrings.add(new DivisionStep(2, 1, "0"));
    Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
    String test = "_0|78954\r\n"
        + " 0|-----\r\n"
        + " -|0\r\n"
        + " 0";
    assertEquals(test,this.formatterLongDivision.formToString(data));    
  }
  
  
  @Test
  void testDivisorGreaterThanDividend(){
    LongDivisionData data = new LongDivisionData(100, 78954);  
    List<DivisionStep> listDataStrings = new ArrayList<>();
    listDataStrings.add(new DivisionStep(1, 1, "0"));
    listDataStrings.add(new DivisionStep(2, 1, "100"));
    Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
    String test = "_100|78954\r\n"
        + " 0  |-----\r\n"
        + " ---|0\r\n"
        + " 100";
    assertEquals(test, this.formatterLongDivision.formToString(data));    
  }
  
  
  @Test
  void testDivisionWithZero(){
    LongDivisionData data = new LongDivisionData(100, 2);  
    List<DivisionStep> listDataStrings = new ArrayList<>();
    listDataStrings.add(new DivisionStep(1, 1, "10"));
    listDataStrings.add(new DivisionStep(2, 2, "0"));
    Mockito.when(this.divisor.divide(data)).thenReturn(listDataStrings);
    String test = 
        "_100|2\r\n"
      + " 10 |--\r\n"
      + " -- |50\r\n"
      + "  0";
    assertEquals(test, this.formatterLongDivision.formToString(data));    
  }
}
