package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

import java.util.Arrays;
import java.util.HashSet;

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

    //quadrantI and quadrantJ are indices from 0 to 2 for the 9 quadrants of a sudoku table
    public int[][] getQuadrantMatrix(int[][] matrix, int quadrantI, int quadrantJ){
        int[][] quadrantMatrix = new int[3][3];
        for(int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                quadrantMatrix[i][j] = matrix[3*quadrantI + i][3*quadrantJ + j];
        return quadrantMatrix;
    }

    //get the quadrant matrix containing the element (i,j) of a sudoku table
    public int[][] getQuadrantMatrixOfElement(int[][] matrix, int i, int j){
        int quadrantI = i/3;
        int quadrantJ = j/3;
        return getQuadrantMatrix(matrix, quadrantI, quadrantJ);
    }

    //Converts an int[] matrix into a HashSet<Integer>
    public HashSet<Integer> toIntegerHashSet(int[][] matrix){
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i< matrix.length; i++)
            for (int x: matrix[i])
                set.add(x);
         return set;
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

    //returns the submatrix of nofRows x nofColumns where elements (0,0) correspond to element (i,j) of 'matrix'
    public int[][] getSubMatrix(int[][] matrix, int i, int j, int nofRows, int nofColumns){

        int[][] subMatrix = new int[nofRows][nofColumns];

        for(int m = 0; m < nofRows; m++)
            for (int n = 0; n < nofColumns; n++)
                subMatrix[m][n] = matrix[i+m][j+n];

        return subMatrix;
    }


    //returns the elements in row i or column j of matrix 'matrix'
    public HashSet<Integer> getElementsInOrAndColumn(int[][] matrix, int i, int j){

        HashSet<Integer> set = new HashSet<>();
        Integer row[] = Arrays.stream(getRow(matrix, i)).boxed().toArray( Integer[]::new);
        Integer column[] = Arrays.stream(getColumn(matrix, j)).boxed().toArray( Integer[]::new);

        set.addAll(Arrays.asList(row));
        set.addAll(Arrays.asList(column));
        return  set;
    }

    //returns a HashSet<Integer> containing the elements in the quadrant of a sudoku table containing element (i,j)
    public HashSet<Integer> getElementsInQuadrantOfElement(int[][] matrix, int i, int j){
        return toIntegerHashSet(getQuadrantMatrixOfElement(matrix, i, j));
    }

    //AQUI
    //given two sets A and B, returns the complement A/B, i.e. all elements belonging to A but not to B.
//    public HashSet<Integer> Complement (HashSet<Integer> setA, HashSet<Integer> setB){
//
//
//    }

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


    public HashSet<Integer> getElementsInRowOrColumnExclusive(int[][] matrix, int i, int j) {
        HashSet<Integer> set = getElementsInOrAndColumn(matrix, i, j);
        set.remove(matrix[i][j]);
        return set;
    }


    // returns all elements that belong to A but do not belong to B
    // relative complement https://en.wikipedia.org/wiki/Complement_(set_theory)
    public HashSet<Integer> getRelativeComplement(HashSet<Integer> A, HashSet<Integer> B) {
        HashSet<Integer> Acopy = new HashSet<>();
        Acopy.addAll(A);
        Acopy.removeAll(B);
        return Acopy;
    }


    public HashSet<Integer> getElementsInQuadrantOfElementExclusive(int[][] matrix, int i, int j) {
        HashSet<Integer> set = getElementsInQuadrantOfElement(matrix, i, j);
        set.remove(matrix[i][j]);
        return set;
    }

    public HashSet<Integer> getUnion(HashSet<Integer> A, HashSet<Integer> B) {
        HashSet<Integer> union = new HashSet<>();
        union.addAll(A);
        union.addAll(B);
        return union;
    }

    public int[] linerToMatrixIndices(int linearIndex, int matrixNofColumns) {
        int[] matrixIndices = new int[]{linearIndex/matrixNofColumns, linearIndex - matrixNofColumns*(linearIndex/matrixNofColumns)};
        return matrixIndices;

    }

    public int matrixToLinearIndex(int i, int j, int nofColumns) {
        return j+nofColumns*i;
    }
}

//