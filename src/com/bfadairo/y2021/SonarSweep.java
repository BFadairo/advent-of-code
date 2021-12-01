package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SonarSweep {

    public static void Sweep(String filePath) {
        List<Integer> depths = readFile(filePath);
        int partOneAns = partOneCountDepthIncreases(depths);
        int partTwoAns = partTwoCountThreeSumDepthIncreases(depths);
        System.out.println("Part One Ans = " + partOneAns);
        System.out.println("Part Two Ans = " + partTwoAns);
    }

    /**
     * Reads the question inputs from the passed in txt file String
     * @param filePath - The path to the input file
     * @return - A List of Integers from the File
     */
    private static List<Integer> readFile(String filePath) {
        // Read the input file
        File file = new File(filePath);
        List<Integer> inputList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputList.add(Integer.valueOf(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an issue locating the file: %s", e.getMessage());
        }

        return inputList;
    }

    /**
     * Iterates through the Sweeps List and returns the amount of times the next value is greater than the previous
     * O(N) Runtime
     * @param depths - A List of Sweeps
     * @return - Amount of times next value was greater than previous
     */
    private static int partOneCountDepthIncreases(List<Integer> depths) {
        int count = 0;

        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) count++;
        }

        return count;
    }

    /**
     * Iterates through the Sweeps list and returns the amount of times sum of the next three values
     * is greater than the previous sum
     * @param depths - List of Depth Measurements from Sonar Sweeps
     * @return - # of times depth increases from previous
     */
    private static int partTwoCountThreeSumDepthIncreases(List<Integer> depths) {
        int previousSum = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 2; i < depths.size(); i++) {
            int valOne = depths.get(i - 2);
            int valTwo = depths.get(i - 1);
            int valThree = depths.get(i);
            int sum = valOne + valTwo + valThree;

            if (sum > previousSum) {
                count++;
            }

            previousSum = sum;
        }

        return count;
    }
}
