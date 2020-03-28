/**
 * NewGameState
 */
package com.kaleyra.academy.sudoku.controller.states;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.controller.commands.CommandManager;
import com.kaleyra.academy.sudoku.model.ClockManager;
import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.NewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.GameStrategyFactory;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.UnknownDifficultyLevelException;
import com.kaleyra.academy.sudoku.ui.UIUtils;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import com.kaleyra.academy.sudoku.utils.Utils;

/**
 * Stato che indica che non è in corso
 * alcun gioco
 *
 * @pattern State
 */
public class NoGameState extends GameState {

    /**
     * Inizia un nuovo gioco caricando un set di dati nel modello
     */
    public void newGame() {
        try {
            //ottiene la strategia corrente di creazione di un nuovo
            //gioco
            NewGameStrategy strategy = GameStrategyFactory.getStrategy();

            //crea un nuovo gioco chiedendo il modello dati alla
            //strategia
            GameModel model = strategy.createModel();

            //imposta il modello dati
            GameController.getInstance().setModel(model);

            //pulisce l'elenco delle operazioni svolte
            //(per la gestione di undo/redo)
            CommandManager.getInstance().clear();

            //transizione allo stato successivo
            GameController.getInstance().setState(
                    StateFactory.createPlayingGameState());

            //avvia l'orologio
            ClockManager.getInstance().start();

        } catch (SudokuException | UnknownDifficultyLevelException e) {
            Utils.showAlert("Impossibile iniziare un nuovo gioco");
        }
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void save() {
        Utils.playSound();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void saveAs() {
        Utils.playSound();
    }

    /**
     * Carica gioco
     */
    public void load() {
        UIUtils.load();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void pause() {
        Utils.playSound();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void resume() {
        Utils.playSound();
    }

    /**
     * Operazione non significativa, produce un suono
     */
    public void end() {
        Utils.playSound();
    }

    /**
     * @return false, in quando non è in corso alcun gioco
     */
    public boolean isPlaying() {
        return false;
    }

}
