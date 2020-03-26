/**
 * ModelLoader
 */
package com.kaleyra.academy.sudoku.model;

import com.kaleyra.academy.sudoku.utils.SudokuException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Oggetto di accesso ai dati, raccoglie le funzionalità
 * per esportare e importare i modelli di gioco su file
 * di testo
 *
 * @pattern DAO, Singleton
 */
public class ModelDAO {
    /**
     * estensione dei file Sudoku
     */
    public static final String FILE_EXTENSION = ".sudoku";
    /**
     * descrizione file Sudoku
     */
    public static final String FILE_DESCRIPTION = "Sudoku File";
    private static Logger logger =
            Logger.getLogger("com.kaleyra.academy.sudoku.model.ModelDAO");
    /**
     * unica istanza della classe
     */
    private static ModelDAO instance = new ModelDAO();

    /**
     * stringa di versione all'inizio del file
     */
    private final String versionString = "SODOKU-1";

    /**
     * flag che identifica le celle "predefinite"
     */
    private final String PREDEFINED_TYPE = "P";

    /**
     * flag che identifica le celle compilate dall'utente
     */
    private final String USER_TYPE = "U";

    /**
     * Costruisce un nuovo oggetto di accesso ai dati
     */
    private ModelDAO() {
    }

    /**
     * @return unica istanza della classe
     */
    public static ModelDAO getInstance() {
        return instance;
    }

    /**
     * Carica un gioco in memoria e restituisce un modello
     * che contiene le relative informazioni
     *
     * @param filename nome del file da caricare
     * @return il modello creato
     * @throws SudokuException
     */
    public GameModel load(String filename) throws SudokuException {
        //celle predefinite
        int[][] predefinedCells =
                new int[GameModel.ROWS][GameModel.COLS];

        //celle compilate dall'utente
        int[][] userCells =
                new int[GameModel.ROWS][GameModel.COLS];

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);

            //contatore di riga utilizzato per identificare
            //la prima, che contiene la versione
            //int rowCount = -1;

            //tempo trascorso contenuto nel file di gioco
            long elapsed = 0;

            //TODO Step 1 logic to load file

            String content = new String ( Files.readAllBytes( Paths.get(filename) ) );
            String lines[] = content.split("\\r?\\n");
            System.out.println("Content in File Line 2:::"+lines[2]);
            for(int k=2;k<lines.length;k++){
                String[] args = lines[k].split(";");
                int i = Integer.parseInt(args[1]);
                int j = Integer.parseInt(args[2]);
                int value = Integer.parseInt(args[3]);
                predefinedCells[i][j] = value;
            }
            System.out.println("Content in File:::"+content);


            logger.info("File " + filename + " caricato");

            //costruisce e ritorna il modello di gioco
            return new GameModel(predefinedCells, userCells, elapsed);

        } catch (IOException e) {
            throw new SudokuException("Impossibile caricare il gioco " +
                    filename, e);
        }
    }

    /**
     * Salva il gioco nel file indicato
     *
     * @param model
     * @param filename
     * @throws SudokuException
     */
    public void save(GameModel model, String filename) throws SudokuException {
        try {
            //crea l'oggetto di scrittura
            FileWriter writer = new FileWriter(filename);

            //scrive la versione
            writer.write(versionString);
            writer.write('\n');

            //scrive il tempo trascorso
            writer.write(String.valueOf(model.getElapsed()));
            writer.write('\n');

            //scorre il modello e salva lo stato delle
            //celle, solo se il valore non è "VUOTO"
            for (byte row = 0; row < GameModel.ROWS; row++) {
                for (byte col = 0; col < GameModel.COLS; col++) {
                    Integer value = model.getCellValue(row, col);
                    String flag = model.isPredefined(row, col) ?
                            PREDEFINED_TYPE : USER_TYPE;

                    if (value != GameModel.EMPTY_VALUE) {
                        StringBuffer record = new StringBuffer();
                        record.append(flag);
                        record.append(";");
                        record.append(row);
                        record.append(";");
                        record.append(col);
                        record.append(";");
                        record.append(value);

                        writer.write(record.toString());
                        writer.write('\n');
                    }
                }
            }

            writer.close();
            logger.fine("File " + filename + " salvato");
        } catch (IOException e) {
            throw new SudokuException("Impossibile salvare il gioco nel file " + filename, e);
        }
    }
}
