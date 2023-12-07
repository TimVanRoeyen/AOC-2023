package com.tim.adventofcode.day2;

public class Runner {
    public static void main(String[] args) {
        CubeCalculator calc = new CubeCalculator();
        int result = calc.calculateTotalId();
        System.out.println(result);
    }
}
