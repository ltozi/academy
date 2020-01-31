/**
 * Utils
 */
package com.kaleyra.academy.sudoku.utils;

import com.kaleyra.academy.sudoku.Sudoku;
import com.kaleyra.academy.sudoku.controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che contiene metodi di varia utilità
 */
public class Utils {

    /**
     * se true controlla la disponibilit� dell'ultima versione
     */
    private static boolean checkVersion = false;

    /**
     * Visualizza una finestra di avviso
     *
     * @param message messaggio da presentare all'utente
     */
    public static void showAlert(String message) {
        JOptionPane.showMessageDialog(
                GameController.getInstance().getView(),
                message, "Sodoku",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Visualizza una finestra interrogativa
     *
     * @param message messaggio da presentare all'utente
     * @return esito della selezione dell'utente
     */
    public static int showQuestion(String message) {
        return JOptionPane.showConfirmDialog(
                GameController.getInstance().getView(),
                message, "Sudoku",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Formatta un periodo di tempo nel formato mm:ss
     *
     * @param elapsed tempo in millisecondi
     * @return tempo in formato mm:ss
     */
    public static String dateFormat(long elapsed) {
        int secondi = (int) (elapsed / 1000);
        int minuti = secondi / 60;

        int secondiRimasti = secondi - minuti * 60;

//		System.out.println( elapsed + ": " +
//				secondiRimasti + ", " + secondi +
//				", " + minuti );

        StringBuffer result = new StringBuffer();
        result.append(secondiRimasti);
        if (result.length() == 1) {
            result.insert(0, "0");
        }
        result.insert(0, ":");
        result.insert(0, minuti);

        return result.toString();
    }

    /**
     * Riproduce un suono di avviso
     */
    public static void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     * Controlla se è disponibile un aggiornamento
     * del programma
     */
    public static void checkVersion() {
        if (checkVersion) {
            //ottiene l'ultima versione
            LatestVersionProxy versionProxy = new LatestVersionProxy();
            Version latestVersion = versionProxy.getLatestVersion();

            //ottiene la versione corrente
            Version currentVersion = Sudoku.getInstance().getCurrentVersion();

            if (!latestVersion.isUpdated(currentVersion)) {
                showAlert(
                        "La versione in uso (" + currentVersion.getVersion() +
                                ") non � aggiornata, dovresti scaricare l'aggiornamento (" +
                                latestVersion.getVersion() + ")");
            }

            System.out.println(currentVersion);
            System.out.println(latestVersion);
        }
    }
}
