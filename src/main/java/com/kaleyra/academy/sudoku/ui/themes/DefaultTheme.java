/**
 * DefaultTheme
 */
package com.kaleyra.academy.sudoku.ui.themes;

import java.awt.*;

/**
 * Default Theme
 */
public class DefaultTheme extends AbstractTheme {

    char[] characters = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * Creates a new default theme
     */
    public DefaultTheme() {
        currentCellBackground = Color.WHITE;
        currentCellForeground = Color.CYAN;
        errorCellBackground = Color.WHITE;
        errorCellForeground = Color.RED;
        predefinedCellBackground = Color.WHITE;
        predefinedCellForeground = Color.BLUE;
        userCellBackground = Color.WHITE;
        userCellForeground = Color.BLACK;

        gridExternalColor = Color.BLACK;
        gridInternalColor = Color.LIGHT_GRAY;
        gridSeparatorHColor = Color.GRAY;
        gridSeparatorVColor = Color.GRAY;

//		protected Font currentCellFont;
//		protected Font errorCellFont;
//		protected Font predefinedCellFont;
//		protected Font userCellFont;

        gridExternalThickness = 2;
        gridInternalThickness = 1;
        gridSeparatorHThickness = 1;
        gridSeparatorVThickness = 1;
    }

    /**
     * @return "Default Theme"
     */
    public String getDescription() {
        return "Default Theme";
    }

    /**
     * @return false, no images
     */
    public boolean hasImages() {
        return false;
    }

    /**
     * @return true
     */
    public boolean hasCharacters() {
        return true;
    }

    /**
     * @return 1-9
     */
    public char getCharacters(int index) {
        return characters[index];
    }

    /**
     * @return null
     */
    public Image getImage(int index) {
        return null;
    }

    /**
     * @return true
     */
    public boolean canScale() {
        return true;
    }

    /**
     * @return true, always installed
     */
    public boolean isInstalled() {
        return true;
    }

    public boolean isSelected() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return preview image
     */
    public Image getPreview() {
        // TODO Auto-generated method stub
        return null;
    }

}
