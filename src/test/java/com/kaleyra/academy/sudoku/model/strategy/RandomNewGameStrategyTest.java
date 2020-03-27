package com.kaleyra.academy.sudoku.model.strategy;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.SimpleGenerationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class RandomNewGameStrategyTest {

    @Test(timeout = 2000)
    public void shouldGenerateRandomPuzzleOK() throws SudokuException {

        RandomNewGameStrategy strategy = new SimpleGenerationStrategy();
        GameModel model = strategy.createModel();

        int countAllEmpty = isModelEmpty(model);
        System.out.println(model.toString());


        assertNotEquals("Random generation not working because all values results empty (0 values)",
                GameModel.ROWS * GameModel.COLS, countAllEmpty);
    }

    private int isModelEmpty(GameModel model) {
        int[][] data = model.getData();
        int countAllEmpty = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] == 0)
                    countAllEmpty++;
            }
        }
        return countAllEmpty;
    }


}