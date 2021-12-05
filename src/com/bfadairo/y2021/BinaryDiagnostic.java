package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pair {
    int valOne;
    int valTwo;

    public Pair(int one, int two) {
        this.valOne = one;
        this.valTwo = two;
    }
}

public class BinaryDiagnostic {

    public static void diagnose(String filePath) {
        List<String> inputs = readFile(filePath);
        double powerConsumption = partOne(inputs);
        double oxygenRating = partTwo(inputs);
        double co2Rating = partTwoCoTwo(inputs);
        System.out.println(oxygenRating * co2Rating);
    }

    private static List<String> readFile(String filePath) {
        // Read the input file
        File file = new File(filePath);
        List<String> inputList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputList.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an issue locating the file: %s", e.getMessage());
        }
        return inputList;
    }

    private static double partOne(List<String> inputs) {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        int length = inputs.get(0).length();
        for (int i = 0; i < length; i++) {
            Pair count = countNums(inputs, i);
            int numOfZeros = count.valOne;
            int numOfOnes = count.valTwo;

            if (numOfOnes > numOfZeros) {
                gamma.append("1");
                epsilon.append("0");
            } else if (numOfOnes < numOfZeros) {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        double gammaD = convertToDecimal(gamma.toString());
        double epsilonD = convertToDecimal(epsilon.toString());
        return gammaD * epsilonD;
    }

    private static double partTwo(List<String> inputs) {
        List<String> oxyGenerator = new ArrayList<>(inputs.size());
        oxyGenerator.addAll(inputs);
        int length = inputs.get(0).length();
        for (int i = 0; i < length; i++) {
            Pair count = countNums(oxyGenerator, i);
            int numOfOnes = count.valOne;
            int numOfZeroes = count.valTwo;
            Character common = numOfOnes >= numOfZeroes ? '1' : '0';
            Character leastCommon = numOfZeroes <= numOfOnes ? '0' : '1';

            for (int j = 0; j < oxyGenerator.size(); j++) {
                String diagnostic = oxyGenerator.get(j);
                if (diagnostic.charAt(i) == leastCommon) {
                    oxyGenerator.remove(diagnostic);
                    j--;
                }
            }
        }

        return convertToDecimal(oxyGenerator.get(0));
    }

    private static double partTwoCoTwo(List<String> inputs) {
        List<String> coScrubber = new ArrayList<>(inputs.size());
        coScrubber.addAll(inputs);
        int length = inputs.get(0).length();
        for (int i = 0; i < length; i++) {
            Pair count = countNums(coScrubber, i);
            int numOfOnes = count.valOne;
            int numOfZeroes = count.valTwo;

            Character common = numOfOnes >= numOfZeroes ? '1' : '0';
            Character leastCommon = numOfZeroes <= numOfOnes ? '0' : '1';

            for (int j = 0; j < coScrubber.size(); j++) {
                if (coScrubber.size() == 1) break;
                String diagnostic = coScrubber.get(j);
                if (diagnostic.charAt(i) == common) {
                    coScrubber.remove(diagnostic);
                    j--;
                }
            }
        }

        return convertToDecimal(coScrubber.get(0));
    }

    private static Pair countNums(List<String> inputs, int index) {
        int numOfZeros = 0;
        int numOfOnes = 0;
        for (int j = 0; j < inputs.size(); j++) {
            String binaryString = inputs.get(j);
            char currentNum = binaryString.charAt(index);
            if (currentNum == '0') {
                numOfZeros++;
            } else {
                numOfOnes++;
            }
        }
        return new Pair(numOfOnes, numOfZeros);
    }

    private static double convertToDecimalManual(String input) {
        double value = 0;
        int count = input.length() - 1;
        for (int i = 0; i < input.length(); i++) {
            double num = Double.parseDouble(input.substring(i, i + 1));
            value += (num * Math.pow(2, count));

            count--;
        }

        System.out.println(value);
        return value;
    }

    private static double convertToDecimal(String input) {
        return Integer.parseInt(input, 2);
    }
}
