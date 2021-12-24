package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LanternFish {

    public static void track(String filePath) {
        String[] strArr = readFile(filePath);
        List<Integer> fishTracker = convertToList(strArr);
//        System.out.println(partOne(fishTracker, 80));
        System.out.println(partTwo(fishTracker, 256));
    }

    private static String[] readFile(String filePath) {
        File file = new File(filePath);
        String[] strNumArr = null;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                strNumArr = str.split(",");
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an error locating the file: %s\n", e.getMessage());
        }
        return strNumArr;
    }

    private static List<Integer> convertToList(String[] strArr) {
        List<Integer> tracker = new ArrayList<>();
        for (String str : strArr) {
            tracker.add(Integer.parseInt(str));
        }
        return tracker;
    }

    private static long partOne(List<Integer> fish, int days) {
        long numOfFish = fish.size();
        int daysPassed = 1;
        while (daysPassed <= days) {
            long fishToAdd = 0;
            for (int i = 0; i < numOfFish; i++) {
                if (fish.get(i) == 0) {
                    fish.set(i, 6);
                    fish.add(8);
                    fishToAdd++;
                } else {
                    fish.set(i, fish.get(i) - 1);
                }
            }
            numOfFish += fishToAdd;

            daysPassed++;
        }
        return numOfFish;
    }

    private static List<Long> createList() {
        return new ArrayList<>() {
            {
                add(0L);
                add(0L);
                add(0L);
                add(0L);
                add(0L);
                add(0L);
                add(0L);
                add(0L);
                add(0L);
            }
        };
    }
    private static long partTwo(List<Integer> fish, int days) {
        List<Long> fishPerAge = createList();

        for (int fishy : fish) {
            fishPerAge.set(fishy, fishPerAge.get(fishy) + 1);
        }

        for (int day = 0; day < days; day++) {
            List<Long> tempFishList = createList();

            for (int nextAge = 7; nextAge >= 0; nextAge--) {
                if (nextAge == 0) {
                    tempFishList.set(8, fishPerAge.get(0));

                    tempFishList.set(6, tempFishList.get(6) + fishPerAge.get(0));
                }

                tempFishList.set(nextAge, fishPerAge.get(nextAge + 1));
            }
            fishPerAge.clear();
            fishPerAge.addAll(tempFishList);
        }

        long sum = fishPerAge.stream().mapToLong(numOfFish -> numOfFish).sum();

        return sum;
    }
}
