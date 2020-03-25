/**
 * GetLatestVersionProxy
 */
package com.kaleyra.academy.sudoku.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Proxy per l'accesso al servizio di ottenimento dell'ultima
 * versione disponibile
 *
 * @pattern Proxy
 */
public class LatestVersionProxy {

    /**
     * dimensione del buffer di lettura dati remoti
     */
    private final int BUFFER_SIZE = 4096;

    /**
     * Crea un nuovo oggetto
     */
    public LatestVersionProxy() {
    }

    /**
     * @return ultima versione del software disponibile
     */
    public Version getLatestVersion() {
        Version result = null;
        try {
            URL url = new URL("http://127.0.0.1/~max/bigatti.it/Version");
            InputStream in = url.openStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int readed = in.read(buffer);

            String data = new String(buffer, 0, readed);
            result = parseVersionString(data);

        } catch (MalformedURLException e) {
            throw new IllegalStateException(
                    "Programma non correttamente configurato");
        } catch (IOException e) {
            //se non è possibile ottenere l'ultima versione
            //disponibile, viene ritornato uno special case
            result = new NotAvailableVersion();
        }
        return result;
    }

    /**
     * Elabora la stringa della versione scaricata dal
     * sito Web del software e crea l'oggetto versione
     * relativo
     *
     * @param data nel formato 1.0.0;20050809;Prima versione stabile
     * @return oggetto <code>Version</code> calcolato
     */
    private Version parseVersionString(String data) {
        Version result = new IncorrectVersion();

        int[] versions = null;
        Date versionDate = null;
        String description = null;

        //estrapola i dati dalla stringa
        int count = 0;
        StringTokenizer st = new StringTokenizer(data, ";");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            switch (count) {
                case 0:
                    versions = processVersion(token);
                    break;
                case 1:
                    versionDate = processDate(token);
                    break;
                case 2:
                    description = token;
            }

            count++;
        }

        //crea l'oggetto versione solo se tutti i dati sono
        //stati ottenuti correttamente
        if (versions != null && versionDate != null && description != null) {
            result = new Version(versions, versionDate, description);
        }

        return result;
    }

    /**
     * Divide la stringa della versione nei suoi elementi numerici
     *
     * @param version stringa da elaborare
     * @return numeri di versioni sottoforma di array di interi
     */
    private int[] processVersion(String version) {
        int[] result = new int[3];
        StringTokenizer st = new StringTokenizer(version, ".");
        try {
            result[0] = Integer.parseInt(st.nextToken());
            result[1] = Integer.parseInt(st.nextToken());
            result[2] = Integer.parseInt(st.nextToken());
        } catch (NumberFormatException e) {
            result = null;
        } catch (NoSuchElementException e) {
            result = null;
        }
        return result;
    }

    /**
     * Elabora la data e la converte in oggetto Date
     *
     * @param dateString String che rappresenta la data
     * @return la data come oggetto <code>Date</code>
     */
    private Date processDate(String dateString) {
        Date result = null;

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(Calendar.YEAR, Integer.parseInt(dateString.substring(0, 4)));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateString.substring(4, 6)));
            calendar.set(Calendar.DATE, Integer.parseInt(dateString.substring(6, 8)));
            result = calendar.getTime();
        } catch (NumberFormatException e) {
            //in caso di errori ritorna null
        }

        return result;
    }

}
