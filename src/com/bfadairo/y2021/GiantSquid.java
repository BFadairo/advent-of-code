package com.bfadairo.y2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Bingoooo
 */
public class GiantSquid {

    public static void bingo(String filePath) {
        Queue<Integer> queue = new LinkedList<>();
        List<int[][]> bingoBoards = readFile(filePath, queue);
//        int score = partOne(bingoBoards, queue);
        int score = partTwo(bingoBoards, queue);
    }

    private static List<int[][]> readFile(String filePath, Queue<Integer> q) {
        // Read the input file
        File file = new File(filePath);
        int lineCount = 0;
        int count = 0;
        List<int[][]> bingoBoards = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() && sc.hasNext()) {
                if (lineCount == 0) {
                    String[] arr = sc.nextLine().split(",");
                    for (String num: arr) {
                        q.offer(Integer.parseInt(num));
                    }
                } else {
                    int[][] board = new int[5][5];
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            board[i][j] = Integer.parseInt(sc.next());
                        }
                    }
                    bingoBoards.add(board);
                }
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.printf("There was an issue locating the file: %s", e.getMessage());
        }
        return bingoBoards;
    }

    private static int partOne(List<int[][]> boards, Queue<Integer> queue) {
        List<Integer> pulledNumbers = new ArrayList<>();

        while (!queue.isEmpty()) {
            int pulledNum = queue.poll();
            for (int[][] board : boards) {
                updateBoard(board, pulledNum);
                boolean winner = checkForWinner(board);
                if (winner) {
                    int sum = calculateUnmarkedSum(board);
                    return sum * pulledNum;
                }
            }
        }
        return 0;
    }

    private static int partTwo(List<int[][]> boards, Queue<Integer> queue) {
        List<Integer> pulledNumbers = new ArrayList<>();

        int listSize = boards.size();

        List<int[][]> cloneBoards = new ArrayList<>(boards);
        while (!queue.isEmpty()) {
            int pulledNum = queue.poll();
            HashSet<int[][]> winners = new HashSet<>();
            for (int[][] board : boards) {
                updateBoard(board, pulledNum);
                boolean winner = checkForWinner(board);
                if (winner) {
                    cloneBoards.remove(board);
                    if (cloneBoards.size() == 0) { // Last Item to be removed
                        int sum = calculateUnmarkedSum(board);
                        return sum * pulledNum;
                    }
                }
            }
        }
        return 0;
    }

    private static void updateBoard(int[][] board, int value) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == value) {
                    board[i][j] = board[i][j] * -1;
                }
            }
        }
    }

    private static boolean checkForWinner(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Check rows
        for (int i = 0; i < rows; i++) {
            int positiveSum = 0;
            for (int j = 0; j < cols; j++) {
                int value = board[i][j];
                if (value > 0) {
                    positiveSum += value;
                }
            }
            if (positiveSum == 0) {
//                System.out.println("Found winning row");
                return true;
            }
        }

        // Check columns
        for (int column = 0; column < cols; column++) {
            int positiveSum = 0;
            for (int row = 0; row < rows; row++) {
                int value = board[row][column];
                if (value > 0) {
                    positiveSum += value;
                }
            }
            if (positiveSum == 0) {
//                System.out.println("found winning column");
                return true;
            }
        }

        return false;
    }

    private static int calculateUnmarkedSum(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int unmarkedSum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] > 0) {
                    unmarkedSum += board[i][j];
                }
            }
            System.out.println(unmarkedSum);
        }

        return unmarkedSum;
    }
}
