package com.kaleyra.academy.sudoku.ui;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class GameFrameTest {

    @Test
    public void shouldSetupMenuAsExpected() {
        GameFrame gameFrame = new GameFrame();
        JMenuBar jMenuBar = gameFrame.getJMenuBar();
        MenuElement[] subElements = jMenuBar.getSubElements();

        assertEquals(3, subElements.length);

        JMenu menuItem = (JMenu) subElements[0];

        assertNotNull(menuItem);
        assertEquals("Gioco", menuItem.getText());
        assertNotNull(menuItem.getActionListeners());
    }

}