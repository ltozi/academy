package com.kaleyra.academy.sudoku.model.strategy.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface SudokuValidationStrategy {

    Integer EXPECTED_SUM_ON_EACH_ROW = 45; //1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9

    Set<Integer> ALLOWED_VALUES = new HashSet<>(Arrays.asList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    /**
     * Validate a single move
     *
     * @param model
     * @param row
     * @param col
     * @param value
     * @return
     */
    boolean isValidMove(int[][] model, int row, int col, int value);


    /**
     * Check if value is valid inside the current quadrant
     *
     * we have to check every number inside
     *
     * 1 2 3
     * 4 ? 6 <--- e.g. 5 must be the only one allowed
     * 7 8 9
     *
     * @param model
     * @param value
     * @return
     */
    boolean isValidForQuadrant(int[][] model, int row, int col, int value);

    /**
     * Check if value is valid for the current row
     *
     * ---1-2-?-9
     * --------
     * ...
     * ..
     *
     * @param model
     * @param value
     * @return
     */
    boolean isValidForRow(int[][] model, int row, int col, int value);

    /**
     * Check if value is valid for the current column
     *
     * ---1-----
     * ---?-----
     * ---3-----
     * ...
     * ..
     *
     * @param model
     * @param value
     * @return
     */
    boolean isValidForColumn(int[][] model, int row, int col, int value);


    /**
     * A model is valid when:
     *
     * - the sum of each number in a row (or column) is 45
     * - each number in a cell is a a valid move
     *
     * @param model
     * @return
     */
    boolean isValidModel(int[][] model);

}
