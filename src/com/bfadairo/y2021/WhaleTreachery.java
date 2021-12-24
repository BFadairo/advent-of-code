package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class WhaleTreachery {

    public static void align(String filePath) {
        int[] submarinePositions = readFile(filePath);
        System.out.println(calculateFuel(submarinePositions));
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

    private static int calculateFuel(int[] positions) {

        int minFuel = Integer.MAX_VALUE;

        for (int i = 1; i < positions.length; i++) {
            int fuelCost = 0;
            for (int j = 0; j < positions.length; j++) {
                fuelCost += Math.abs(positions[j] - i);
            }

            minFuel = Math.min(fuelCost, minFuel);
//            System.out.printf("Fuel Cost: %d, Position: %d\n", fuelCost, i);
        }
        return minFuel;
    }
}
