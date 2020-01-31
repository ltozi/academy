/**
 * IncorrectVersion
 */
package com.kaleyra.academy.sudoku.utils;

/**
 * Definisce una versione di cui non è stato possibile
 * eseguire completamente o totalmente il parsing, dunque
 * che potrebbe contenere dati non validi
 *
 * @pattern Special Case
 */
public class IncorrectVersion extends Version {

    /**
     * Crea un nuovo oggetto. La descrizione suggerisce
     * che la versione non è disponibile
     */
    public IncorrectVersion() {
        description = "(errori nell'ottenimento dei dati)";
    }

    /**
     * Assume che la versione in uso sia la più recente
     */
    public boolean isUpdated(Version currentVersion) {
        return true;
    }

    /**
     * L'uguaglianza è determinata solo dal fatto che
     * l'oggetto passato è di questa classe
     */
    public boolean equals(Object o) {
        return (o instanceof IncorrectVersion);
    }

}
