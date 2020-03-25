/**
 * NumberSelectionWindow
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.GameModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Finestra di selezione del numero da inserire in una
 * cella del gioco composta da una griglia di numeri 3x3
 * e da un pulsante di chiusura
 * <p/>
 * La finestra si chiude automaticamente dopo
 * <code>MAX_MILLISEC</code> dall'ultima attività dell'utente
 */
public class NumberSelectionWindow extends JWindow {

    /**
     * numero pulsanti numerici della finestra
     */
    private static final int NUM_BUTTONS = 9;

    /**
     * periodo massimo di persistenza a video senza operativit� dell'utente
     */
    private static final int MAX_MILLISEC = 4600;

    /**
     * testo da utilizzare nel pulsante di cancellazione
     */
    private final String CLEAR_BUTTON_TEXT = "X";
    /**
     * colore di default delle etichette in questo L&F
     */
    private Color defaultLabelColor;
    /**
     * bordo lineare colore grigio
     */
    private Border labelBorder =
            BorderFactory.createLineBorder(Color.lightGray);
    /**
     * timer per la gestione della chiusura automatica della finestra
     */
    private Timer timer = new Timer();
    /**
     * attivit� temporizzata per la gestione della chiusura automatica della finestra
     */
    private TimerTask closeTask;
    /**
     * riga relativa alla cella che l'utente sta modificando
     */
    private Integer row;
    /**
     * colonna relativa alla cella che l'utente sta modificando
     */
    private Integer col;

    /**
     * Crea la finestra ed inizializza l'interfaccia utente
     */
    public NumberSelectionWindow() {
        super();
        createUI();
    }

    /**
     * Crea l'interfaccia utente, che è composta da una griglia
     * di pulsanti con i valori da 1 a 9 più un pulsante in basso
     * che permette la cancellazione del valore presente nella
     * cella di gioco che si sta modificando
     * <p/>
     * I pulsanti sono implementati come etichette
     * sensibili al posizionamento e clic del mouse
     */
    private void createUI() {
        //font da utilizzare per i pulsanti di selezione
        final Font font = new Font("Arial", Font.BOLD, 16);

        //gestione delle etichette di testo
        final MouseListener mouseListener = new MouseAdapter() {

            /**
             * Selezione di un pulsante
             */
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();

                //controlla se la selezione è relativa ad un valore
                //oppure alla pulizia della casella
                if (CLEAR_BUTTON_TEXT.equals(label.getText())) {
                    select(GameModel.EMPTY_VALUE);
                } else {
                    Integer valueSelected = Integer.parseInt(label.getText());
                    select(valueSelected);
                }

                close();
            }

            /**
             * Quando il puntatore del mouse entra in una etichetta
             * ne viene modificato l'aspetto, in modo da
             * dare un ritorno visuale all'utente
             */
            public void mouseEntered(MouseEvent e) {
                rescheduleCloseTask();
                JLabel label = (JLabel) e.getSource();
                label.setOpaque(true);
                label.setBackground(Color.white);
                label.setForeground(Color.blue);
                label.setBorder(labelBorder);
            }

            /**
             * Quando il puntatore del mouse esce dall'etichetta
             * di testo il suo aspetto viene ripristinato.
             */
            public void mouseExited(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                label.setOpaque(false);
                label.setForeground(defaultLabelColor);
                label.setBorder(null);
            }
        };

        //crea la griglia di numeri
        JPanel contentPane = new JPanel(new GridLayout(3, 3));
        JLabel[] buttons = new JLabel[NUM_BUTTONS];
        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttons[i] = new JLabel(String.valueOf(i + 1));
            buttons[i].setHorizontalAlignment(JLabel.CENTER);
            buttons[i].setFont(font);
            buttons[i].addMouseListener(mouseListener);

            contentPane.add(buttons[i]);
        }

        //pulsante di azzeramento della cella
        JLabel clearButton = new JLabel(CLEAR_BUTTON_TEXT);
        clearButton.setHorizontalAlignment(JLabel.CENTER);
        clearButton.setFont(font);
        clearButton.addMouseListener(mouseListener);

        //costruisce l'interfaccia
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPane, BorderLayout.CENTER);
        getContentPane().add(clearButton, BorderLayout.SOUTH);
        setSize(100, 120);

        //memorizza il colore di default dei pulsanti
        defaultLabelColor = buttons[0].getForeground();

    }

    /**
     * Mostra la finestra alle coordinate indicate
     *
     * @param x   coordinata assoluta sullo schermo dove è stato fatto clic
     * @param y   coordinata assoluta sullo schermo dove è stato fatto clic
     * @param row riga della cella da modificare
     * @param col colonna della cella da modificare
     */
    public void show(int x, int y, Integer row, Integer col) {
        this.row = row;
        this.col = col;

        //centra la popup nella casella
        x -= this.getWidth() / 2;
        y -= this.getHeight() / 2;

        //posiziona la finestra e la rende visibile
        setLocation(x, y);
        toFront();
        setVisible(true);
        setAlwaysOnTop(true);

        //attiva il task di chiusura della finestra
        rescheduleCloseTask();
    }

    /**
     * Riesegue la schedulazione del task di chiusura
     * automatica della finestra
     */
    private void rescheduleCloseTask() {
        if (closeTask != null) {
            closeTask.cancel();
        }
        createCloseTask();
        timer.schedule(closeTask, MAX_MILLISEC);
    }

    /**
     * Attiva un timer che chiude la finestra
     * dopo MAX_MILLISEC secondi
     */
    private void createCloseTask() {
        closeTask = new TimerTask() {
            public void run() {
                close();
            }
        };
    }

    /**
     * Chiude la finestra. Se il task di chiusura automatica
     * è attivo, lo termina.
     */
    public void close() {
        if (closeTask != null) {
            closeTask.cancel();
        }
        setVisible(false);
    }

    /**
     * Invia il messaggio di selezione di un valore per
     * una determinata cella alla classe che controlla
     * il gioco
     *
     * @param value valore selezionato
     */
    private void select(Integer value) {
        GameController.getInstance().select(row, col, value);
    }
}
