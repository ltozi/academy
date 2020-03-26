/**
 * Preferences
 */
package com.kaleyra.academy.sudoku.utils;

import com.kaleyra.academy.sudoku.utils.os.OSUtils;

import java.io.*;
import java.util.Properties;

/**
 * Gestisce le preferenze del programma
 *
 * @pattern Singleton
 */
public class Preferences {

    /**
     * Mostra gli errori mentre si gioca
     */
    public static final String SHOW_ERRORS = "show.errors";
    /**
     * Mostra le guide. Evidenziano le celle dove non pu� essere
     * presente il numero evidenziato
     */
    public static final String SHOW_RULES = "show.rules";
    /**
     * Tiene traccia del tempo trascorso mentre si gioca
     */
    public static final String SHOW_TIMER = "show.timer";
    /**
     * Nasconde automaticamente la finestra di gioco se il timer
     * viene messo in pausa
     */
    public static final String HIDE_WHEN_PAUSE = "show.timer.hideWhenPause";
    /**
     * Mette in pausa automaticamente il timer quando la finestra di
     * gioco viene nascosta o iconizzata
     */
    public static final String PAUSE_WHEN_HIDE = "show.timer.pauseWhenHide";
    /**
     * Nome del file di configurazione
     */
    private static final String PROPERTIES_FILE_NAME = "sudoku.properties";
    /**
     * unica istanza della classe
     */
    private static Preferences instance = new Preferences();

    /**
     * Propriet� di configurazione del programma
     */
    private Properties properties = new Properties();

    /**
     * Crea un oggetto di gestione delle preferenze e carica
     * in memoria la configurazione attuale
     */
    private Preferences() {
        load();
    }

    public static Preferences getInstance() {
        return instance;
    }

    public void setProperty(String propertyName, String value) {
        properties.put(propertyName, value);

        //salva la configurazione ad ogni modifica
        save();
    }

    public String getProperty(String propertyName) {
        return (String) properties.get(propertyName);
    }

    public void setProperty(String propertyName, boolean value) {
        setProperty(propertyName, value ? "true" : "false");
    }

    public boolean getPropertyAsBool(String propertyName) {
        boolean result = false;

        String value = getProperty(propertyName);
        if ("true".equals(value)) {
            result = true;
        }

        return result;
    }

    public void toggleProperty(String propertyName) {
        boolean value = getPropertyAsBool(propertyName);
        setProperty(propertyName, !value);
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(getFilename());
            properties.store(fos, "File di configurazione di iSodoku");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    private void load() {
        try {
        	System.out.println(getFilename());
            FileInputStream fos = new FileInputStream(getFilename());
            properties.load(fos);
       	 System.out.println("Custom file loaded");
        } catch (FileNotFoundException e) {
        	 System.out.println("Custom file settings not found");
            //se il file non è stato trovato, salva quello di
            //default (vuoto)
            save();
        } catch (IOException e) {
          
        }
    }

    private String getFilename() {
        String path = System.getProperty("user.home");

        if(path == null || path.isEmpty())
            return PROPERTIES_FILE_NAME;

        return path + File.separator + PROPERTIES_FILE_NAME;
    }
}
