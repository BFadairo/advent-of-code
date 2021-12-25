package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class WhaleTreachery {

    public static void align(String filePath) {
        int[] submarinePositions = readFile(filePath);
        System.out.println(calculateFuelPartOne(submarinePositions));
        System.out.println(calculateFuelPartTwo(submarinePositions));
    }

    public static int[] readFile(String filePath) {
        File file = new File(filePath);
        int[] positionsArr = null;
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(",");
                positionsArr = Arrays.stream(input).flatMapToInt(str -> IntStream.of(Integer.parseInt(str))).toArray();
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an error locating the file: %s\n", e.getMessage());
        }
        return positionsArr;
    }

    private static int calculateFuelPartOne(int[] positions) {

        int minFuel = Integer.MAX_VALUE;

        for (int i = 1; i < positions.length; i++) {
            int totalFuelCost = 0;

            for (int j = 0; j < positions.length; j++) {
                totalFuelCost += Math.abs(positions[j] - i);
            }

            minFuel = Math.min(totalFuelCost, minFuel);
//            System.out.printf("Fuel Cost: %d, Position: %d\n", totalFuelCost, i);
        }
        return minFuel;
    }

    private static int calculateFuelPartTwo(int[] positions) {

        int minFuel = Integer.MAX_VALUE;

        for (int i = 1; i < positions.length; i++) {
            int totalFuelCost = 0;

            for (int position : positions) {
                int fuelCost = 1;
                int totalFuelCostForPosition = 0;
                int currentVal = position;
                if (currentVal > i) {
                    while (currentVal > i) {
                        totalFuelCostForPosition += fuelCost;
                        fuelCost++;
                        currentVal--;
                    }
                } else if (currentVal < i) {
                    while (currentVal < i) {
                        totalFuelCostForPosition += fuelCost;
                        fuelCost++;
                        currentVal++;
                    }
                }

                totalFuelCost += totalFuelCostForPosition;
            }

            minFuel = Math.min(totalFuelCost, minFuel);
        }
        return minFuel;
    }
}
