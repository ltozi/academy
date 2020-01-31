package com.kaleyra.academy.sudoku.controller;

import com.kaleyra.academy.sudoku.controller.commands.AbstractCommand;
import com.kaleyra.academy.sudoku.controller.commands.CommandFactory;
import com.kaleyra.academy.sudoku.controller.commands.CommandManager;
import com.kaleyra.academy.sudoku.controller.commands.InsertValueCommand;
import com.kaleyra.academy.sudoku.controller.states.GameState;
import com.kaleyra.academy.sudoku.controller.states.StateFactory;
import com.kaleyra.academy.sudoku.controller.states.events.SudokuEventPublisher;
import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;
import com.kaleyra.academy.sudoku.ui.GameFrame;
import com.kaleyra.academy.sudoku.ui.HistoryViewFrame;
import com.kaleyra.academy.sudoku.utils.Preferences;
import com.kaleyra.academy.sudoku.utils.Utils;

import java.util.Iterator;

/**
 * Controlla le operazioni di gioco implementando
 * le funzioni di base e le interazioni tra vista
 * e modello dati
 *
 * @pattern MVC, Singleton
 */
public class GameController  implements SudokuEventPublisher {

    private SudokuValidationStrategy validationStrategy = new DefaultValidationStrategy();

    /**
     * unica istanza della classe
     */
    private static GameController instance;

    /**
     * vista del gioco
     */
    private GameFrame view;

    private HistoryViewFrame historyViewFrame;

    /**
     * modello dei dati
     */
    private GameModel model;

    /**
     * stato corrente del gioco
     */
    private GameState state;

    /**
     * Crea un nuovo oggetto
     * inizializzando lo stato di partenza
     */
    private GameController() {
        //lo stato iniziale è "nessun gioco in corso"
        state = StateFactory.createNoGameState();
    }

    /**
     * @return unica istanza della classe
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * @return la vista corrente
     */
    public GameFrame getView() {
        return view;
    }

    /**
     * Imposta la vista corrente
     *
     * @param view
     */
    public void setView(GameFrame view) {
        this.view = view;
    }

    /**
     * @return il modello corrente
     */
    public GameModel getModel() {
        return model;
    }

    /**
     * Imposta il modello corrente
     *
     * @param model
     */
    public void setModel(GameModel model) {
        this.model = model;

        //se la vista è presente, la aggiorno
        if (view != null) {
            view.refreshUI();
        }
    }

    /**
     * @return lo stato corrente
     */
    public GameState getState() {
        return state;
    }

    /**
     * Imposta lo stato corrente
     *
     * @param state
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * inizia un nuovo gioco
     */
    public void newGame() {
        state.newGame();
        updateHistoryView();
        historyViewFrame.setVisible(true);
//		view.requestFocus();
        view.setVisible(true);
    }

    /**
     * termina il gioco in corso
     */
    public void end() {
        state.end();
        historyViewFrame.setVisible(false);
    }

    /**
     * mette in pausa il gioco corrente
     */
    public void pause() {
        state.pause();
    }

    /**
     * ripristina il gioco corrente
     */
    public void resume() {
        state.resume();
    }

    /**
     * @return c'è un gioco in corso?
     */
    public boolean isPlaying() {
        return state.isPlaying();
    }

    /**
     * Salva il gioco nel file corrente. Se non esiste file corrente,
     * lo chiede all'utente.
     * <p/>
     * Se non è in corso alcun gioco emette un suono e non fa nulla
     */
    public void save() {
        state.save();
    }

    /**
     * Presenta la finestra di selezione del file e
     * permette all'utente di scegliere un nome di file
     */
    public void saveAs() {
        state.saveAs();
    }

    /**
     * Importa un gioco predefinito o salvato in precedenza
     */
    public void load() {
        state.load();
        updateHistoryView();
    }

    /**
     * Seleziona il valore per una cella della griglia di
     * gioco
     *
     * @param row
     * @param col
     * @param value
     */
    public void select(Integer row, Integer col, Integer value) {
        //crea il comando di inserimento del valore
        AbstractCommand command = CommandFactory.createInsertValue(row, col, value);

        if(Preferences.getInstance().getPropertyAsBool(Preferences.SHOW_ERRORS)) {
            if(!validationStrategy.isValidMove(model.getData(), row, col, value)) {
                Utils.showAlert("Il valore " + value + " non è valido per questa cella!");
                return;
            }
        }

        //invoca il comando
        CommandManager.getInstance().invokeCommand(command);

        if(validationStrategy.isValidModel(model.getData())) {
            this.publishOnWin(this);
        }

        //aggiorna la vista sulla history delle mosse
        updateHistoryView();

        //svuota la riga di stato
        view.setStatusText("");
    }

    /**
     * Annulla l'ultimo comando
     */
    public void undo() {
        AbstractCommand command = CommandFactory.createUndo();
        CommandManager.getInstance().invokeCommand(command);

        updateHistoryView();
    }

    /**
     * Ripristina l'ultimo comando
     */
    public void redo() {
        AbstractCommand command = CommandFactory.createRedo();
        CommandManager.getInstance().invokeCommand(command);

        updateHistoryView();
    }

    /**
     * Aggiorna la vista sulla storia delle mosse fatte
     */
    private void updateHistoryView() {
        Iterator iter =
                CommandManager.getInstance().getHistoryIterator();
        historyViewFrame.update(iter);
    }

    public HistoryViewFrame getHistoryView() {
        return historyViewFrame;
    }

    public void setHistoryView(HistoryViewFrame frame) {
        this.historyViewFrame = frame;
    }

    public void showErrors() {
        Preferences.getInstance().toggleProperty(
                Preferences.SHOW_ERRORS);
    }

    public void showLatestMove() {
        InsertValueCommand command =
                (InsertValueCommand) CommandManager.getInstance().getLast(InsertValueCommand.class);

        if (command != null) {
            view.showMove(command.getRow(), command.getCol());
        }
    }

    public void quit() {
        view.setVisible(false);
        historyViewFrame.setVisible(false);
        view.dispose();
        historyViewFrame.dispose();

        System.exit(0);
    }
}
