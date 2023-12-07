package com.tim.adventofcode.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationCalculator {
    private static long lowestLocation = -1;
    private static final String input;
    private List<Long> seeds;
    private ArrayList<List<Long>> soils, fertilizers, waters, lights, temperatures, humidities, locations;

    static {
        try {
            input = Files.readString(Path.of("C:\\Users\\GamePc\\IdeaProjects\\AdventOfCode\\src\\main\\java\\com\\tim\\adventofcode\\day5\\input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long calculateLowestLocation() {
        initLists();
        for(int seedCounter = 0; seedCounter <= (seeds.size()+2); seedCounter += 2){
            Long currentSeed = seeds.get(seedCounter);
            for(int i = 0; i < seeds.get(seedCounter+1); i++){
                Long loc = findLocationOfSeed(currentSeed+i);
                if(lowestLocation == -1){
                    lowestLocation = loc;
                } else if (lowestLocation > loc) {
                    lowestLocation = loc;
                }
            }
            System.out.println("Intermediary lowest location: " + lowestLocation);
        }
        /*
        for(long l:seeds){
            Long loc = findLocationOfSeed(l);
            System.out.printf("Location of seed %d is %d\n",l, loc);
            if(lowestLocation == -1){
                lowestLocation = loc;
            } else if (lowestLocation > loc) {
                lowestLocation = loc;
            }
            counter += counter + 2;
        }

         */
        return lowestLocation;
    }
    private void initLists(){
        seeds = new ArrayList<>();
        for(String s:input.lines().findFirst().get().split(":")[1].split(" ")){
            if(!s.equalsIgnoreCase("")){
                seeds.add(Long.parseLong(s));
            }
        }
        soils = new ArrayList<>();
        populateListFromEntry(soils, 2);
        fertilizers = new ArrayList<>();
        populateListFromEntry(fertilizers, 3);
        waters = new ArrayList<>();
        populateListFromEntry(waters, 4);
        lights = new ArrayList<>();
        populateListFromEntry(lights, 5);
        temperatures = new ArrayList<>();
        populateListFromEntry(temperatures, 6);
        humidities = new ArrayList<>();
        populateListFromEntry(humidities, 7);
        locations = new ArrayList<>();
        populateListFromEntry(locations, 8);
    }
    private List<Long> stringToLong(String s){
        List<Long> list = new ArrayList<>();
        for(String nr:s.split(" ")){
            if (isConvertibleToLong(nr.replaceFirst("\r",""))){
                list.add(Long.parseLong(nr.replaceFirst("\r","")));
            }
        }
        return list;
    }
    private void populateListFromEntry(ArrayList<List<Long>> entry, int part){
        for(String s:input.split(":")[part].strip().split("\n")){
            if(isConvertibleToLong(s.substring(0,1))) {
                entry.add(stringToLong(s));
            }
        }
    }
    private static boolean isConvertibleToLong(String s) {
        try {
            Long.parseLong(s);
            return true; // Successfully parsed as long
        } catch (NumberFormatException e) {
            return false; // Cannot be parsed as long
        }
    }

    private long findLocationOfSeed(long seed){
        // Get seed to soil
        long soilId = calculateFromTo(seed, soils);
        long fertilizerId = calculateFromTo(soilId, fertilizers);
        long waterId = calculateFromTo(fertilizerId, waters);
        long lightId = calculateFromTo(waterId, lights);
        long temperatureId = calculateFromTo(lightId, temperatures);
        long humidityId = calculateFromTo(temperatureId, humidities);
        long locationResult = calculateFromTo(humidityId, locations);
        // Return final result (location)
        return locationResult;
    }
    private long calculateFromTo(long seed, List<List<Long>> dest){
        for(List<Long> sl:dest){
            if(seed >= sl.get(1) && seed <= (sl.get(1)+sl.get(2))){
                return (sl.get(0)+(seed - sl.get(1)));
            }
        }
        return seed;
    }
}
