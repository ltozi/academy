/**
 * PreferencesFrame
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.ui.themes.Theme;
import com.kaleyra.academy.sudoku.ui.themes.ThemesManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class PreferencesFrame extends AbstractPreferencesFrame {

    private static final int PANEL_COUNT = 4;

    private JCheckBox showTimerCheckBox;
    private JCheckBox hideWhenPauseCheckBox;
    private JCheckBox pauseWhenHideCheckBox;
    private JCheckBox showErrorsCheckBox;
    private JCheckBox allowHintsCheckBox;
    private JCheckBox useTimerCheckBox;

    public PreferencesFrame() {
        super("Preferenze", PANEL_COUNT);
    }

    protected void setup() {
        descriptions[0] = "Generale";
        descriptions[1] = "Nuovo gioco";
        descriptions[2] = "    Temi    ";
        descriptions[3] = "Posta elettronica";

        icons[0] = "GeneralPref.png";
        icons[1] = "NewGamePref.png";
        icons[2] = "IconPref.png";
        icons[3] = "MailPref.png";

        panels[0] = createGeneralePanel();
        panels[1] = createNuovoGiocoPanel();
        panels[2] = createTemiPanel();
        panels[3] = createMailPanel();
    }

    private JPanel createGeneralePanel() {
        JLabel timerLabel = new JLabel("Timer");
        timerLabel.setBorder(labelBorder);
        JLabel pausaLabel = new JLabel("Pausa");
        pausaLabel.setBorder(labelBorder);
        JLabel soluzioneLabel = new JLabel("Soluzione");
        soluzioneLabel.setBorder(labelBorder);
        JLabel varieLabel = new JLabel("Varie");
        varieLabel.setBorder(labelBorder);

        useTimerCheckBox = new JCheckBox("Utilizza timer");
        showTimerCheckBox = new JCheckBox("Mostra timer durante il gioco");
        hideWhenPauseCheckBox = new JCheckBox("Nascondi la finestra quando il gioco viene messo in pausa");
        pauseWhenHideCheckBox = new JCheckBox("Metti in pausa il gioco quando la finestra viene nascosta");
        showErrorsCheckBox = new JCheckBox("Mostra errori durante il gioco");
        allowHintsCheckBox = new JCheckBox("Consenti suggerimenti");

        JPanel p = new JPanel(new GridLayout(15, 1));
//		SpringLayout panelLayout = new SpringLayout();
//		JPanel p = new JPanel( panelLayout );

        p.add(timerLabel);
        p.add(useTimerCheckBox);
        p.add(showTimerCheckBox);
        p.add(new JLabel());
        p.add(pausaLabel);
        p.add(hideWhenPauseCheckBox);
        p.add(pauseWhenHideCheckBox);
        p.add(new JLabel());
        p.add(soluzioneLabel);
        p.add(new JCheckBox("Mostra soluzione automaticamente alla fine del gioco"));
        p.add(showErrorsCheckBox);
        p.add(allowHintsCheckBox);
        p.add(new JLabel());
        p.add(varieLabel);
        p.add(new JCheckBox("Visualizza elenco mosse durante il gioco"));

//		panelLayout.putConstraint(SpringLayout.NORTH,
//				timerLabel, 0, SpringLayout.NORTH, p);
//		panelLayout.putConstraint(SpringLayout.NORTH,
//				useTimerCheckBox, 10, SpringLayout.SOUTH, timerLabel);
//		panelLayout.putConstraint(SpringLayout.NORTH,
//				showTimerCheckBox, 0, SpringLayout.SOUTH, useTimerCheckBox);

        return p;
    }

    //livello, dimensione griglia
    private JPanel createNuovoGiocoPanel() {
        String[] gridSizeList = {"6x6", "9x9", "12x12"};
        String[] levelList = {"Facile", "Medio", "Difficile", "Molto difficile"};

        JPanel p = new JPanel(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(2, 2));

        p1.add(createRightOrientedLabel("Dimensione griglia nuovo gioco: "));
        p1.add(new JComboBox(gridSizeList));
        p1.add(createRightOrientedLabel("Livello di difficoltà: "));
        p1.add(new JComboBox(levelList));

        JLabel previewLabel1 = new JLabel("Anteprima");
        previewLabel1.setBorder(
                BorderFactory.createEmptyBorder(10, 0, 5, 0));

        JLabel previewLabel2 = new JLabel();
        previewLabel2.setBackground(Color.WHITE);
        previewLabel2.setOpaque(true);
        previewLabel2.setBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        previewLabel2.setPreferredSize(new Dimension(100, 100));

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(previewLabel1, BorderLayout.NORTH);
        p2.add(previewLabel2, BorderLayout.CENTER);

        p.add(p1, BorderLayout.NORTH);
        p.add(p2, BorderLayout.CENTER);

        return p;
    }

    private JPanel createTemiPanel() {
        TableModel iconSetModel = new AbstractTableModel() {

            public int getRowCount() {
                return ThemesManager.getInstance().getThemes().length;
            }

            public int getColumnCount() {
                return 4;
            }

            public Object getValueAt(int row, int col) {
                Theme[] themesList =
                        ThemesManager.getInstance().getThemes();
                Theme thisTheme = themesList[row];

                switch (col) {
                    case 0:
                        return new Boolean(thisTheme.isSelected());
                    case 1:
                        return thisTheme.getDescription();
                    case 2:
                        return thisTheme.getPreview();
                    case 3:
                        return new Boolean(thisTheme.isInstalled());
                }
                return null;
            }

            public String getColumnName(int col) {
                switch (col) {
                    case 0:
                        return "�";
                    case 1:
                        return "Nome";
                    case 2:
                        return "Anteprima";
                    case 3:
                        return "�";
                }
                return "";
            }

            public Class getColumnClass(int col) {
                switch (col) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Image.class;
                    case 3:
                        return Boolean.class;
                }
                return null;
            }

        };
        JTable table = new JTable(iconSetModel);
        TableColumn c1 = table.getColumnModel().getColumn(0);
        c1.setMaxWidth(18);
        c1.setMinWidth(18);
        TableColumn c2 = table.getColumnModel().getColumn(1);
        c2.setMaxWidth(150);
        c2.setMinWidth(150);
        TableColumn c4 = table.getColumnModel().getColumn(3);
        c4.setMaxWidth(18);
        c4.setMinWidth(18);

        JPanel pulsantiPanel = new JPanel(new FlowLayout());
        pulsantiPanel.add(new JButton("Aggiorna elenco temi"));

        JLabel descriptionLabel = new JLabel("Temi disponibili");
        descriptionLabel.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel p = new JPanel(new BorderLayout());
        p.add(descriptionLabel, BorderLayout.NORTH);
        p.add(new JScrollPane(table),
                BorderLayout.CENTER);
        p.add(pulsantiPanel, BorderLayout.SOUTH);

        return p;
    }

    private JPanel createMailPanel() {
        JPanel p = new JPanel(new BorderLayout());
        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(10);
        JPanel p1 = new JPanel(gridLayout);

        JTextField nomeTextField = new JTextField(10);
        nomeTextField.setText(System.getProperty("user.name"));

        p1.add(createRightOrientedLabel("Indirizzo di posta del mittente: "));
        p1.add(new JTextField(10));
        p1.add(createRightOrientedLabel("Nome mittente: "));
        p1.add(nomeTextField);
        p1.add(createRightOrientedLabel("Indirizzo server SMTP: "));
        p1.add(new JTextField(15));

        p.add(p1, BorderLayout.NORTH);

        return p;
    }

    protected JLabel createRightOrientedLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }
}
