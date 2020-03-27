package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;

import java.util.Random;

public class SimpleGenerationStrategy extends RandomNewGameStrategy {

    @Override
    public GameModel setGameDifficulty(GameModel model) {

        Random random = new Random();
        int[][] data = model.getData();

        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            for (int colIndex = 0; colIndex < data.length; colIndex++) {
                int emptyValue = random.nextInt(LEVEL_MEDIUM);
                if(emptyValue == 0) {
                    data[rowIndex][colIndex] = 0; //remove from generated
                }
            }
        }

        return model;
    }




}
