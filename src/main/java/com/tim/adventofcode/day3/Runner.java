package com.tim.adventofcode.day3;

import com.tim.adventofcode.day2.CubeCalculator;

public class Runner {
    public static void main(String[] args) {
        GearCalculator calc = new GearCalculator();
        int result = calc.calculateTotal();
        System.out.println(result);
    }
}
