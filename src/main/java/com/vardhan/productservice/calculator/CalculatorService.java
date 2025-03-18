package com.vardhan.productservice.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int  addService(int a, int b) {

        System.out.println("Service: Some logic here");
        System.out.println("Service: some logic before addition");
        int sum = a + b;
        System.out.println("Service:  some logic after addition");
        return sum;
    }
}
