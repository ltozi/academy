/**
 * SodokuException
 */
package com.kaleyra.academy.sudoku.utils;

/**
 * Eccezione generica del programma Sudoku
 */
public class SudokuException extends Exception {

    /**
     * Crea un nuovo oggetto
     */
    public SudokuException() {
        super();
    }

    /**
     * Crea un nuovo oggetto
     */
    public SudokuException(String message) {
        super(message);
    }

    /**
     * Crea un nuovo oggetto
     */
    public SudokuException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea un nuovo oggetto
     */
    public SudokuException(Throwable cause) {
        super(cause);
    }

}
