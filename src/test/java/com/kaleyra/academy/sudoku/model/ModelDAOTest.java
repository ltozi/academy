package com.kaleyra.academy.sudoku.model;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ModelDAOTest {

    @Test
    public void loadGameFromStringArrayCorrectly() {
        //loadGameFromFile
        String[] exampleStringArrayInput = {"SODOKU-1","0","P;0;2;8","P;0;5;7","P;0;6;1","P;0;7;6","P;0;8;9","P;1;0;9","P;1;1;6",
                            "P;1;5;8","P;1;6;7","P;2;0;5","P;2;1;1","P;2;2;7","P;2;4;6","P;2;6;2","P;3;3;3","P;3;4;5",
                            "P;4;2;4","P;4;4;1","P;4;6;3","P;5;4;9","P;5;5;2","P;6;2;6","P;6;4;3","P;6;6;5","P;6;7;8","P;6;8;7",
                            "P;7;2;3","P;7;3;6","P;7;7;1","P;7;8;2","P;8;0;8","P;8;1;2","P;8;2;1","P;8;3;4","P;8;6;9"};

        int[][] targetTable ={{0,0,8,0,0,7,1,6,9},
                              {9,6,0,0,0,8,7,0,0},
                              {5,1,7,0,6,0,2,0,0},
                              {0,0,0,3,5,0,0,0,0},
                              {0,0,4,0,1,0,3,0,0},
                              {0,0,0,0,9,2,0,0,0},
                              {0,0,6,0,3,0,5,8,7},
                              {0,0,3,6,0,0,0,1,2},
                              {8,2,1,4,0,0,9,0,0}};

        ModelDAO modelDAO =  ModelDAO.getInstance();

        int[][] table = new int[GameModel.ROWS][GameModel.COLS]; //initialized to 0 by default

        table = modelDAO.loadGameFromStringArray(exampleStringArrayInput);
        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(table[i][j]+ "  ");
            System.out.println();
        }*/
        assertArrayEquals(targetTable,table);

    }


}
