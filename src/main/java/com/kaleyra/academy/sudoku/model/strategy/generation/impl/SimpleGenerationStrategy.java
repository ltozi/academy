package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;
import com.kaleyra.academy.sudoku.utils.Preferences;

import java.util.HashSet;
import java.util.Random;

public class SimpleGenerationStrategy extends RandomNewGameStrategy {

    @Override
    public GameModel setGameDifficulty(GameModel model) throws UnknownDifficultyLevelException {

        Random random = new Random();
        int[][] data = model.getData();

        Preferences preferences = Preferences.getInstance();
//preferences.getProperty(Preferences.DIFFICULTY_LEVEL) // gives a string

        HashSet<Integer> difficultyLevelHS = new HashSet<>();
        switch (preferences.getProperty(Preferences.DIFFICULTY_LEVEL)){
            case "EASY":
                difficultyLevelHS = LEVEL_EASY_hs;
                break;
            case "MEDIUM":
                difficultyLevelHS = LEVEL_MEDIUM_hs;
                break;
            case "HARD":
                difficultyLevelHS = LEVEL_HARD_hs;
                break;
            default:
                throw new UnknownDifficultyLevelException("Property 'difficulty.level' in configuration file should be" +
                        " 'EASY', 'MEDIUM' or 'HARD'.");
        }

        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            for (int colIndex = 0; colIndex < data.length; colIndex++) {
                int emptyValue = random.nextInt(10); //generates random integers from 0 to 9
                if(difficultyLevelHS.contains(emptyValue)) {
                    data[rowIndex][colIndex] = 0; //remove from generated
                }
            }
        }


        return model;
    }




}
