package com.tim.adventofcode.day1;

public class Executor {
    public static void main(String[] args) {
        CalibrationCalculator calc = new CalibrationCalculator();
        System.out.println(calc.calculateCalibration());
        System.out.println(calc.calculateCalibrationLambda());
    }
}
