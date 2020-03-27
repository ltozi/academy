/**
 * OSUtils
 */
package com.kaleyra.academy.sudoku.utils.os;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;
import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.ui.PreferencesFrame;

import javax.swing.*;
import java.io.File;

/**
 * Funzioni di varia utilità legate al sistema operativo
 *
 * @pattern Singleton
 */
public class OSUtils {

    /**
     * unica istanza della classe
     */
    private static OSUtils instance = new OSUtils();

    /**
     * Crea nuovo oggetto
     */
    private OSUtils() {
    }

    /**
     * @return unica istanza della classe
     */
    public static OSUtils getInstance() {
        return instance;
    }

    /**
     * @return strategia specifica al sistema operativo in uso
     */
    public Strategy getStrategy() {
        String mrj = System.getProperty("mrj.version");
        if (mrj != null && mrj.length() > 0) {
            return new MacOSXStrategy();
        }
        return new NullStrategy();
    }

    /**
     * Funzionalit� specifiche relative ai sistemi operativi
     *
     * @pattern Strategy
     */
    public interface Strategy {

        /**
         * Esegue le inizializzazioni del sistema
         */
        void init();

        /**
         * @return percorso di default dove salvare i giochi
         */
        String getDefaultDataPath();

        /**
         * @return percorso del file di configurazione
         */
        String getPropertiesPath();
    }

    /**
     * Mac OS X
     *
     * @pattern Strategy
     */
    class MacOSXStrategy implements Strategy {

        /**
         * Imposta il men� a livello di schermata
         */
        public void init() {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            JFrame.setDefaultLookAndFeelDecorated(true);
            Application thisApp = Application.getApplication();
            thisApp.addPreferencesMenuItem();
            thisApp.setEnabledPreferencesMenu(true);
            thisApp.addAboutMenuItem();
            thisApp.setEnabledAboutMenu(true);

            thisApp.addApplicationListener(new ApplicationAdapter() {
                public void handleAbout(ApplicationEvent e) {
                    JOptionPane.showConfirmDialog(GameController.getInstance().getView(), "iSodoku 0.1");
                }

                public void handlePreferences(ApplicationEvent e) {
                    PreferencesFrame p = new PreferencesFrame();
                    p.setVisible(true);
                }

                public void handleQuit(ApplicationEvent e) {
                    GameController.getInstance().quit();
                }
            });
        }

        /**
         * @return percorso ~/Documents per esteso
         */
        public String getDefaultDataPath() {
            String documentsDir = System.getProperty("user.home");
            documentsDir += File.separator + "Documents";
            return documentsDir;
        }

        /**
         * @return percorso ~/Library/Preferences per esteso
         */
        public String getPropertiesPath() {
            String documentsDir = System.getProperty("user.home");
            documentsDir += File.separator + "Library";
            documentsDir += File.separator + "Preferences";
            return documentsDir;
        }
    }

    /**
     * Strategia che non fa nulla, nel caso il programma non
     * sia in grado di determinare il sistema operativo in
     * cui sta girando.
     *
     * @pattern Strategy
     */
    class NullStrategy implements Strategy {

        /**
         * Non fa nulla
         */
        public void init() {
            System.out.println("Null strategy chosen at game satrtup.");
        }

        /**
         * @return stringa vuota
         */
        public String getDefaultDataPath() {
            return "";
        }

        /**
         * @return stringa vuota
         */
        public String getPropertiesPath() {
            return "";
        }
    }
}
