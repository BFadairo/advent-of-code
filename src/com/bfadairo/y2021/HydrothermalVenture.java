package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HydrothermalVenture {

    public static void venture(String filePath) {
        List<String[]> coordinatePairs = readFile(filePath);
        Pair maxXAndY = findMaxXAndY(coordinatePairs);
        int[][] plane = plotCoordinates(coordinatePairs, maxXAndY);
        int intersections = countIntersectionPoints(plane);
    }

    private static List<String[]> readFile(String filePath) {
        // Read the input file
        File file = new File(filePath);
        List<String[]> inputList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputList.add(cleanInput(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an issue locating the file: %s", e.getMessage());
        }
        return inputList;
    }

    private static String[] cleanInput(String input) {
        String coordinate = input.replaceAll(" ->", "").trim();
        return coordinate.split("[\\W]");
    }

    private static Pair findMaxXAndY(List<String[]> coordinates) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (String[] coordinate: coordinates) {
            int x1 = Integer.parseInt(coordinate[0]);
            int y1 = Integer.parseInt(coordinate[1]);
            int x2 = Integer.parseInt(coordinate[2]);
            int y2 = Integer.parseInt(coordinate[3]);
            maxX = Math.max(x1, Math.max(maxX, x2));
            maxY = Math.max(y1, Math.max(maxY, y2));
        }

        return new Pair(maxX + 1, maxY + 1);
    }
    private static int[][] plotCoordinates(List<String[]> coordinates, Pair bounds) {
        int[][] plane = new int[bounds.valOne][bounds.valTwo];

        for (String[] coordinate: coordinates) {
            int x1 = Integer.parseInt(coordinate[0]);
            int y1 = Integer.parseInt(coordinate[1]);
            int x2 = Integer.parseInt(coordinate[2]);
            int y2 = Integer.parseInt(coordinate[3]);
            if (x1 != x2 && y1 != y2) continue;
            if (y1 == y2) {
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    for (int j = y2; j <= y2; j++) {
                        int valueOne = plane[j][i];
                        if (valueOne == 0) {
                            plane[j][i] = 1;
                        } else if (valueOne > 0) {
                            plane[j][i] += 1;
                        }
                    }
                }
            } else if (x1 == x2) {
                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                    for (int j = x1; j <= x2; j++) {
                        int valueOne = plane[i][j];
                        if (valueOne == 0) {
                            plane[i][j] = 1;
                        } else if (valueOne > 0) {
                            plane[i][j] += 1;
                        }
                    }
                }
            }
        }
        return plane;
    }

    private static int countIntersectionPoints(int[][] plane) {
        int rows = plane.length;
        int cols = plane[0].length;
        int intersectionPoints = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (plane[i][j] > 1) {
                    intersectionPoints++;
                }
            }
        }
        return intersectionPoints;
    }
}
