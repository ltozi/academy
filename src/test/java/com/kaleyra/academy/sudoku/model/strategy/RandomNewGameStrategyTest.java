package com.kaleyra.academy.sudoku.model.strategy;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RecursiveStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.SimpleGenerationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import org.junit.Test;

public class RandomNewGameStrategyTest {

    @Test
    public void shouldGenerateRandomPuzzleOK() throws SudokuException {

        RandomNewGameStrategy strategy = new SimpleGenerationStrategy();
        GameModel model = strategy.createModel();

        System.out.println(model.toString());
    }


}