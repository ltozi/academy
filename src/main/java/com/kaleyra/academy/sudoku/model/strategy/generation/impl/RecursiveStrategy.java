package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;

public class RecursiveStrategy extends RandomNewGameStrategy {

    private SudokuValidationStrategy validationStrategy = new DefaultValidationStrategy();

    @Override
    public GameModel createModel() throws SudokuException {

        GameModel gameModel = new GameModel();

        boolean ok = generateValidMatrix(gameModel.getData());

        if( ! ok )
            throw new SudokuException("Model is not generated correctly for some reason");

        return gameModel;

    }

    /**
     * TODO...
     *
     * @param model
     * @return
     */
    @Override
    public GameModel setGameDifficulty(GameModel model) {


        return model;
    }

    private boolean generateValidMatrix(int[][] matrix) {

        if(validationStrategy.isValidModel(matrix))
            return true;

        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] bytes = matrix[i];
            for (int j = 0; j < bytes.length; j++) {
                Integer aByte = bytes[j];

                if(matrix[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }

            }
        }


        // else for each-row backtrack
        for (int num = 1; num <= 9; num++)
        {
            if (validationStrategy.isValidMove(matrix, row, col, num))
            {
                matrix[row][col] = num;
                if (generateValidMatrix(matrix))
                    return true;
                else
                    matrix[row][col] = 0; // replace it
            }
        }

        return false;
    }


}
