/**
 * GameModel
 */
package com.kaleyra.academy.sudoku.model;

/**
 * Rappresenta la griglia di 9 righe per 9 colonne che
 * rappresenta il piano di gioco del Sudoku.
 *
 * @pattern MVC
 */
public class GameModel {

    /**
     * numero massimo righe
     */
    public static final int ROWS = 9;

    /**
     * numero massimo colonne
     */
    public static final int COLS = 9;

    /**
     * valore che indica una cella vuota
     */
    public static final Integer EMPTY_VALUE = 0;

    /**
     * valore delle celle
     */
    private int[][] data = new int[ROWS][COLS];

    /**
     * flag che indica i valori presenti in partenza
     */
    private boolean[][] predefined = new boolean[ROWS][COLS];

    /**
     * tempo di gioco trascorso
     */
    private long elapsed;

    /**
     * nome del file relativo a questo gioco
     */
    private String currentFilename;

    /**
     * Costruisce un nuovo modello vuoto
     */
    public GameModel() {
    }

    /**
     * Costruisce un nuovo modello a partire
     * da due insiemi di celle. Il primo contiene celle "predefinite",
     * il secondo celle compilate dall'utente
     *
     * @param predefinedCells celle predefinite
     * @param userCells       celle compilate dall'utente
     * @param elapsed         tempo di gioco già trascorso
     */
    public GameModel(int[][] predefinedCells, int[][] userCells, long elapsed) {

        //imposta il tempo trascorso
        this.elapsed = elapsed;

        //analizza i dati di inizializzazione ed
        //imposta i flag relativi alle celle
        //valorizzate in partenza
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                //in caso di collisione tra i dati presenti
                //nelle due matrici, hanno precedenza le celle
                //predefinite
                data[row][col] = userCells[row][col];

                //se la cella corrente è predefinita, imposta
                //il flag relativo
                if (predefinedCells[row][col] != 0) {
                    data[row][col] = predefinedCells[row][col];
                    predefined[row][col] = true;
                }
            }
        }
    }

    /**
     * Ritorna il valore di una cella
     *
     * @param row riga
     * @param col colonna
     * @return valore della cella
     */
    public Integer getCellValue(int row, int col) {
        return data[row][col];
    }

    /**
     * Imposta il valore di una cella
     *
     * @param row   riga
     * @param col   colonna
     * @param value valore da impostare
     */
    public void setCellValue(int row, int col, Integer value) {
        data[row][col] = value;
    }

    /**
     * Indica se una cella contiene un valore già risolto
     *
     * @param row riga
     * @param col colonna
     * @return true se la cella è predefinita
     */
    public boolean isPredefined(int row, int col) {
        return predefined[row][col];
    }

    /**
     * @return tempo di gioco trascorso
     */
    public long getElapsed() {
        return elapsed;
    }

    /**
     * Imposta il tempo di gioco trascorso
     *
     * @param elapsed
     */
    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    /**
     * @return nome del file corrente
     */
    public String getCurrentFilename() {
        return currentFilename;
    }

    /**
     * Imposta il nome del file corrente
     *
     * @param currentFilename
     */
    public void setCurrentFilename(String currentFilename) {
        this.currentFilename = currentFilename;
    }

    public int[][] getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( " ");
        for (int row = 0; row < data.length; row++) {
            int[] datum = data[row];
            for (int col = 0; col < datum.length; col++) {
                builder.append(data[row][col]);
                builder.append( " | ");
            }
            builder.append( " \n ");
        }
        return builder.toString();
    }

}
