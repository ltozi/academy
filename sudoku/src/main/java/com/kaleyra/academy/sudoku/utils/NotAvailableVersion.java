/**
 * NotAvailableVersion
 */
package com.kaleyra.academy.sudoku.utils;

/**
 * Numero ultima versione non disponibile
 *
 * @pattern Special Case
 */
public class NotAvailableVersion extends Version {

    /**
     * Crea un nuovo oggetto. La descrizione suggerisce
     * che la versione non è disponibile
     */
    public NotAvailableVersion() {
        description = "(non disponibile)";
    }

    /**
     * essendo impossibile determinare l'ultima versione
     * il programma deve assumere che quella in uso
     * è la più recente disponibile
     */
    public boolean isUpdated(Version currentVersion) {
        return true;
    }

    /**
     * L'uguaglianza è determinata solo dal fatto che
     * l'oggetto passato è di questa classe
     */
    public boolean equals(Object o) {
        return (o instanceof NotAvailableVersion);
    }

}
