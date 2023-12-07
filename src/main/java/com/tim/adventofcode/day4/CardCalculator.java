package com.tim.adventofcode.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CardCalculator {
    private static int total = 0;
    private static final String input;

    static {
        try {
            input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day4\\input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int calculateTotal() {
        /*input.lines()
                .map(s -> s.split(":"))
                .map(s -> s[1].split("\\|"))
                .forEach(s -> {
                    List<Integer> winningNumbers = new ArrayList<>(), scratchedNumbers = new ArrayList<>();
                    String[] winningNumbersStrings = s[0].trim().split(" ");
                    String[] scratchedNumbersStrings = s[1].trim().split(" ");
                    for(int i = 0; i < winningNumbersStrings.length; i++){
                        if(winningNumbersStrings[i] != ""){
                            winningNumbers.add(Integer.parseInt(winningNumbersStrings[i]));

                        }
                    }
                    for(int i = 0; i < scratchedNumbersStrings.length; i++){
                        if(scratchedNumbersStrings[i] != "") {
                            scratchedNumbers.add(Integer.parseInt(scratchedNumbersStrings[i]));
                        }
                    }
                    calculatePointsForRow(winningNumbers, scratchedNumbers);
                });
        System.out.println("Returning total: " + total);
         */
        for(int i = 0; i < input.lines().count(); i++)
        {
            calculatePointsForRow(i);
        }
        System.out.println("Total is " + total);
        return total;
    }

    private void calculatePointsForRow(List<Integer> winningNumbers, List<Integer> scratchedNumbers){
        int winningCount = 0;
        for(int nr:scratchedNumbers){
            if(winningNumbers.contains(nr)){
                winningCount++;
            }
        }
        if(winningCount > 0){
            total += Math.pow(2,winningCount-1);
        }
        System.out.printf("Amount of winning numbers: %d. Results in %f added to total. New total is %d.%n ", winningCount, Math.pow(2,winningCount-1), total);
    }

    private void calculatePointsForRow(int row){
        List<Integer> winningNumbersForRow = new ArrayList<>(), scratchedNumbers = new ArrayList<>();
        String rowString = input.split("\n")[row];
        int winningCount = 0;
        String[] winningNumbersForRowString = rowString.split(":")[1].split("\\|")[0].trim().split(" ");
        for (String value : winningNumbersForRowString) {
            if (value != "") {
                winningNumbersForRow.add(Integer.parseInt(value));
            }
        }
        String[] scratchedNumbersString = rowString.split(":")[1].split("\\|")[1].trim().split(" ");
        for (String s : scratchedNumbersString) {
            if (s != "") {
                scratchedNumbers.add(Integer.parseInt(s));
            }
        }
        for(int nr:scratchedNumbers){
            if(winningNumbersForRow.contains(nr)){
                winningCount++;
            }
        }
        System.out.printf("Winning count of row %d is %d%n", row, winningCount);
        for(int i = 1; i <= winningCount; i++){
            calculatePointsForRow(row + i);
        }
        total++;
        //System.out.println("String at row " + row + " is: " + rowString);
        /*
        for(int nr:scratchedNumbers){
            if(winningNumbers.contains(nr)){
                winningCount++;
            }
        }
        if(winningCount > 0){
            total += Math.pow(2,winningCount-1);
        }
        System.out.printf("Amount of winning numbers: %d. Results in %f added to total. New total is %d.%n ", winningCount, Math.pow(2,winningCount-1), total);
        */
    }
}
