/**
 * Sudoku
 */
package com.kaleyra.academy.sudoku;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.controller.states.GameState;
import com.kaleyra.academy.sudoku.controller.states.StateFactory;
import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.ui.GameFrame;
import com.kaleyra.academy.sudoku.ui.HistoryViewFrame;
import com.kaleyra.academy.sudoku.utils.Utils;
import com.kaleyra.academy.sudoku.utils.Version;
import com.kaleyra.academy.sudoku.utils.os.OSUtils;

import javax.swing.*;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale dell'applicazione.
 *
 * @pattern Singleton
 */
public class Sudoku {

    /**
     * singleton
     */
    private static Sudoku instance = new Sudoku();

    /**
     * versione attuale dell'applicazione
     */
    private Version currentVersion = new Version(
            new int[]{1, 0, 0}, new Date(), "Versione beta");

    /**
     * Il costruttore inizializza il controllore e la vista,
     * oltre ad eseguire eventuali configurazioni specifiche
     * per il sistema operativo in uso
     */
    private Sudoku() {
        //Inizializzazioni specifiche al sistema operativo
        OSUtils.getInstance().getStrategy().init();

        //FIXME
        Logger.getLogger("").addHandler(new ConsoleHandler());
        Logger.getLogger("").setLevel(Level.ALL);

        //crea un modello vuoto e lo associa al controllore
        GameController controller = GameController.getInstance();
        controller.setModel(new GameModel());

        //crea l'interfaccia utente principale
        controller.setView(new GameFrame());

        //crea la finestra della storia delle mosse
        controller.setHistoryView(new HistoryViewFrame());

        controller.subscribe(
                publisher -> {
                    int i = JOptionPane.showConfirmDialog(GameController.getInstance().getView(),
                        "Congrats! You solved the puzzle! Do you want to make another game?", "Sudoku",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if(i == JOptionPane.YES_OPTION) {
                        controller.newGame();
                    }
                });


    }

    /**
     * @return unica istanza della classe
     */
    public static Sudoku getInstance() {
        return instance;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile", "true");

        Sudoku sodoku = Sudoku.getInstance();
        sodoku.show();
    }

    /**
     * Mostra l'interfaccia utente
     */
    public void show() {
        JFrame frame = GameController.getInstance().getView();
        frame.setVisible(true);

        //controlla l'ultima versione disponibile
        //la verifica avviene qui perchè se fosse nel costruttore
        //si avrebbe una circolarità tra getInstance() e costruttore
        Utils.checkVersion();
    }

    /**
     * @return ritorna la versione corrente
     */
    public Version getCurrentVersion() {
        return currentVersion;
    }

}
