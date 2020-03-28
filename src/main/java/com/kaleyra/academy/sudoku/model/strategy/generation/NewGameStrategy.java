/**
 * NewGameStrategy
 */
package com.kaleyra.academy.sudoku.model.strategy.generation;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.UnknownDifficultyLevelException;
import com.kaleyra.academy.sudoku.utils.SudokuException;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Interfaccia degli oggetti che si occupano di creare
 * nuovi gioci.
 *
 * @pattern Strategy
 */
public interface NewGameStrategy {

    /**
     * livello di gioco facile
     */
    int LEVEL_EASY = 3;
    //20% chance of making a cell 0 (2 values of 10). The only integer values from 0 to 9 that make a cell 0
    HashSet<Integer> LEVEL_EASY_hs = new HashSet<>(Arrays.asList(8,9));

    /**
     * livello di gioco medio
     */
    int LEVEL_MEDIUM = 2;
    //50% chance of making a cell 0 (5 values of 10). The only integer values from 0 to 9 that make a cell 0
    HashSet<Integer> LEVEL_MEDIUM_hs = new HashSet<>(Arrays.asList(5,6,7,8,9));

    /**
     * livello di gioco difficile
     */
    int LEVEL_HARD = 1;
    //90% chance of making a cell 0 (9 values of 10). The only integer values from 0 to 9 that make a cell 0
    HashSet<Integer> LEVEL_HARD_hs = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    /**
     * @return crea un nuovo modello
     * @throws SudokuException
     */
    GameModel createModel() throws SudokuException, UnknownDifficultyLevelException;

}
