/**
 * AbstractCommand
 */
package com.kaleyra.academy.sudoku.controller.commands;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.ui.GameFrame;

/**
 * Classe base per tutti i comandi che possono essere
 * annullati
 *
 * @pattern Command
 */
public abstract class AbstractCommand {

    /**
     * riferimento al gestore dei comandi
     */
    protected final static CommandManager manager =
            CommandManager.getInstance();

    /**
     * Esegue il comando
     *
     * @return
     */
    public boolean doCommand() {
        refreshView();
        return false;
    }

    /**
     * Annulla l'esecuzione del comando
     *
     * @return
     */
    public boolean undoCommand() {
        refreshView();
        return false;
    }

    /**
     * Ad ogni esecuzione o ripristino del comando viene
     * aggiornata la vista dell'applicazione
     */
    private void refreshView() {
        GameFrame view = GameController.getInstance().getView();
        view.refreshUI();
    }
}
