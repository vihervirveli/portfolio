package com.example;
import org.junit.*;
public class SimpleCalculatorTest {
    
    @Test
    public void test(){
        SimpleCalculator cal = new SimpleCalculator();
        int result = cal.add(5,6);
        Assert.assertEquals(11, result);
    } 
}
