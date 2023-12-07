package com.tim.adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class sol {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day1\\input.txt"));
        int sum = input.lines()
                .map(String::chars)
                .map(intStream -> intStream.filter(Character::isDigit).toArray())
                .map(ints -> (char) ints[0] + "" + (char) ints[ints.length - 1])
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(sum);
    }
}
