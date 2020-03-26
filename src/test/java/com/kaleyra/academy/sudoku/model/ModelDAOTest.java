package com.kaleyra.academy.sudoku.model;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ModelDAOTest {

    @Test
    public void shouldPrintFile() throws IOException {
        String fileName = "C:\\Users\\Commodaro\\Desktop\\academy\\src\\main\\resources\\game1.sudoku";

        FileReader reader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(reader);

        Scanner sc = new Scanner(new File(fileName));



        sc.next();
        sc.next();
        String str = " ";
        while(sc.hasNextLine())
        {
            str = sc.next();

            String a[] = str.split(";", 4);
            int row = Integer.parseInt(a[1]); int column = Integer.parseInt(a[2]); int value = Integer.parseInt(a[3]);

            System.out.print(row);
            System.out.print(column);
            System.out.println(value);

        }

       // System.out.println(a[0]);
       // System.out.println(a[1]);
       // System.out.println(a[2]);
       // System.out.println(a[3]);

        //System.out.println(row);
        //System.out.println(column);
        //System.out.println(value);


        int[][] predefinedCells =
                new int[GameModel.ROWS][GameModel.COLS];
    }
}
