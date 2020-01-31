/**
 * PlayingGameState
 */
package com.kaleyra.academy.sudoku.controller.states;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.ClockManager;
import com.kaleyra.academy.sudoku.utils.Utils;

/**
 * Gioco in esecuzione
 *
 * @pattern State
 */
public class PlayingGameState extends AbstractInitiedGameState {

    /**
     * Inizializza il timestamp di inizio del gioco
     */
    public PlayingGameState() {
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
     * Mette in pausa il gioco, riportando una dicitura nella
     * barra di stato ed eseguendo una transizione allo stato
     * <code>PausedGameState</code>
     */
    public void pause() {
        GameController.getInstance().getView().setStatusText("In pausa");
        GameController.getInstance().setState(
                StateFactory.createPausedGameState());
        ClockManager.getInstance().stop();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void resume() {
        Utils.playSound();
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
     * @return true, perchè il gioco è in corso
     */
    public boolean isPlaying() {
        return true;
    }

}
