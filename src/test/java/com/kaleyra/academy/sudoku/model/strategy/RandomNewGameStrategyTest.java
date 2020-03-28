package com.kaleyra.academy.sudoku.model.strategy;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.SimpleGenerationStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.UnknownDifficultyLevelException;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import org.junit.Test;

public class RandomNewGameStrategyTest {

    @Test(timeout = 5000)
    public void shouldGenerateRandomPuzzleOK() throws SudokuException, UnknownDifficultyLevelException {

        RandomNewGameStrategy strategy = new SimpleGenerationStrategy();
        GameModel model = strategy.createModel();

        System.out.println();
        System.out.println(model.toString());
    }

    @Test(timeout = 1000)
    public void printSudokuFullTableTest(){
        RandomNewGameStrategy strategy = new SimpleGenerationStrategy();

        strategy.printSudokuFullTable2();
    }

}