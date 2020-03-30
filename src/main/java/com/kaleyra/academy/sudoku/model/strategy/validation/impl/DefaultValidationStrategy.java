package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

public class DefaultValidationStrategy implements SudokuValidationStrategy {


    @Override
    public boolean isValidMove(int[][] model, int row, int col, int value) {
        if (!isValidForQuadrant(model, row, col, value) || !isValidForRow(model, row, col, value) || !isValidForColumn(model, row, col, value)) {
            return false;
        } else {
            return true;
        }
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

        for (int i = 0; i < 9; i++) {
            if (!checkRow(getRow(model, i)) || !checkRow(getCol(model, i))) {
                isValid = false;
                break;
            }
        }

        if (!getBoxAndCheckBox(model)) {
            isValid = false;
        }

        return isValid;
    }

    private int[] getRow (int[][] matrix, int n) {
        int[] row = matrix[n];
        return row;
    }

    private int[] getCol (int[][] matrix, int n) {
        int[] col = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][n];
        }
        return col;
    }

    private boolean checkRow (int[] row) {
        boolean isValid = true;

        //INITIALIZE COUNTERS
        int one = 0; int two = 0; int three = 0; int four = 0; int five = 0;
        int six = 0; int seven = 0; int eight = 0; int nine = 0;
        //CHECK ROWS

        for (int j = 0; j < 9; j++) {
            switch (row[j]) {
                case 1: one++; break;
                case 2: two++; break;
                case 3: three++; break;
                case 4: four++; break;
                case 5: five++; break;
                case 6: six++; break;
                case 7: seven++; break;
                case 8: eight++; break;
                case 9: nine++; break;
            }
        }

        //CHECK IF ROW IS OK
        if (one != 1 || two != 1 || three != 1 || four != 1 || five != 1 || six != 1 || seven != 1 || eight != 1 || nine != 1) {
            isValid = false;
        }
        return isValid;
    }

    protected boolean getBoxAndCheckBox (int[][] matrix) {

        boolean isValid = true;

        for (int y = 0; y < 7; y += 3) {
            for (int x = 0; x < 7; x += 3) {
                int[][] subMatrix = new int[3][3];
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        subMatrix[k][l] = matrix[y + k][x + l];
                    }
                }
                if (!checkBox(subMatrix)) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public boolean checkBox (int[][] subMatrix) {
        boolean isValid = true;

        //INITIALIZE COUNTERS
        int one = 0; int two = 0; int three = 0; int four = 0; int five = 0;
        int six = 0; int seven = 0; int eight = 0; int nine = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (subMatrix[i][j]) {
                    case 1: one++; break;
                    case 2: two++; break;
                    case 3: three++; break;
                    case 4: four++; break;
                    case 5: five++; break;
                    case 6: six++; break;
                    case 7: seven++; break;
                    case 8: eight++; break;
                    case 9: nine++; break;
                }
            }
        }

        //CHECK IF BOX IS OK
        if (one != 1 || two != 1 || three != 1 || four != 1 || five != 1 || six != 1 || seven != 1 || eight != 1 || nine != 1) {
            isValid = false;
        }
        return isValid;
    }
}
