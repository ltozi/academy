package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

import java.util.HashSet;
import java.util.stream.IntStream;

public class DefaultValidationStrategy implements SudokuValidationStrategy {

    @Override
    public boolean isValidMove(int[][] model, int row, int col, int value) {
        return isValidForColumn(model, row, col, model[row][col]) && isValidForRow(model, row, col, model[row][col])
                && isValidForQuadrant(model, row, col, model[row][col]);
    }

    public boolean isValidForQuadrant(int[][] matrix, int rowForValue, int colForValue, int value) {
        int startI = rowForValue - rowForValue % 3;
        int startJ = colForValue - colForValue % 3;
        String box = "";
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (startI + i == rowForValue && startJ + j == colForValue) continue;
                box += matrix[startI + i][startJ + j];
            }
        return !box.replaceAll("0", "").contains("" + value);
    }

    public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
        String row = "";
        for (int i = 0; i < 9; i++) {
            if (i == colForValue) continue;
            row += matrix[rowForValue][i];
        }
        return !row.replaceAll("0", "").contains("" + value);
    }

    public boolean isValidForColumn(int[][] matrix, int rowForValue, int colForValue, int value) {

        String col = "";
        for (int i = 0; i < 9; i++) {
            if (i == rowForValue) continue;
            col += matrix[i][colForValue];
        }
        return !col.replaceAll("0", "").contains("" + value);
    }

    public boolean isValidModel(int[][] model) {
        boolean cond = true;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                cond = cond && isValidMove(model, i, j, model[i][j]);
            }
        return cond;
    }

    /*public boolean isValidSudoku(int[][] board) {
        HashSet<String> seen = new HashSet();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int current_val = board[i][j];
                if (current_val != '_') {
                    if (!seen.add(current_val + "found in row" + i) ||
                            !seen.add(current_val + "found in column" + j) ||
                            !seen.add(current_val + "found in sub box" + i / 3 + "_" + j / 3)) return
                            false;
                }
            }
        }
        return true;
    }*/

    private static int BOARD_SIZE = 9;
    private static int BOARD_START_INDEX = 0;
    private static int SUBSECTION_SIZE = 3;
    private static int NO_VALUE = 0;
    private static int MIN_VALUE = 1;
    private static int MAX_VALUE = 9;

    public boolean solve(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            printBoard(board);
                            return true;
                        }
                        board[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }

    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private boolean checkConstraint(
            int[][] board,
            int row,
            boolean[] constraint,
            int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    public void printBoard(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.print(" \n ");
        }
    }
}
