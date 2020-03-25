/**
 * NewGameStrategy
 */
package com.kaleyra.academy.sudoku.model.strategy.generation;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.utils.SudokuException;

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

    /**
     * livello di gioco medio
     */
    int LEVEL_MEDIUM = 2;

    /**
     * livello di gioco difficile
     */
    int LEVEL_DIFFICULT = 1;

    /**
     * @return crea un nuovo modello
     * @throws SudokuException
     */
    GameModel createModel() throws SudokuException;

}
