/**
 * CommandFactory
 */
package com.kaleyra.academy.sudoku.controller.commands;

/**
 * Factory di creazione di comandi all'applicazione
 *
 * @pattern Abstract Factory
 */
public class CommandFactory {

    /**
     * Crea un comando di inserimento di un valore
     *
     * @param row   riga della griglia
     * @param col   colonna della griglia
     * @param value valore da inserire
     * @return comando richiesto
     */
    public static AbstractCommand createInsertValue(Integer row, Integer col, Integer value) {
        return new InsertValueCommand(row, col, value);
    }

    /**
     * Crea un comando di annullo del comando precedente
     *
     * @return comando richiesto
     */
    public static AbstractCommand createUndo() {
        return new UndoCommand();
    }

    /**
     * Crea un comando di riesecuzione dell'ultimo comando
     * annullato
     *
     * @return comando richiesto
     */
    public static AbstractCommand createRedo() {
        return new RedoCommand();
    }
}
