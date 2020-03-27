package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

import java.sql.PreparedStatement;
import java.util.Arrays;

public class DefaultValidationStrategy implements SudokuValidationStrategy {


    @Override
    public boolean isValidMove(int[][] model, int row, int col, int value) {
        return  isValidForRow(model,row,col,value) && isValidForColumn(model,row,col,value) && isValidForQuadrant(model,row,col,value);
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
        //Table[Quotient[i,3],{i,0,8}]
        //i-3Quotient[i,3]

        int[][] quadrant = new int[3][3];

        int quadrantOriginRow = rowForValue/3;
        int quadrantOriginColumn = colForValue/3;

        int rowForValueRelative  = rowForValue - 3*(rowForValue/3);
        int colForValueRelative  = colForValue - 3*(colForValue/3);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                quadrant[i][j] = matrix[3*quadrantOriginRow + i][3*quadrantOriginColumn + j];


        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(quadrant[i][j]+"  ");
            System.out.println();
        }*/

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (i == rowForValueRelative && j == colForValueRelative)
                    continue;
                if (quadrant[i][j] == value)
                    return false;
            }
        return true;
    }


    public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
        //TODO
        int[] row = matrix[rowForValue];
        // It is possible to overwrite a cell with a rules compliant-value.
        // if ( row[colForValue] != 0 ) // 0 stands for empty cell
         //   return false;
        if (value == 0)
            return true;

        for (int i=0; i<row.length; i++) {
            if (i == colForValue)
                continue;
            if (row[i] == value)
                return false;
        }
        return true;
        //return !Arrays.asList(row).contains(value);
    }



    public boolean isValidForColumn(int[][] matrix, int rowForValue, int colForValue, int value) {
        int[][] matrixTranspose = new int[GameModel.ROWS][GameModel.COLS];
        for (int i=0; i< GameModel.ROWS; i++)
            for (int j=0; j < GameModel.COLS; j++)
                matrixTranspose[i][j] = matrix[j][i];
        //TODO
        return isValidForRow(matrixTranspose, colForValue,rowForValue, value);
    }

    public boolean hasUniqueElementsArr(int[] arr, int nullElement){
        int m,n;
        for (m = 0; m < arr.length; m++) {
            if (arr[m] == nullElement ) continue;
            for (n = m + 1; n < arr.length; n++) {
                if (arr[n] == nullElement ) continue;
                if ( arr[m]  == arr[n])
                    return false;
            }
        }
        return true;
    }

    public boolean hasUniqueElementsMatrix(int[][] matrix, int nullElement){
        int n = matrix.length;
        int m = matrix[0].length;

        int[] arr = new int[m*n];
        for(int i=0; i < n; i++)
            for(int j=0; j < m; j++)
                arr[j + i*m] = matrix[i][j];

        return hasUniqueElementsArr(arr, nullElement);
    }

    public int[][] getQuadrantMatrix(int[][] matrix, int quadrantI, int quadrantJ){
        int[][] quadrantMatrix = new int[3][3];
        for(int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                quadrantMatrix[i][j] = matrix[3*quadrantI + i][3*quadrantJ + j];
        return quadrantMatrix;
    }

    public int[] getRow(int[][] matrix, int i){

        return matrix[i];
    }

    public int[] getColumn(int[][] matrix, int j){
        int[] column  = new int[matrix.length];
        for (int i=0; i< column.length; i++)
            column[i] = matrix[i][j];
        return column;

    }

    public int sum(int[] arr){
        int sum = 0;
        for(int i = 0; i<arr.length; i++)
            sum+=arr[i];
        return sum;
    }
    public int sum(int[][] matrix){
        int sum = 0;
        for(int i = 0; i<matrix.length; i++)
            sum+=sum(matrix[i]);
        return sum;
    }

    public boolean isValidModel(int[][] model) {
        //TODO
        int i,j;
        int[] row;
        int[] col;
        int[][] quadrantMatrix;

        //check rows
        for (i=0; i< model.length; i++){
            row = getRow(model,i);
            if (!hasUniqueElementsArr(row,0) || sum(row)!= 45 )
                return false;
        }
        for (j=0; j< model[0].length; j++){
            col = getColumn(model,j);
            if (!hasUniqueElementsArr(col,0)  || sum(col)!= 45)
                return false;
        }

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                quadrantMatrix = getQuadrantMatrix(model, i, j);
                if (!hasUniqueElementsMatrix(quadrantMatrix,0) || sum(quadrantMatrix)!= 45 )
                    return false;
            }
        }

       return true;
    }



}
