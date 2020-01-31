/**
 * UIUtils
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.controller.commands.CommandManager;
import com.kaleyra.academy.sudoku.controller.states.StateFactory;
import com.kaleyra.academy.sudoku.model.ClockManager;
import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.ModelDAO;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import com.kaleyra.academy.sudoku.utils.Utils;
import com.kaleyra.academy.sudoku.utils.os.OSUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Contiene metodi di varia utilità relativi all'interfaccia utente
 *
 * @pattern Delegation, Facade
 */
public class UIUtils {

    /**
     * Filtro di file che lascia passare solo quelli con
     * estensione <code>ModelDAO.FILE_EXTENSION</code>
     */
    private static FileFilter sudokuFileFilter = new FileFilter() {
        public boolean accept(File file) {
            return file.getAbsolutePath().endsWith(
                    ModelDAO.FILE_EXTENSION);
        }

        public String getDescription() {
            return ModelDAO.FILE_DESCRIPTION;
        }
    };

    /**
     * Presenta la finestra di importazione di un gioco
     */
    public static void load() {
        //ottiene il percorso di default dove salvare i file
        String documentsDir = OSUtils.getInstance().getStrategy().getDefaultDataPath();

        //crea la finestra di selezione file e imposta
        //le opzioni relative
        JFileChooser fileChooser = new JFileChooser(documentsDir);
        fileChooser.setFileFilter(sudokuFileFilter);
        fileChooser.setDialogTitle("Importa gioco");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileHidingEnabled(true);

        //presenta la finestra di dialogo associandola alla
        //vista dell'applicazione
        Component parent = GameController.getInstance().getView();
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {

            //in caso si selezione di un file ne estrae
            //il nome e concatena l'estensione, se questa non
            //� gi� presente
            String currentFilename = fileChooser.getSelectedFile().getAbsolutePath();
            if (!currentFilename.endsWith(ModelDAO.FILE_EXTENSION)) {
                currentFilename += ModelDAO.FILE_EXTENSION;
            }
            try {
                //carica il file e lo imposta nel sistema
                GameModel model = ModelDAO.getInstance().load(currentFilename);
                model.setCurrentFilename(currentFilename);
                GameController.getInstance().setModel(model);

                //pulisce l'elenco delle operazioni svolte
                //(per la gestione di undo/redo)
                CommandManager.getInstance().clear();

                //transizione allo stato successivo
                GameController.getInstance().setState(
                        StateFactory.createPlayingGameState());

                //imposta il tempo precedentemente passato e
                //avvia l'orologio
                ClockManager.getInstance().setElapsed(model.getElapsed());
                ClockManager.getInstance().start();
            } catch (SudokuException e) {
                Utils.showAlert("Non è stato possibile caricare il gioco dal file " + currentFilename);
            }
        }
    }

    /**
     * Se il modello corrente ha associato un nome di file,
     * lo sovrascrive con lo stato attuale del gioco.
     * <p/>
     * Diversamente, chiama il metodo <code>saveAs()</code>
     */
    public static void save() {
        //ottiene il modello corrente ed il relativo nome di file
        GameModel model = GameController.getInstance().getModel();
        String currentFilename = model.getCurrentFilename();

        if (currentFilename == null) {
            //se il file non è presente, richiede il nome all'utente
            saveAs();
        } else {
            //salva il file
            try {
                ModelDAO.getInstance().save(
                        GameController.getInstance().getModel(),
                        currentFilename);
            } catch (SudokuException e) {
                Utils.showAlert(
                        "Non è stato possibile salvare il gioco nel file " + currentFilename);
            }
        }
    }

    /**
     * Presenta la finestra di salvataggio di un gioco
     */
    public static void saveAs() {
        //ottiene il modello corrente ed il nome del file
        GameModel model = GameController.getInstance().getModel();
        String currentFilename = model.getCurrentFilename();

        //ottiene il percorso di default dove salvare i file
        String documentsDir = OSUtils.getInstance().getStrategy().getDefaultDataPath();

        //crea la finestra di dialogo e ne imposta le opzioni
        JFileChooser fileChooser = new JFileChooser(documentsDir);
        fileChooser.setFileFilter(sudokuFileFilter);
        fileChooser.setDialogTitle("Salva gioco con nome");
        fileChooser.setFileHidingEnabled(true);

        //se il nome del file corrente non è vuoto, inizializza
        //la finestra di dialogo con il file selezionato
        if (currentFilename != null) {
            File currentFile = new File(
                    documentsDir + File.separator + currentFilename);
            fileChooser.setSelectedFile(currentFile);
        }

        //presenta la finestra di dialogo
        Component parent = GameController.getInstance().getView();
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {

            //se l'utente conferma, viene estratto il nome del
            //file selezionato ed eventualmente viene aggiunta
            //l'estensione
            currentFilename = fileChooser.getSelectedFile().getAbsolutePath();
            if (!currentFilename.endsWith(ModelDAO.FILE_EXTENSION)) {
                currentFilename += ModelDAO.FILE_EXTENSION;
            }

            //imposta il nome corrente del file e chiama il
            //metodo di salvataggio
            model.setCurrentFilename(currentFilename);
            save();
        }
    }

}
