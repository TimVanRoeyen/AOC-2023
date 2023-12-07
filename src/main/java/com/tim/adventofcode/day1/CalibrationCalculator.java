package com.tim.adventofcode.day1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class CalibrationCalculator {
    public int calculateCalibration(){
        int result = 0;
        File file = new File("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day1\\input.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                char[] output = new char[2];
                String line = replaceLettersWithNumber(sc.nextLine());
                System.out.println("Updated string is " + line);
                boolean nrRead = false;
                String firstNumber = "", lastNumber = "";
                for(char c:line.toCharArray()){
                    if(Character.isDigit(c)){
                        String digit = String.valueOf(c);
                        if(!nrRead){
                            firstNumber = digit;
                            nrRead = true;
                        }
                        lastNumber = digit;
                    }
                }
                System.out.printf("First number is %s, last number is %s. ", firstNumber, lastNumber);
                output[0] = firstNumber.charAt(0);
                output[1] = lastNumber.charAt(0);
                String value = new String(output);
                System.out.println("Adding value: " + value);
                result += Integer.parseInt(value);
                System.out.printf("Total result so far is %d%n", result);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    public int calculateCalibrationLambda(){
        int result = 0;
        String input = "";
        try {
            input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day1\\input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = input.lines()
                //.map(s -> replaceLettersWithNumber(s)) //Part 2: replace all lettered words with numbers
                .map(String::chars)
                .map(intStream -> intStream.filter(Character::isDigit).toArray())
                .map(ints -> (char) ints[0] + ""+ (char) ints[ints.length-1])
                .mapToInt(Integer::parseInt)
                .sum();

        return result;
    }

    private static String replaceLettersWithNumber(String s){
        return s.replaceAll("one","o1ne")
                .replaceAll("two","t2wo")
                .replaceAll("three","t3hree")
                .replaceAll("four","f4our")
                .replaceAll("five","f5ive")
                .replaceAll("six","s6ix")
                .replaceAll("seven","s7even")
                .replaceAll("eight","e8ight")
                .replaceAll("nine","n9ine")
                .replaceAll("zero","z0ero");
    }

}
