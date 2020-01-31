/**
 * AbstractTheme
 */
package com.kaleyra.academy.sudoku.ui.themes;

import java.awt.*;

/**
 *
 */
public abstract class AbstractTheme implements Theme {

    protected Color currentCellBackground;
    protected Color currentCellForeground;
    protected Color errorCellBackground;
    protected Color errorCellForeground;
    protected Color predefinedCellBackground;
    protected Color predefinedCellForeground;
    protected Color userCellBackground;
    protected Color userCellForeground;

    protected Color gridExternalColor;
    protected Color gridInternalColor;
    protected Color gridSeparatorHColor;
    protected Color gridSeparatorVColor;

    protected Font currentCellFont;
    protected Font errorCellFont;
    protected Font predefinedCellFont;
    protected Font userCellFont;

    protected int gridExternalThickness;
    protected int gridInternalThickness;
    protected int gridSeparatorHThickness;
    protected int gridSeparatorVThickness;

    public Color getCurrentCellBackground() {
        return currentCellBackground;
    }

    public Font getCurrentCellFont() {
        return currentCellFont;
    }

    public Font getCurrentCellFont(Dimension viewDimension) {
        // TODO Auto-generated method stub
        return null;
    }

    public Color getCurrentCellForeground() {
        return currentCellForeground;
    }

    public Color getErrorCellBackground() {
        return errorCellBackground;
    }

    public Font getErrorCellFont() {
        return errorCellFont;
    }

    public Font getErrorCellFont(Dimension viewDimension) {
        // TODO Auto-generated method stub
        return null;
    }

    public Color getErrorCellForeground() {
        return errorCellForeground;
    }

    public Color getGridExternalColor() {
        return gridExternalColor;
    }

    public int getGridExternalThickness() {
        return gridExternalThickness;
    }

    public Color getGridInternalColor() {
        return gridInternalColor;
    }

    public int getGridInternalThickness() {
        return gridInternalThickness;
    }

    public Color getGridSeparatorHColor() {
        return gridSeparatorHColor;
    }

    public int getGridSeparatorHThickness() {
        return gridSeparatorHThickness;
    }

    public Color getGridSeparatorVColor() {
        return gridSeparatorVColor;
    }

    public int getGridSeparatorVThickness() {
        return gridSeparatorVThickness;
    }

    public Color getPredefinedCellBackground() {
        return predefinedCellBackground;
    }

    public Font getPredefinedCellFont() {
        return predefinedCellFont;
    }

    public Font getPredefinedCellFont(Dimension viewDimension) {
        // TODO Auto-generated method stub
        return null;
    }

    public Color getPredefinedCellForeground() {
        return predefinedCellForeground;
    }

    public Color getUserCellBackground() {
        return userCellBackground;
    }

    public Font getUserCellFont() {
        return userCellFont;
    }

    public Font getUserCellFont(Dimension viewDimension) {
        // TODO Auto-generated method stub
        return null;
    }

    public Color getUserCellForeground() {
        return userCellForeground;
    }

}
