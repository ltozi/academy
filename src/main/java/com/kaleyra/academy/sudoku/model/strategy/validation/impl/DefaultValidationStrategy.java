package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

public class DefaultValidationStrategy implements SudokuValidationStrategy {


    @Override
    public boolean isValidMove(int[][] model, int row, int col, int value) {
        return false;
    }

    /**
     * To identify the starting point of the box the following formulas are used:
     *
     * - (row - row%3)  (e.g. in case of row 8 the box's top left coordinate is 6)
     * - (col - col%3)  (e.g. in case of col 7, the box's top left corner is still 6)
     *
     * @param matrix
     * @param rowForValue
     * @param colForValue
     * @param value
     * @return
     */
    public boolean isValidForQuadrant(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        boolean isValid = true;
        int startingRow = rowForValue - rowForValue % 3;
        int startingCol = colForValue - colForValue % 3;
        if (value != 0) {
            for (int i = startingRow; i < startingRow + 3; i++) {
                for (int j = startingCol; j < startingCol + 3; j++) {
                    if (matrix[i][j] == value) {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return isValid;
    }


    public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        boolean isValid = true;
        for (int i = 0; i < 9; i++){
            if (matrix[rowForValue][i] == value) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }



    public boolean isValidForColumn(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        boolean isValid = true;
        for (int i = 0; i < 9; i++){
            if (matrix[i][colForValue] == value) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public boolean isValidModel(int[][] model) {
        //TODO
        boolean isValid = true;

        return isValid;
    }
}
