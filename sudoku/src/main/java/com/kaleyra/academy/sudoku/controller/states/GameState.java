/**
 * GameState
 */
package com.kaleyra.academy.sudoku.controller.states;

import com.kaleyra.academy.sudoku.controller.states.events.SudokuEventPublisher;

/**
 * Definisce lo stato corrente del gioco
 *
 * @pattern State
 */
public abstract class GameState {

    /**
     * Nuovo gioco
     */
    public abstract void newGame();

    /**
     * Salva gioco
     */
    public abstract void save();

    /**
     * Salva gioco con nome
     */
    public abstract void saveAs();

    /**
     * Carica gioco
     */
    public abstract void load();

    /**
     * Mette in pausa
     */
    public abstract void pause();

    /**
     * Riprende dopo la pausa
     */
    public abstract void resume();

    /**
     * Termina il gioco
     */
    public abstract void end();

    /**
     * @return true se è in corso un gioco
     */
    public abstract boolean isPlaying();

}
