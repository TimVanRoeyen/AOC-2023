package com.tim.adventofcode.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearCalculator {
    private static int total = 0;
    private static final String input;
    private static final char[][] inputArray;

    private static final Set<Character> symbols = new HashSet<>(Arrays.asList('$','&','*','+','-','=','#','%','/','@','_'));
    static {
        try {
            input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day3\\input.txt"));
            // Convert input to char[][]
            String[] lines = input.split("\n");
            inputArray = new char[lines.length][];
            for(int i = 0; i < lines.length; i++){
                inputArray[i] = lines[i].toCharArray();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int calculateTotal() {
        int currentPosX = 0, currentPosY = 0;

        for(char[] line:inputArray){
            System.out.println("Current Y position: " + currentPosY);
            String numberString = "";
            for(char c:line){
                if(Character.isDigit(c)){
                    int intPos = currentPosX;
                    boolean usableNumber = false;
                    System.out.printf("Character at X %d Y %d is a digit: %c%n", currentPosX, currentPosY, c);
                    while(Character.isDigit(line[intPos])){
                        if(!usableNumber) {
                            usableNumber = checkIfValidNumber(intPos, currentPosY);
                        }
                        numberString += line[intPos];
                        intPos++;
                    }
                    System.out.println("Found number: " + numberString);
                    if(!numberString.equals("") && usableNumber){
                        increaseTotal(Integer.parseInt(numberString));
                    }
                    currentPosX += numberString.length()-1;
                    numberString = "";
                    System.out.println("Total: " + total);
                }
                currentPosX++;
            }
            currentPosY++;
            currentPosX = 0;
        }

        return total;
    }

    private static boolean checkIfValidNumber(int x, int y){
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if((x + i) == -1 || x + i == 140){

                } else if ((y + j) == -1 || y + j == 140) {

                } else {
                    if (symbols.contains(inputArray[y+j][x+i])){
                        System.out.printf("Number at Y%d X%d is a special sign!%n", y+j+1, x+i+1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void increaseTotal(int amount){
        total += amount;
    }
}
