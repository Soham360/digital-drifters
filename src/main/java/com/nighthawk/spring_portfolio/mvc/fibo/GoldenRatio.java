package com.nighthawk.spring_portfolio.mvc.fibo;

import java.util.ArrayList;

public class GoldenRatio extends Fibo {
    public static ArrayList<Integer> findNth(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer");
        }

        ArrayList<Integer> fibonacciList = new ArrayList<>();
        fibonacciList.add(0);
        fibonacciList.add(1);

        for (int i = 2; i <= num; i++) {
            int fib = fibonacciList.get(i - 1) + fibonacciList.get(i - 2);
            fibonacciList.add(fib);
        }
        
        return fibonacciList;
    }
    
}