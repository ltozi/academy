/**
 * SodokuFrame
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.controller.GameController;
import com.kaleyra.academy.sudoku.model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Finestra principale dell'applicazione
 *
 * @pattern MVC
 */
public class GameFrame extends JFrame {
    private static final Color NORMAL_CELLS_COLOR = Color.BLACK;

    private static final Color PREDEFINED_CELLS_COLOR = Color.BLUE;

    private static final Color HIGHLIGHT_CELL_COLOR = Color.CYAN;

    /**
     * larghezza della finestra
     */
    private static final int DEFAULT_FRAME_WIDTH = 400;

    /**
     * altezza della finestra
     */
    private static final int DEFAULT_FRAME_HEIGHT = DEFAULT_FRAME_WIDTH + 20;

    /**
     * numero di celle per riga
     */
    private static final int ROWS = 9;

    /**
     * numero di celle per colonna
     */
    private static final int COLS = 9;

    /**
     * etichette che compongono le singole celle del
     * piano di gioco
     */
    JLabel[][] labels = new JLabel[ROWS][COLS];
    /**
     * barra di stato
     */
    JLabel statusText = new JLabel(" ");
    /**
     * timer
     */
    JLabel timerText = new JLabel(" ");
    /**
     * gestore di evento sulle singole celle
     */
    private MouseListener labelMouseListener = new LabelMouseListener();
    /**
     * finestra di selezione dei numeri da inserire
     */
    private NumberSelectionWindow selectionWindow = new NumberSelectionWindow();
    /**
     * dimensione corrente del font, utilizzato per
     * il ridimensionamento della finestra
     */
    private int currentFontSize = 20;

    /**
     * Crea ed inizializza una finestra Swing che
     * contiene l'interfaccia utente del programma
     */
    public GameFrame() {
        super("Sudoku");
        createUI();
    }

    /**
     * Crea l'interfaccia utente
     */
    private void createUI() {
        //imposta il men� del programma
        JMenuBar menuBar = createMenu();
        setJMenuBar(menuBar);

        //layout manager della finestra
        getContentPane().setLayout(new BorderLayout());

        //font utilizzato per le cifre di gioco
        Font labelFont = createFont();

        //crea il piano di gioco
        JPanel contentPane = new JPanel(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                //creazione etichetta ed impostazione
                //delle relative caratteristiche
                labels[row][col] = new JLabel();
                labels[row][col].setFont(labelFont);
                labels[row][col].setHorizontalAlignment(JLabel.CENTER);

                //imposta il gestore di evento sull'etichetta
                labels[row][col].addMouseListener(labelMouseListener);

                contentPane.add(labels[row][col]);
            }
        }

        BorderComposer composer = new BorderComposer();
        composer.setBorders(labels);

        //crea la barra di stato
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(statusText, BorderLayout.WEST);
        statusBar.add(timerText, BorderLayout.EAST);

        getContentPane().add(contentPane, BorderLayout.CENTER);
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setLocation(0, 0);

        //collega l'iconizzazione della finestra principale
        //a nascondere quella dell'history
        addWindowListener(new WindowAdapter() {
            public void windowDeiconified(WindowEvent e) {
                if (GameController.getInstance().isPlaying()) {
                    GameController.getInstance().getHistoryView().setVisible(true);
                    relocateHistoryView();
                    setVisible(true);
                }
            }

            public void windowClosing(WindowEvent e) {
                GameController.getInstance().quit();
            }

            public void windowIconified(WindowEvent e) {
                GameController.getInstance().getHistoryView().setVisible(false);
            }
        });

