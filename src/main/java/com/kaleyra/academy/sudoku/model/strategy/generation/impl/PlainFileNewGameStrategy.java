/**
 * PlainFileNewGameStrategy
 */
package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.ModelDAO;
import com.kaleyra.academy.sudoku.model.strategy.generation.NewGameStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;

/**
 * Questa strategia di creazione di un nuovo gioco si limita
 * a caricare un file dal disco
 *
 * @pattern Strategy
 */
public class PlainFileNewGameStrategy implements NewGameStrategy {

    /**
     * Carica il file game1.sudoku e ritorna il modello dati.
     * Questo file contiene un gioco di esempio per provare
     * il programma.
     *
     * @throws SudokuException
     */
    public GameModel createModel() throws SudokuException {
        return ModelDAO.getInstance().load("game1.sudoku");
    }

}
