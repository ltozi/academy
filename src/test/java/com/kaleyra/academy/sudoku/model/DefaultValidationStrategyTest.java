package com.kaleyra.academy.sudoku.model;

import com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultValidationStrategyTest {

    private DefaultValidationStrategy validationStrategy;

    @Before
    public void setup() {
        validationStrategy = new DefaultValidationStrategy();
    }

    @Test
    public void shouldBeValidRow() {
        int[][] b = new int[9][9];

        assertTrue(validationStrategy.isValidForRow(b, 0, 3, 2));
    }

    @Test
    public void shoudlLetPlaceZeroValueAsNoValue() {
        int[][] b = new int[9][9];
        assertTrue(validationStrategy.isValidForRow(b, 0, 3, 0));
    }

    @Test
    public void shouldGiveErrorBecauseValueAlreadyInsideRow() {
        int[][] b = new int[9][9];
        b[0] = new int[]{0, 0, 0, 0, 0, 0, 9, 0, 0};
        assertFalse(validationStrategy.isValidForRow(b, 0,  3, 9));
    }


    @Test
    public void shouldOverwriteExistingValue() {
        int[][] b = new int[9][9];

        Integer row = 3;
        Integer col = 0;
        Integer value = 9;

        b[row][col] = 9;

        assertTrue(validationStrategy.isValidForColumn(b, row, col, value));
    }

    @Test
    //public void shoudGiveError() {
    public void shouldValidateFalse() {
        int[][] b = new int[9][9];
        b[0][0] = 9; b[0][1] = 2;  b[0][2] = 3;
        b[1][0] = 4; b[1][1] = 5;  b[1][2] = 6;
        b[2][0] = 7; b[2][1] = 8;  b[2][2] = 1;

        Integer row = 2;
        Integer col = 0;
        Integer value = 9;

        assertFalse(validationStrategy.isValidForColumn(b, row, col, value));
    }


    @Test
    public void shouldBeValidForQuadrant() {
        int[][] b = new int[9][9];

        Integer row = 1;
        Integer col = 4;
        Integer value = 4;  //still not present in quadrant and should be valid
        //                                      | quadrant is here                    |
        b[0][0] = 3; b[0][1] = 4;  b[0][2] = 8; b[0][3] = 5; b[0][4] = 2;  b[0][5] = 7; b[0][6] = 1; b[0][7] = 6;  b[0][8] = 9;
        b[1][0] = 9; b[1][1] = 6;  b[1][2] = 2; b[1][3] = 1; b[1][4] = 4;
        //                                      |_____________________________________|

        assertTrue(validationStrategy.isValidForQuadrant(b, row, col, value));
    }


    @Test
    public void shouldGiveErrorWhenValueIsAlreadInsideQuadrant() {
        int[][] b = new int[9][9];
        b[0][0] = 1; b[0][1] = 2;  b[0][2] = 3;
        b[1][0] = 4; b[1][1] = 0;  b[1][2] = 6;
        b[2][0] = 7; b[2][1] = 8;  b[2][2] = 9;

        Integer row = 1;
        Integer col = 1;
        Integer value = 9;  //Already exist in quadrant

        assertFalse(validationStrategy.isValidForQuadrant(b, row, col, value));
    }


    @Test
    public void shouldBeValidModel() {
        int[][] b = generateFullValidModel();

        assertTrue(validationStrategy.isValidModel(b));
    }

    private int[][] generateFullValidModel() {
        int[][] model = new int[9][9];
        model[0][0] = 3; model[0][1] = 4;  model[0][2] = 8; model[0][3] = 5; model[0][4] = 2;  model[0][5] = 7; model[0][6] = 1; model[0][7] = 6;  model[0][8] = 9;
        model[1][0] = 9; model[1][1] = 6;  model[1][2] = 2; model[1][3] = 1; model[1][4] = 4;  model[1][5] = 8; model[1][6] = 7; model[1][7] = 5;  model[1][8] = 3;
        model[2][0] = 5; model[2][1] = 1;  model[2][2] = 7; model[2][3] = 9; model[2][4] = 6;  model[2][5] = 3; model[2][6] = 2; model[2][7] = 4;  model[2][8] = 8;

        model[3][0] = 6; model[3][1] = 7;  model[3][2] = 9; model[3][3] = 3; model[3][4] = 5;  model[3][5] = 4; model[3][6] = 8; model[3][7] = 2;  model[3][8] = 1;
        model[4][0] = 2; model[4][1] = 8;  model[4][2] = 4; model[4][3] = 7; model[4][4] = 1;  model[4][5] = 6; model[4][6] = 3; model[4][7] = 9;  model[4][8] = 5;
        model[5][0] = 1; model[5][1] = 3;  model[5][2] = 5; model[5][3] = 8; model[5][4] = 9;  model[5][5] = 2; model[5][6] = 6; model[5][7] = 7;  model[5][8] = 4;

        model[6][0] = 4; model[6][1] = 9;  model[6][2] = 6; model[6][3] = 2; model[6][4] = 3;  model[6][5] = 1; model[6][6] = 5; model[6][7] = 8;  model[6][8] = 7;
        model[7][0] = 7; model[7][1] = 5;  model[7][2] = 3; model[7][3] = 6; model[7][4] = 8;  model[7][5] = 9; model[7][6] = 4; model[7][7] = 1;  model[7][8] = 2;
        model[8][0] = 8; model[8][1] = 2;  model[8][2] = 1; model[8][3] = 4; model[8][4] = 7;  model[8][5] = 5; model[8][6] = 9; model[8][7] = 3;  model[8][8] = 6;

        return model;
    }


    @Test
    public void shouldGiveErrorBecauseAWrongValueExists() {

        int[][] bytes = generateFullValidModel();

        bytes[1][1] = 9; //Dirty value

        assertFalse(validationStrategy.isValidModel(bytes));
    }

    @Test
    public void hasUniqueElementsArrTestTrue(){
        int[] arr = new int[]{1,2,0,4,6,0,8,9};
        assertTrue(validationStrategy.hasUniqueElementsArr(arr,0));
    }
    @Test
    public void hasUniqueElementsArrTestFalse(){
        int[] arr = new int[]{1,2,0,4,2,6,0,8,9};
        assertFalse(validationStrategy.hasUniqueElementsArr(arr,0));
    }

    //hasUniqueElementsMatrix
    @Test
    public void hasUniqueElementsMatrixTestTrue(){
        int[][] matrix = new int[][]{{1,0,3},{4,5,0},{7,8,9}};
        assertTrue(validationStrategy.hasUniqueElementsMatrix(matrix,0));
    }
    @Test
    public void hasUniqueElementsMatrixTestFalse(){
        int[][] matrix = new int[][]{{1,0,3},{4,5,0},{7,3,9}};
        assertFalse(validationStrategy.hasUniqueElementsMatrix(matrix,0));
    }

    //getQuadrantMatrix
    @Test
    public void getQuadrantMatrixTest(){
        int[][] matrix ={{0,0,8,0,0,7,1,6,9},
                         {9,6,0,0,0,8,7,0,0},
                         {5,1,7,0,6,0,2,0,0},
                         {0,0,0,3,5,0,0,0,0},
                         {0,0,4,0,1,0,3,0,0},
                         {0,0,0,0,9,2,0,0,0},
                         {0,0,6,0,3,0,5,8,7},
                         {0,0,3,6,0,0,0,1,2},
                         {8,2,1,4,0,0,9,0,0}};

        int[][] matrix00 = new int[][]{{0,0,8},
                                        {9,6,0},
                                        {5,1,7}};
        int[][] matrix12 = new int[][]{{0,0,0},
                                        {3,0,0},
                                        {0,0,0}};
        assertArrayEquals(matrix00, validationStrategy.getQuadrantMatrix(matrix,0,0));
        assertArrayEquals(matrix12, validationStrategy.getQuadrantMatrix(matrix,1,2));
    }

    @Test
    public void getRowTest(){
        int[][] matrix = new int[][]{{1,2,3,45},{4,5,6,21},{7,8,9,39}};
        int[] row = new int[]{4,5,6,21};
        assertArrayEquals(row,validationStrategy.getRow(matrix,1));
    }
    @Test
    public void getColumnTest(){
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9},{42,5,6}};
        int[] row = new int[]{1,4,7,42};
        assertArrayEquals(row,validationStrategy.getColumn(matrix,0));
    }

    @Test
    public void sumArrayTest(){
        int[] arr = new int[]{1,2,3};
        assertEquals(6,validationStrategy.sum(arr));
    }
    @Test
    public void sumArrayMatrix(){
        int[][] matrix = new int[][]{{12,2,3},{4,5,6},{7,8,9}};
        assertEquals(56,validationStrategy.sum(matrix));
    }

}