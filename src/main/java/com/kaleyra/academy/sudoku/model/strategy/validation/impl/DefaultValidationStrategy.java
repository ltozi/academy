package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

import java.util.Arrays;

public class DefaultValidationStrategy implements SudokuValidationStrategy {


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
    public boolean isValidForQuadrant(int[][] matrix, Integer rowForValue, Integer colForValue, Integer value) {
        if (doPrevalidation(value)) return true;

        /*
            A square is 3*3 so we can identify a quadrant dividing by 3 calculating its
            starting point from the top
         */
        int topLeftRow = (rowForValue - rowForValue % 3);
        int topLeftCol = (colForValue - colForValue % 3);

        for (int row = topLeftRow; row < topLeftRow+3; row++) {
            int[] bytes = matrix[row];
            for (int col = topLeftCol; col < topLeftCol+3; col++) {
                Integer existingValue = matrix[row][col];

                if(row == rowForValue && col == colForValue)
                    continue;

                if(existingValue.equals(value))
                    return false;
            }
        }

        return true;
    }

    private boolean doPrevalidation(Integer value) {
        if (value == null || value.equals(0)) return true;

        if(!ALLOWED_VALUES.contains(value))
            throw new IllegalArgumentException("Value " + value + " is not allowed. Valid values are: " + Arrays.toString(ALLOWED_VALUES.toArray()));
        return false;
    }

    public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
        if (doPrevalidation(value)) return true;

        for (int col = 0; col < matrix[rowForValue].length; col++) {
            Integer existingCellValue = matrix[rowForValue][col];
            if(col == colForValue)
                continue; //Skip

            if(existingCellValue.equals(value)) //No match should be found
               return false;
        }
        return true;
    }



    public boolean isValidForColumn(int[][] matrix, Integer rowForValue, Integer colForValue, Integer value) {
        if (doPrevalidation(value)) return true;

        for (int row = 0; row < matrix.length; row++) {
            Integer existingCellValue = matrix[row][colForValue];
            if(existingCellValue == null || row == rowForValue)
                continue; //Skip

            if(existingCellValue.equals(value))
                return false;
        }
        return true;
    }

    public boolean isValidModel(int[][] model) {
        Integer expectedSumOnEachRow = EXPECTED_SUM_ON_EACH_ROW;

        for (int row = 0; row < model.length; row++) {
            int[] columns = model[row];
            for (int col = 0; col < columns.length; col++) {
                Integer aValue = model[row][col];

                if( ! isValidMove(model, row, col, aValue) ) {
                    return false;
                }

                expectedSumOnEachRow = (expectedSumOnEachRow - aValue);
            }
            if(expectedSumOnEachRow != 0)
                return false;

            expectedSumOnEachRow = EXPECTED_SUM_ON_EACH_ROW;
        }
        return true;
    }
}
