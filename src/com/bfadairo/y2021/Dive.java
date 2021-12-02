package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dive {

    public static void dive(String filePath) {
        List<Move> moves = readFile(filePath);
        int product = calculatePosition(moves);
    }

    static class Move {

        String direction;
        int amount;

        public Move(String direction, int amount) {
            this.direction = direction;
            this.amount = amount;
        }
    }

    private static List<Move> readFile(String filePath) {
        List<Move> moves = new ArrayList<>();
        File input = new File(filePath);
        try {
            Scanner sc = new Scanner(input);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split(" ");
                Move move = new Move(arr[0], Integer.parseInt(arr[1]));
                moves.add(move);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an error loading the file %s\n", e.getMessage());
        }
        return moves;
    }

    private static int calculatePosition(List<Move> moves) {
        int depth = 0;
        int horizontalPosition = 0;

        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            switch (move.direction) {
                case "forward":
                    horizontalPosition += move.amount;
                    break;
                case "backward":
                    horizontalPosition -= move.amount;
                    break;
                case "up":
                    depth -= move.amount;
                    break;
                case "down":
                    depth += move.amount;
                    break;
            }
        }
        return depth * horizontalPosition;
    }
}
