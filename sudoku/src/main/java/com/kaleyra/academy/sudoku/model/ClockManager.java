/**
 * CounterManager
 */
package com.kaleyra.academy.sudoku.model;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.ui.GameFrame;
import com.kaleyra.academy.sudoku.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Contatore che gestisce lo scorrimento del tempo
 * di gioco
 *
 * @pattern Singleton
 */
public class ClockManager {

    /**
     * unica istanza della classe
     */
    public static ClockManager instance = new ClockManager();

    /**
     * timestamp inizio sessione di misurazione del tempo
     */
    private long started;

    /**
     * tempo precedentemente trascorso per questo gioco
     */
    private long previousElapsed;

    /**
     * true se il contatore sta scorrendo
     */
    private boolean playing;

    /**
     * Crea un <code>ClockManager</code> inizializzando
     * il timer che circa ogni secondo aggiorna il modello
     * dati con il tempo trascorso
     */
    private ClockManager() {
        //aggiorna, circa ogni secondo, il tempo di
        //gioco trascorso
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                GameFrame frame =
                        GameController.getInstance().getView();
                GameModel model =
                        GameController.getInstance().getModel();

                updateModel();

                frame.setTimerText(
                        Utils.dateFormat(model.getElapsed()) + "     ");
            }
        }, 900, 900);
    }

    /**
     * @return unica istanza della classe
     */
    public static ClockManager getInstance() {
        return instance;
    }

    /**
     * Avvia il contatore
     */
    public void start() {
        started = System.currentTimeMillis();
        playing = true;
    }

    /**
     * Interrompe il contatore
     */
    public void stop() {
        long stopped = System.currentTimeMillis();
        previousElapsed += stopped - started;
        started = 0;
        playing = false;

        updateModel();
    }

    /**
     * Interrompe il contatore ed azzera il tempo trascorso
     */
    public void stopAndClear() {
        stop();
        previousElapsed = 0;
        updateModel();
    }

    /**
     * @return il tempo trascorso
     */
    public long getElapsed() {
        return computeElapsed();
    }

    /**
     * Imposta il tempo trascorso
     *
     * @param elapsed
     */
    public void setElapsed(long elapsed) {
        previousElapsed = elapsed;
    }

    /**
     * Calcola il tempo trascorso
     *
     * @return tempo trascorso
     */
    private long computeElapsed() {
        long elapsed =
                System.currentTimeMillis() - started + previousElapsed;

        if (!playing) {
            elapsed = previousElapsed;
        }

        return elapsed;
    }

    /**
     * Metodo non sincronizzato in quanto l'unica entit�
     * che aggiorna il tempo trascorso di un modello � questo
     * oggetto.
     */
    private void updateModel() {
        GameModel model = GameController.getInstance().getModel();
        model.setElapsed(computeElapsed());
    }

}
