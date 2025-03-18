package com.vardhan.productservice.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b) {

        System.out.println("Controller : Some logic here");
        System.out.println("Controller : Some logic before addition");
        int sum = calculatorService.addService(a,b);
        System.out.println("Controller : Some logic after addition");
        return sum;
    }
}
