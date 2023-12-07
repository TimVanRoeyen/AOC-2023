package com.tim.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeCalculator {
    private static int total = 0;
    private static int powerTotal = 0;
    public int calculateTotalId() {
        String input = "";
        int redLimit = 12, greenLimit = 13, blueLimit = 14;
        String numberRegex = "\\b(\\d+)\\b";
        Pattern pattern = Pattern.compile(numberRegex);
        try {
            input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day2\\input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        input.lines()
                .map(s -> s.split(":"))
                .forEach(s -> {
                    boolean valid = true;
                    int minimumRed = 0, minimumBlue = 0, minimumGreen = 0;
                    Matcher m = pattern.matcher(s[0]);
                    int gameid = m.find() ? Integer.parseInt(m.group(1)) : 0, cubeCount = 0;
                    System.out.println("Game ID: " + gameid);
                    for(String entry:s){
                        if(!entry.substring(0,4).equalsIgnoreCase("game")){
                            String[] games = entry.split(";");
                            for(String game:games){
                                System.out.println("Game: " + game);
                                for(String draw:game.split(",")){
                                    m = pattern.matcher(draw);
                                    cubeCount = m.find() ? Integer.parseInt(m.group(0)):0;
                                    if(draw.endsWith("red")){
                                        System.out.println("Red cubes: " + cubeCount);
                                        if(cubeCount > redLimit){
                                            valid = false;
                                        }
                                        if(cubeCount > minimumRed){
                                            minimumRed = cubeCount;
                                        }
                                    } else if (draw.endsWith("blue")) {
                                        System.out.println("Blue cubes: " + cubeCount);
                                        if(cubeCount > blueLimit){
                                            valid = false;
                                        }
                                        if(cubeCount > minimumBlue){
                                            minimumBlue = cubeCount;
                                        }
                                    } else if (draw.endsWith("green")) {
                                        System.out.println("Green cubes: " + cubeCount);
                                        if(cubeCount > greenLimit){
                                            valid = false;
                                        }
                                        if(cubeCount > minimumGreen){
                                            minimumGreen = cubeCount;
                                        }
                                    } else System.out.println("No colour found");
                                }
                            }
                            System.out.printf("Minimum red: %d // Minimum green: %d // Minimum blue: %d%n", minimumRed, minimumGreen, minimumBlue);
                        }
                    }
                    System.out.println("Boolean result: " + valid);
                    if(valid) incrementTotal(gameid);
                    System.out.println("Game ID total: " + total);
                    incrementPowerTotal(minimumRed * minimumBlue * minimumGreen);
                    System.out.println("Power total: " + powerTotal);
                    System.out.println("------------------------------------------");
                });
        return total;
    }

    private static void incrementTotal(int amount){
        total += amount;
    }
    private static void incrementPowerTotal(int amount){
        powerTotal += amount;
    }}
