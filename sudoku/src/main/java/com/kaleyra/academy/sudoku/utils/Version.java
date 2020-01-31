/**
 * Version
 */
package com.kaleyra.academy.sudoku.utils;

import java.util.Date;

/**
 * Definisce la versione del programma
 *
 * @pattern Immutable
 */
public class Version {

    /**
     * versione principale
     */
    int major;

    /**
     * versione secondaria
     */
    int minor;

    /**
     * revisione
     */
    int revision;

    /**
     * data di rilascio
     */
    Date released;

    /**
     * descrizione
     */
    String description;

    /**
     * Crea un nuovo oggetto vuoto
     */
    public Version() {
    }

    /**
     * Costruisce un nuovo oggetto
     *
     * @param versions    array dei numeri di versione
     * @param released    data di rilascio
     * @param description descrizione
     */
    public Version(int[] versions, Date released, String description) {
        major = versions[0];
        minor = versions[1];
        revision = versions[2];

        this.released = released;
        this.description = description;
    }

    /**
     * @return versione
     */
    public String getVersion() {
        return major + "." + minor + "." + revision;
    }

    /**
     * @return data di rilascio
     */
    public Date getReleased() {
        return released;
    }

    /**
     * @return descrizione
     */
    public String getDescription() {
        return description;
    }

    /**
     * Indica se la versione corrente è aggiornata o meno
     * rispetto ad una versione di riferimento
     *
     * @param riferimento versione da verificare
     * @return true se le due versioni coincidono
     */
    public boolean isUpdated(Version riferimento) {
        return equals(riferimento);
    }

    /**
     * Due oggetti <code>Version</code> sono uguali se i numeri di
     * versione e le date di rilascio coincidono. Non viene
     * controllata la descrizione del rilascio, che pu� quindi subire
     * modifiche successive.
     *
     * @param o oggetto da confrontare
     * @return true se i due oggetti sono equivalenti
     */
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Version) {
            Version v = (Version) o;
            result = v.getVersion().equals(getVersion()) &&
                    v.getReleased().equals(getReleased());
        }
        return result;
    }

    /**
     * @return una rappresentazione testuale della versione
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append("=[version=");
        sb.append(getVersion());
        sb.append(", released=");
        sb.append(released);
        sb.append(", description");
        sb.append(description);
        sb.append("]");
        return sb.toString();
    }

}
