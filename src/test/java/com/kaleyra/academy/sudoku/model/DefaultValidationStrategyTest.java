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
        //ARRANGE
        b[0][0] = 1; b[0][1] = 4;  b[0][2] = 3; b[0][4] = 5; b[0][5] = 6;  b[0][6] = 7; b[0][7] = 8; b[0][8] = 9;
        //ACT

        //ASSERT
        assertTrue(validationStrategy.isValidForRow(b, 0, 3, 2));
    }

    @Test
    public void shoudlLetPlaceZeroValueAsNoValue() {
        int[][] b = new int[9][9];
        //ARRANGE
        b[0][0] = 1; b[0][1] = 4;  b[0][2] = 3; b[0][4] = 5; b[0][5] = 6;  b[0][6] = 7; b[0][7] = 8; b[0][8] = 9;
        //ACT

        //ASSERT
        assertTrue(validationStrategy.isValidForRow(b, 0, 3, 0));
    }

    @Test
    public void shouldGiveErrorBecauseValueAlreadyInsideRow() {
        int[][] b = new int[9][9];
        //ARRANGE
        b[0][0] = 1; b[0][1] = 4;  b[0][2] = 3; b[0][4] = 5; b[0][5] = 6;  b[0][6] = 7; b[0][7] = 8; b[0][8] = 9;
        //ACT

        //ASSERT
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
    public void shoudGiveError() {
        int[][] b = new int[9][9];
        b[0][0] = 1; b[0][1] = 2;  b[0][2] = 3;
        b[1][0] = 4; b[1][1] = 5;  b[1][2] = 6;
        b[2][0] = 7; b[2][1] = 8;  b[2][2] = 9;

        Integer row = 2;
        Integer col = 0;
        Integer value = 9;

        assertTrue(validationStrategy.isValidForColumn(b, row, col, value));
    }


    @Test
    public void shouldBeValidForQuadrant() {
        int[][] b = new int[9][9];

        Integer row = 1;
        Integer col = 5;
        Integer value = 6;  //still not present in quadrant and should be valid
        //                                      | quadrant is here                    |
        b[0][0] = 3; b[0][1] = 4;  b[0][2] = 8; b[0][3] = 5; b[0][4] = 2;  b[0][5] = 7; b[0][6] = 1; b[0][7] = 6;  b[0][8] = 9;
        b[1][0] = 9; b[1][1] = 6;  b[1][2] = 2; b[1][3] = 1; b[1][4] = 9;
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
    public void shouldGenerateSubMatrixAndCheckIt () {
        int[][] b = generateFullValidModel();
        boolean isValid = true;

        for (int y = 0; y < 7; y += 3) {
            for (int x = 0; x < 7; x += 3) {
                int[][] subMatrix = new int[3][3];
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        subMatrix[k][l] = b[y + k][x + l];
                    }
                }
                for (int k = 0; k < subMatrix.length; k++) {
                    for (int z = 0; z < subMatrix.length; z++) {
                        System.out.print(subMatrix[k][z]);
                    }
                    if (!checkBox(subMatrix)) {
                        isValid = false;
                    }
                    System.out.println();
                }
                System.out.println("---");
                System.out.println(isValid);
                System.out.println("---");
            }
        }


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