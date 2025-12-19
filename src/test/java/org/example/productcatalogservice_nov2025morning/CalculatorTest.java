package org.example.productcatalogservice_nov2025morning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void TestAdd_With2PositiveIntegers_RunSuccessfully() {
        //Arrange
        Calculator calculator  = new Calculator();

        //Act
        int result = calculator.add(1,5);

        //Assert
        assert(result == 6);
        assertEquals(6,result);
    }

    @Test
    public void TestDivide_ByZero_ResultsInArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class,
                ()->calculator.divide(1,0));
    }

}