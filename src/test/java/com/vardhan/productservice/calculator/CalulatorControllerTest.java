package com.vardhan.productservice.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalulatorControllerTest {

//    CalculatorService calculatorService = Mockito.mock(CalculatorService.class);
//     CalculatorController calculatorController = new CalculatorController(calculatorService);

    @MockitoBean
    CalculatorService calculatorService;
    @Autowired
    CalculatorController calculatorController;
    @Test
    public void testAddWhenTwoIntegersArePassedReturnInteger() {
        // Training the mock
        when(calculatorService.addService(5,10))
                .thenReturn(15);

        when(calculatorService.addService(10,20)).thenReturn(30);

       //  when(calculatorService.addService(anyInt(), anyInt())).thenReturn(Any);
        //Arrange
        int firstInteger = 10;
        int secondInteger = 20;
        int expectedResult = firstInteger + secondInteger;

        //Act
        int actualResult = calculatorController.add(firstInteger, secondInteger);

        //Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
