package com.example.securitylearning.LearningSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CalculatorServiceTest {

    @Autowired
     CalculatorService calculator;

    @Test
    void checkTheSumIsCorrect(){

      assertEquals(30 ,calculator.add(20, 10));
    }
    @Test
    void checkTheDifferenceIsCorrect(){
        assertEquals(60 ,calculator.subtract(10,20));
    }
    @Test
    void checkTheProductIsCorrect(){
        assertEquals(40 ,calculator.multiply(10,20));
    }
    @Test
    void checkTheQuotientIsCorrect(){
        assertEquals( 2,calculator.divide(10,5));
    }
    @Test
    void checkDividingByZeroThrowsException(){
        assertThrows(ArithmeticException.class, () -> calculator.divide(10,0));
    }
}
