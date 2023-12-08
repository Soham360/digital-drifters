package com.nighthawk.spring_portfolio.mvc.fibo;

public class Fibo {
    private long finalTime;

    public long getFinalTime() {
        return finalTime;
    }

    public long calculateTime(Integer nth) {
        long startTime = System.nanoTime();
        long result = getNthTerm(nth);
        long endTime = System.nanoTime();
        finalTime = endTime - startTime;
        return result;
    }

    public Integer getNthTerm(Integer nth){
        return nth;
    }

    
}