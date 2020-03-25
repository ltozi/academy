/**
 * AbstractInitiedGameState
 */
package com.kaleyra.academy.sudoku.controller.states;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.ClockManager;
import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.ui.UIUtils;
import com.kaleyra.academy.sudoku.utils.Utils;

import javax.swing.*;

/**
 * Superclasse astratta che rappresenta uno stato in cui
 * il gioco è iniziato e non ancora terminato. Puè dunque essere
 * re-iniziato, terminato o salvato.
 * <p/>
 * L'utente può anche caricare un altro gioco sopra questo e
 * sovrascriverlo
 *
 * @pattern State
 */
public abstract class AbstractInitiedGameState extends GameState {

    /**
     * Chiede all'utente se intende interrompere il gioco e
     * in caso positivo riavvia la partita
     */
    public void restart() {
        if (Utils.showQuestion("è in corso un gioco. Vuoi interromperlo?") == JOptionPane.YES_OPTION) {
            GameState state = StateFactory.createNoGameState();
            state.newGame();

            ClockManager.getInstance().stopAndClear();
        }
    }

    /**
     * Chiede all'utente se desidera terminare il gioco in
     * corso. In caso affermativo viene eseguita una transizione
     * allo stato <code>NoGameState</code>.
     */
    public void terminate() {
        if (Utils.showQuestion("è in corso un gioco. Vuoi terminarlo?") == JOptionPane.YES_OPTION) {
            GameState state = StateFactory.createNoGameState();
            GameController.getInstance().setState(state);

            GameModel model = new GameModel();
            GameController.getInstance().setModel(model);

            ClockManager.getInstance().stopAndClear();
        }
    }

    public void save() {
        UIUtils.save();
    }

    public void saveAs() {
        UIUtils.saveAs();
    }

    public void load() {
        if (Utils.showQuestion("è in corso un gioco. Vuoi caricarne un altro?") == JOptionPane.YES_OPTION) {
            UIUtils.load();
        }
    }

}
