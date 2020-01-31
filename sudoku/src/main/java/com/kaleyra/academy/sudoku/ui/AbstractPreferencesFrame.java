/**
 * AbstractPreferencesFrame
 */
package com.kaleyra.academy.sudoku.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPreferencesFrame extends JFrame {

    protected JPanel contents;
    protected CardLayout cardLayout;

    protected int panelCount;
    protected int defaultPanel = 0;

    protected String[] descriptions;
    protected String[] icons;
    protected JPanel[] panels;
    protected JButton[] buttons;

    protected IconToolbar toolbar;

    protected Border labelBorder =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0, 0, 5, 0),
                    BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)
            );

    public AbstractPreferencesFrame(String title, int panelCount) {
        super(title);
        this.panelCount = panelCount;

        panels = new JPanel[panelCount];
        buttons = new JButton[panelCount];
        descriptions = new String[panelCount];
        icons = new String[panelCount];

        setup();
        createUI();
    }

    protected abstract void setup();

    protected void createUI() {
        toolbar = createToolbar();

        cardLayout = new CardLayout();
        contents = new JPanel(cardLayout);
        contents.setBorder(
                BorderFactory.createEmptyBorder(10, 40, 20, 40));

        for (int i = 0; i < panelCount; i++) {
            cardLayout.addLayoutComponent(panels[i], "" + i);
            contents.add(panels[i], "" + i);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(contents, BorderLayout.CENTER);

        setResizable(false);
        setLocation(50, 50);

        showPanel(defaultPanel);
    }

    protected IconToolbar createToolbar() {
        IconToolbar toolbar = new IconToolbar();

        for (int i = 0; i < panelCount; i++) {
            final int id = i;

            buttons[i] = toolbar.createButton(descriptions[i], icons[i]);
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showPanel(id);
                }
            });
        }

        return toolbar;
    }

    protected void showPanel(int panelIndex) {
        cardLayout.show(contents, "" + panelIndex);
        setTitle(descriptions[panelIndex]);

        for (int i = 0; i < panelCount; i++) {
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
        }

        buttons[panelIndex].setContentAreaFilled(true);
        buttons[panelIndex].setBorderPainted(true);

        Dimension d = panels[panelIndex].getPreferredSize();
        d.height += toolbar.getPreferredSize().height + 50;
        d.width += 80;
        setSize(d);
    }

}
