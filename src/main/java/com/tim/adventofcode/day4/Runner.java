package com.tim.adventofcode.day4;

import org.apache.commons.lang3.time.StopWatch;

public class Runner {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        CardCalculator calc = new CardCalculator();
        int result = calc.calculateTotal();
        System.out.println(result);
        sw.stop();
        System.out.println("Time taken: " + sw.getTime());
    }
}
