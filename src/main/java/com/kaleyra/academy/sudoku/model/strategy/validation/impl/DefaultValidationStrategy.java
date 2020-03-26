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
        int x = 0;
        int y = 0;

        if(rowForValue < 3 && colForValue < 3)
        {
            x = 0;
            y = 0;
        }
        else if(rowForValue < 3 && colForValue > 2 && colForValue < 6)
        {
            x = 0;
            y = 3;
        }
        else if(rowForValue < 3 && colForValue > 5)
        {
            x = 0;
            y = 6;
        }
        else if(rowForValue < 6 && rowForValue > 2 && colForValue < 3)
        {
            x = 3;
            y = 0;
        }
        else if(rowForValue < 6 && rowForValue > 2 && colForValue > 2 && colForValue < 6)
        {
            x = 3;
            y = 3;
        }
        else if(rowForValue < 6 && rowForValue > 2 && colForValue > 5)
        {
            x = 3;
            y = 6;
        }
        else if(rowForValue > 6 && colForValue < 3)
        {
            x = 6;
            y = 0;
        }
        else if(rowForValue > 5 && colForValue > 2 && colForValue < 6)
        {
            x = 6;
            y = 3;
        }
        else if(rowForValue > 5 && colForValue > 5)
        {
            x = 6;
            y = 6;
        }

        for(int i = x; i < x + 2; i++)
            for(int j = y; j < y + 2; j++)
                if(matrix[i][j] == value)
                {
                    //System.out.println("entrato con: " + matrix[i][j]);
                    return true;
                }

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

        boolean ret = false;
        boolean invalid = false;

        if(model.length >= 8 && model != null && !invalid)
            ret = true;

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(model[i][j] < 0 || model[i][j] > 8)
                {
                    ret = false;
                    invalid = true;
                }
        return ret;
    }
}
