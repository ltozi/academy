/**
 * CommandManager
 */
package com.kaleyra.academy.sudoku.controller.commands;

import com.kaleyra.academy.sudoku.utils.Utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Gestisce l'esecuzione dei comandi supportati dal sistema
 * e controlla le operazioni di annullo e ripetizione (undo/redo),
 * che sono gestite come casi particolari
 *
 * @pattern Command, Singleton
 */
public class CommandManager {

    /**
     * unica istanza della classe
     */
    private static CommandManager instance = new CommandManager();

    /**
     * elenco operazioni svolte
     */
    private List history = new LinkedList();

    /**
     * Mantiene la posizione corrente, all'interno dell'elenco
     * delle operazioni svolte, dell'ultima operazione annullata
     * o ripristinata.
     */
    private int currentIndex;

    /**
     * Crea un nuovo oggetto
     */
    private CommandManager() {
    }

    /**
     * @return unica istanza della classe
     */
    public static CommandManager getInstance() {
        return instance;
    }

    /**
     * Pulisce l'elenco delle operazioni
     */
    public void clear() {
        history.clear();
    }

    /**
     * Annulla l'ultima operazione
     */
    public void undo() {
        if (history.size() > 0 && currentIndex != -1) {
            AbstractCommand command = (AbstractCommand) history.get(currentIndex);
            command.undoCommand();

            //posiziona l'indice corrente ad un
            //elemento indietro
            currentIndex--;
        } else {
            Utils.playSound();
        }
    }

    /**
     * Ripete l'ultima operazione
     */
    public void redo() {
        if (history.size() > 0 && currentIndex != history.size() - 1) {
            //posiziona l'indice corrente ad un
            //elemento avanti
            currentIndex++;

            AbstractCommand command = (AbstractCommand) history.get(currentIndex);
            command.doCommand();
        } else {
            Utils.playSound();
        }
    }

    /**
     * Invoca il comando specificato
     *
     * @param command comando da invocare
     */
    public void invokeCommand(AbstractCommand command) {
        //i comandi di undo e redo non vengono inseriti
        //nella lista dei comandi eseguiti
        if (command instanceof UndoCommand) {
            undo();
            return;
        }
        if (command instanceof RedoCommand) {
            redo();
            return;
        }

        //questa sezione esegue i comandi "normali"
        if (command.doCommand()) {
            history.add(command);
            currentIndex = history.size() - 1;
        }
    }

    /**
     * @return storia delle mosse effettuate dall'utente
     * @pattern Iterator
     */
    public Iterator getHistoryIterator() {
        return history.iterator();
    }

    /**
     * @param Clazz classe dell'ultimo comando da ritornare
     * @return ultimo comando svolto del tipo richiesto
     */
    public AbstractCommand getLast(Class clazz) {
        AbstractCommand result = null;

        for (int i = currentIndex; currentIndex >= 0; i--) {
            AbstractCommand command = (AbstractCommand) history.get(i);
            if (command.getClass().equals(clazz)) {
                result = command;
                break;
            }
        }

        return result;
    }
}
