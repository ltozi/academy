/**
 * PausedGameState
 */
package com.kaleyra.academy.sudoku.controller.states;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.ClockManager;
import com.kaleyra.academy.sudoku.utils.Utils;

/**
 * Gioco in pausa, il timer è temporaneamente sospeso
 *
 * @pattern State
 */
public class PausedGameState extends AbstractInitiedGameState {

    /**
     * Crea un nuovo oggetto
     */
    public PausedGameState() {
    }

    /**
     * La richiesta di un nuovo gioco presenta una finestra
     * di conferma, perchè l'operazione equivale all'annullare
     * il gioco in corso.
     */
    public void newGame() {
        restart();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void pause() {
        Utils.playSound();
    }

    /**
     * Riprende il gioco, eseguendo una transizione allo
     * stato <code>PlayingGameState</code>
     */
    public void resume() {
        GameController.getInstance().getView().setStatusText("");
        GameController.getInstance().setState(
                StateFactory.createPlayingGameState());
        ClockManager.getInstance().start();
    }

    /**
     * La richiesta di terminare il gioco prevede una
     * conferma da parte dell'utente, in quanto il gioco
     * corrente verrebbe annullato.
     */
    public void end() {
        terminate();
    }

    /**
     * @return false, in quanto il gioco è sospeso
     */
    public boolean isPlaying() {
        return false;
    }

}
