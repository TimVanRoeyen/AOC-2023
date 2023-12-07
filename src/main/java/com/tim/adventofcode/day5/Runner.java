package com.tim.adventofcode.day5;

import com.tim.adventofcode.day4.CardCalculator;
import org.apache.commons.lang3.time.StopWatch;

public class Runner {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        LocationCalculator calc = new LocationCalculator();
        long result = calc.calculateLowestLocation();
        System.out.println(result);
        sw.stop();
        System.out.println("Time taken: " + sw.getTime());
    }
}
