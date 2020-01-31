/**
 * Theme
 */
package com.kaleyra.academy.sudoku.ui.themes;

import java.awt.*;

/**
 * Define a common interface for themes
 */
public interface Theme {

    /**
     * Max symbol/image supported
     */
    int MAX_SYMBOL = 16;

    /**
     * @return theme description
     */
    String getDescription();

    /**
     * @return grid external border color
     */
    Color getGridExternalColor();

    /**
     * @return grid internal border color
     */
    Color getGridInternalColor();

    /**
     * @return grid vertical internal separator color
     */
    Color getGridSeparatorVColor();

    /**
     * @return grid horizontal internal separator color
     */
    Color getGridSeparatorHColor();

    int getGridExternalThickness();

    int getGridInternalThickness();

    int getGridSeparatorVThickness();

    int getGridSeparatorHThickness();

    Color getCurrentCellForeground();

    Color getCurrentCellBackground();

    Color getPredefinedCellForeground();

    Color getPredefinedCellBackground();

    Color getUserCellBackground();

    Color getUserCellForeground();

    Color getErrorCellForeground();

    Color getErrorCellBackground();

    Font getCurrentCellFont();

    Font getPredefinedCellFont();

    Font getUserCellFont();

    Font getErrorCellFont();

    Font getCurrentCellFont(Dimension viewDimension);

    Font getPredefinedCellFont(Dimension viewDimension);

    Font getUserCellFont(Dimension viewDimension);

    Font getErrorCellFont(Dimension viewDimension);

    /**
     * this theme uses images
     */
    boolean hasImages();

    /**
     * this theme uses characters
     */
    boolean hasCharacters();

    /**
     * @param index symbol index
     * @return character representing symbol
     */
    char getCharacters(int index);

    /**
     * @param index symbol index
     * @return image representing symbol
     */
    Image getImage(int index);

    /**
     * @return true if character/image can scale size based on view dimension
     */
    boolean canScale();

    /**
     * @return true if installed on the system
     */
    boolean isInstalled();

    /**
     * @return true if currently selected
     */
    boolean isSelected();

    /**
     * @return theme preview image
     */
    Image getPreview();

}
