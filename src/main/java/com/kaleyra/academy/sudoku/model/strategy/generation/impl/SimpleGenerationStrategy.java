package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;

import java.util.Random;

public class SimpleGenerationStrategy extends RandomNewGameStrategy {

    @Override
    public GameModel setGameDifficulty(GameModel model) {

        String property = " ";
        Random random = new Random();
        int[][] data = model.getData();
        int DIFFICULTY_LEVEL = 0;

        //scrive la proprietà
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerEasy.useFile", "easy");
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerMedium.useFile", "medium");
        System.setProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerHard.useFile", "hard");

        //legge la proprietà
        property = System.getProperty("com.kaleyra.academy.sudoku.model.ChangeGameDifficultyManagerHard.useFile");

        switch (property) {
            case "hard":
                DIFFICULTY_LEVEL = LEVEL_DIFFICULT;
                break;
            case "medium":
                DIFFICULTY_LEVEL = LEVEL_MEDIUM;
                break;
            case "easy":
                DIFFICULTY_LEVEL = LEVEL_EASY;
        }

        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            for (int colIndex = 0; colIndex < data.length; colIndex++) {
                int emptyValue = random.nextInt(DIFFICULTY_LEVEL);
                if(emptyValue == 0) {
                    data[rowIndex][colIndex] = 0; //remove from generated
                }
            }
        }

        return model;
    }
}