        addComponentListener(new ComponentAdapter() {
            //al ridimensionamento della finestra viene
            //chiamato il ridimensionamento del contenuto
            //dei singoli pannelli
            public void componentResized(ComponentEvent e) {
                resizeUI();
                relocateHistoryView();
            }

            //al riposizionamento della finestra, sposta
            //anche la finestra della history collegata
            public void componentMoved(ComponentEvent e) {
                relocateHistoryView();
            }
        });

    }

    /**
     * @return font da utilizzare per le caselle di gioco
     */
    private Font createFont() {
        Font labelFont = new Font("Arial", Font.PLAIN, currentFontSize);
        return labelFont;
    }

    /**
     * Crea la barra dei menu dell'applicazione
     *
     * @return il menu dell'applicazione
     */
    private JMenuBar createMenu() {
        KeyStroke ks;

        //nuovo gioco
        JMenuItem newGameMenuItem = new JMenuItem("Nuovo");
        ks = KeyStroke.getKeyStroke('N',
                java.awt.event.InputEvent.META_MASK);
        newGameMenuItem.setAccelerator(ks);
        newGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().newGame();
            }
        });

        //chiude il gioco corrente
        JMenuItem endGameMenuItem = new JMenuItem("Chiudi");
        ks = KeyStroke.getKeyStroke('W',
                java.awt.event.InputEvent.META_MASK);
        endGameMenuItem.setAccelerator(ks);
        endGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().end();
            }
        });

        //mette in pausa il gioco
        JMenuItem pauseMenuItem = new JMenuItem("Pausa");
        ks = KeyStroke.getKeyStroke('U',
                java.awt.event.InputEvent.META_MASK);
        pauseMenuItem.setAccelerator(ks);
        pauseMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().pause();
            }
        });

        //riprende il gioco
        JMenuItem resumeMenuItem = new JMenuItem("Riprendi");
        ks = KeyStroke.getKeyStroke('R',
                java.awt.event.InputEvent.META_MASK);
        resumeMenuItem.setAccelerator(ks);
        resumeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().resume();
            }
        });

        //salva il gioco
        JMenuItem saveMenuItem = new JMenuItem("Salva...");
        ks = KeyStroke.getKeyStroke('S',
                java.awt.event.InputEvent.META_MASK);
        saveMenuItem.setAccelerator(ks);
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().save();
            }
        });

        //salva il gioco con nome
        JMenuItem saveAsMenuItem = new JMenuItem("Salva con nome...");
        ks = KeyStroke.getKeyStroke('S',
                java.awt.event.InputEvent.META_MASK |
                        java.awt.event.InputEvent.SHIFT_MASK);
        saveAsMenuItem.setAccelerator(ks);
        saveAsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().saveAs();
            }
        });

        //salva il gioco
        JMenuItem importMenuItem = new JMenuItem("Importa...");
        ks = KeyStroke.getKeyStroke('I',
                java.awt.event.InputEvent.META_MASK |
                        java.awt.event.InputEvent.SHIFT_MASK);
        importMenuItem.setAccelerator(ks);
        importMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().load();
            }
        });

        //annulla ultima mossa
        JMenuItem undoMenuItem = new JMenuItem("Annulla ultima mossa");
        ks = KeyStroke.getKeyStroke('Z',
                java.awt.event.InputEvent.META_MASK);
        undoMenuItem.setAccelerator(ks);
        undoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().undo();
            }
        });

        //ripristina ultima mossa
        JMenuItem redoMenuItem = new JMenuItem("Ripristina ultima mossa");
        ks = KeyStroke.getKeyStroke('Z',
                java.awt.event.InputEvent.META_MASK |
                        java.awt.event.InputEvent.SHIFT_MASK);
        redoMenuItem.setAccelerator(ks);
        redoMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().redo();
            }
        });

        //mostra ultima mossa
        JMenuItem showLastMoveMenuItem = new JMenuItem("Mostra ultima mossa");
        showLastMoveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().showLatestMove();
            }
        });

        JMenuItem showHintMenuItem = new JMenuItem("Mostra suggerimento");
        showHintMenuItem.setEnabled(false);
        JMenuItem showSolutionMenuItem = new JMenuItem("Mostra soluzione");
        showSolutionMenuItem.setEnabled(false);
        JMenuItem showRulesMenuItem = new JMenuItem("Mostra guide");
        showRulesMenuItem.setEnabled(false);

        //mostra errori
        //TODO: refresh dello stato di selezione in funzione delle propriet� persistite
        JCheckBoxMenuItem showErrorsMenuItem =
                new JCheckBoxMenuItem("Mostra errori");
        showErrorsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().showErrors();
            }
        });

        //contrai finestre (minimizza)
        JMenuItem contraiMenuItem = new JMenuItem("Contrai");
        ks = KeyStroke.getKeyStroke('M',
                java.awt.event.InputEvent.META_MASK);
        contraiMenuItem.setAccelerator(ks);
        contraiMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });

        //dimensione di default
        JMenuItem dimensioneDefaultMenuItem = new JMenuItem("Dimensione default");
        ks = KeyStroke.getKeyStroke('D',
                java.awt.event.InputEvent.META_MASK);
        dimensioneDefaultMenuItem.setAccelerator(ks);
        dimensioneDefaultMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
            }
        });

        //finestra principale
        JMenuItem finestraPrincipaleMenuItem = new JMenuItem("Finestra Principale");
        finestraPrincipaleMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toFront();
                setVisible(true);
            }
        });

        JMenuItem finestraStatisticheMenuItem = new JMenuItem("Statistiche");
        finestraStatisticheMenuItem.setEnabled(false);

        //menu gioco
        JMenu gameMenu = new JMenu("Gioco");
        gameMenu.add(newGameMenuItem);
        gameMenu.add(endGameMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(pauseMenuItem);
        gameMenu.add(resumeMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(saveMenuItem);
        gameMenu.add(saveAsMenuItem);
        gameMenu.add(importMenuItem);

        //menu mosse
        JMenu mosseMenu = new JMenu("Mosse");
        mosseMenu.add(undoMenuItem);
        mosseMenu.add(redoMenuItem);
        mosseMenu.addSeparator();
        mosseMenu.add(showLastMoveMenuItem);
        mosseMenu.add(showHintMenuItem);
        mosseMenu.add(showSolutionMenuItem);
        mosseMenu.addSeparator();
        mosseMenu.add(showRulesMenuItem);
        mosseMenu.add(showErrorsMenuItem);

        //menu finestra
        JMenu finestraMenu = new JMenu("Finestra");
        finestraMenu.add(contraiMenuItem);
        finestraMenu.add(dimensioneDefaultMenuItem);
        finestraMenu.addSeparator();
        finestraMenu.add(finestraPrincipaleMenuItem);
        finestraMenu.add(finestraStatisticheMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        menuBar.add(mosseMenu);
        menuBar.add(finestraMenu);

        return menuBar;
    }

    /**
     * Imposta il testo di stato
     *
     * @param text testo da visualizzare
     */
    public void setStatusText(String text) {
        statusText.setText(text);
    }

    /**
     * Imposta il testo del contatore
     *
     * @param text testo da visualizzare
     */
    public void setTimerText(String text) {
        timerText.setText(text);
    }

    /**
     * Aggiorna l'interfaccia utente inviando
     * un messaggio di rinfresco ai singoli pannelli
     * Sudoku presenti nella finestra
     */
    public void refreshUI() {
        GameModel model =
                GameController.getInstance().getModel();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                setCellValue(row, col, model.getCellValue(row, col));
                setCellPredefined(row, col, model.isPredefined(row, col));
            }
        }
        repaint();
    }

    /**
     * Chiamato quando la finestra viene ridimensionata,
     * aggiorna la dimensione delle cifre nelle caselle di gioco,
     * ma solo se la nuova dimensione � diversa da quella
     * attuale.
     */
    private void resizeUI() {
        GameFrame frame = GameController.getInstance().getView();
        Dimension frameSize = frame.getSize();

        int maxSize = (int) Math.min(
                frameSize.getHeight(), frameSize.getWidth());
        int fontSize = 20 * maxSize / 400;

        if (fontSize != currentFontSize) {
            currentFontSize = fontSize;
            Font currentFont = createFont();
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    labels[row][col].setFont(currentFont);
                }
            }
        }
    }

    /**
     * Imposta il valore di una cella. Se il valore �
     * <code>GameModel.EMPTY_VALUE</code>, la cella � considerata
     * vuota e non viene presentato alcun valore al suo interno.
     *
     * @param row   riga della cella
     * @param col   colonna della cella
     * @param value valore da impostare
     */
    private void setCellValue(int row, int col, Integer value) {
        if (value == GameModel.EMPTY_VALUE) {
            labels[row][col].setText("");
        } else {
            labels[row][col].setText(String.valueOf(value));
        }
    }

    /**
     * Imposta se una cella contiene un valore predefinito o meno.
     * Il testo delle celle normali � in colore nero, mentre le
     * celle con valori predefiniti hanno testo in colore blu.
     *
     * @param row        riga della cella
     * @param col        colonna della cella
     * @param predefined true se il valore � predefinito
     */
    private void setCellPredefined(int row, int col, boolean predefined) {
        if (predefined) {
            labels[row][col].setForeground(PREDEFINED_CELLS_COLOR);
        } else {
            labels[row][col].setForeground(NORMAL_CELLS_COLOR);
        }
    }

    /**
     * Visualizza graficamente l'ultima cella
     *
     * @param row
     * @param col
     */
    public void showMove(final int row, final int col) {
        final int iterations = 8;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int currentIteration = 0;

            public void run() {
                Color c = (currentIteration % 2 == 0) ?
                        HIGHLIGHT_CELL_COLOR : NORMAL_CELLS_COLOR;
                labels[row][col].setForeground(c);

                currentIteration++;
                if (currentIteration == iterations) {
                    cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 250);
    }

    /**
     *
     */
    private void relocateHistoryView() {
        Point p = getLocation();
        Dimension d = getSize();

        p.setLocation(p.x + d.getWidth() + 10, p.y);

        GameController.getInstance().getHistoryView().setLocation(p);
    }

    /**
     * Gestore di evento sulle singole celle, che si occupa
     * primariamente di gestire il clic sulle stesse.
     *
     * @pattern Observer
     */
    private class LabelMouseListener extends MouseAdapter {

        /**
         * Alla pressione di un pulsante del mouse viene
         * invocata, se � in corso un gioco, la finestra
         * di selezione del valore da inserire
         */
        public void mousePressed(MouseEvent e) {
            //se il gioco � interrotto non fa nulla
            if (!GameController.getInstance().isPlaying()) {
                return;
            }

            //determina, a partire dall'oggetto che ha
            //generato l'evento, di quale cella si tratta
            Integer row = getRow((JLabel) e.getSource());
            Integer col = getCol((JLabel) e.getSource());

            GameModel model = GameController.getInstance().getModel();

            //se la cella contiene un valore predefinito, visualizza
            //un messaggio di errore e termina
            if (!model.isPredefined(row, col)) {

                //determina la posizione assoluta sullo schermo
                //del punto dove l'utente ha eseguito il clic
                Point p = e.getPoint();
                SwingUtilities.convertPointToScreen(p,
                        (Component) e.getSource());


                int x = (int) p.getLocation().getX() + 300;
                int y = (int) p.getLocation().getY() + 300;

                //apre la finestra di selezione alle coordinate
                //determinate
                selectionWindow.close();
                selectionWindow.show(x, y, row, col);
            } else {
                GameController.getInstance().getView().setStatusText(
                        "Non puoi selezionare il valore di una cella predefinita!");
            }
        }

        /**
         * Identifica l'indice dell'etichetta di testo passata
         * come parametro, elemento che permette l'individuazione
         * delle coordinate dell'etichetta all'interno del pannello
         *
         * @param label etichetta da ricercare
         * @return numero tra 0 e ROWS*COLS
         */
        private int getLabelIndex(JLabel label) {
            int index = -1;

            //esegue una ricerca seriale confrontando
            //gli oggetti per equivalenza logica
            //(probabilmente avrebbe funzionato anche l'equivalenza
            //di riferimenti tramite ==)
            int i = 0;
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (label.equals(labels[row][col])) {
                        index = i;
                    }
                    i++;
                }
            }

            //se non viene trovata l'etichetta viene generata
            //un'eccezione di runtime. Questo evento non dovrebbe
            //essere possibile
            if (index == -1) {
                throw new IllegalStateException("Impossibile individuare l'etichetta " + label +
                        " tra quelle disponibili " + labels);
            }

            return index;
        }

        /**
         * @param label etichetta selezionata
         * @return riga dell'etichetta nel pannello
         */
        private Integer getRow(JLabel label) {
            return (getLabelIndex(label) / ROWS);
        }

        /**
         * @param label etichetta selezionata
         * @return colonna dell'etichetta nel pannello
         */
        private Integer getCol(JLabel label) {
            return  (getLabelIndex(label) % COLS);
        }
    }

}
