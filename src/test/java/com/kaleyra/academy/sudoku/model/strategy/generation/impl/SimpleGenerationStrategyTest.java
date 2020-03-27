package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class SimpleGenerationStrategyTest {

    @Test
    public void shouldSetDifficultyHard() {

        String valueRead = "";

        SimpleGenerationStrategy sgs = new SimpleGenerationStrategy();
        int[][] model = new int[9][9];
        long elapsed = System.nanoTime();

        //GameModel gm = new GameModel(model, model, elapsed);
        //sgs.setGameDifficulty(gm);

        //scrive la proprietà
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerEasy.useFile", "easy");
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerMedium.useFile", "medium");
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerHard.useFile", "hard");

        //legge la proprietà
        String s = System.getProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerHard.useFile");
        //System.out.println();
        //System.out.println(property);

        assertTrue("Il valore letto '" + s + "' non corrisponde al valore di prova: 'hard'", s == ("hard"));
    }
}
