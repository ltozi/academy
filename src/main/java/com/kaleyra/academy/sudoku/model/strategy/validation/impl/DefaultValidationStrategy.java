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
        return false;
    }


    public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        if(value == 0)
            value = -1;

        for(int i = 0; i < 9; i++) {
            if(matrix[i][colForValue] == value)
                return false;
        }
        return true;
    }

    public boolean isValidForColumn(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        /*
        for(int i = 0; i < 9; i++) {
            if(matrix[rowForValue][i] == value)
                return false;
        }
        return true;    */

        matrix[rowForValue][colForValue] = value;
        return true;
    }

    public boolean isValidModel(int[][] model) {
        //TODO
        return false;
    }
}
