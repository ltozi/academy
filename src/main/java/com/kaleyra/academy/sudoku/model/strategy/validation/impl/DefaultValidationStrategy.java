package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

public class DefaultValidationStrategy implements SudokuValidationStrategy {


    @Override
    public boolean isValidMove(int[][] model, int row, int col, int value) {
        boolean ret = true;

        ret = isValidForQuadrant(model, row, col, value);

        if(!isValidForRow(model, row, col, value))
            ret = false;

        if(!isValidForColumn(model, row, col, value))
            ret = false;

        if(value == 0 && ret)
            model[row][col] = 0;

        return ret;
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
        boolean ret = true;

        if(rowForValue < 3)
            x = 0;
        else if(rowForValue >= 3 && rowForValue < 6)
            x = 3;
        else if(rowForValue >= 6)
            x = 6;

        if(colForValue < 3)
            y = 0;
        else if(colForValue >= 3 && colForValue < 6)
            y = 3;
        else if(colForValue >= 6)
            y = 6;


        for(int i = x; i < x + 3; i++)
            for(int j = y; j < y + 3; j++)
                if(matrix[i][j] == value)
                {
                    ret = false;
                }

        return ret;
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
        boolean ret = true;

        for(int i = 0; i < 9; i++) {
            if(matrix[rowForValue][i] == value)
                ret = false;
        }

        return ret;
    }

    public boolean isValidModel(int[][] model) {

        boolean ret = true;

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(model[i][j] == 0)
                {
                    ret = false;
                }

        return ret;
    }

    public boolean isWrongValue(int[][] model) {

        boolean ret = true;

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(model[i][j] > 9 || model[i][j] < 0)
                {
                    ret = false;
                }
        return  ret;
    }

    public void writeInsideNotEmptyCell(int[][] matrix, int rowForValue, int colForValue, int value) {
        matrix[rowForValue][colForValue] = value;
    }
}
