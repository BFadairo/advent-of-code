package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryDiagnostic {

    public static void diagnose(String filePath) {
        List<String> inputs = readFile(filePath);
        double powerConsumption = partOne(inputs);
        System.out.println(powerConsumption);
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
        for (int i = 0; i < length ; i++) {
            int numOfZeros = 0;
            int numOfOnes = 0;
            for (int j = 0; j < inputs.size(); j++) {
                String binaryString = inputs.get(j);
                char currentNum = binaryString.charAt(i);
                if (currentNum == '0') {
                    numOfZeros++;
                } else {
                    numOfOnes++;
                }
            }
            if (numOfOnes > numOfZeros) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        double gammaD = convertToDecimal(gamma.toString());
        double epsilonD = convertToDecimal(epsilon.toString());
        return gammaD * epsilonD;
    }

    private static void partTwo(List<String> inputs) {

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
