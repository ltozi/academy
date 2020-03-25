/**
 * BorderComposer
 */
package com.kaleyra.academy.sudoku.ui;

import com.kaleyra.academy.sudoku.model.GameModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Creates borders for every cell in the board
 *
 * @pattern Delegation
 * , emulas
 */
public class BorderComposer {

    /**
     * external border
     */
    private static final int EXTERNAL = 0;

    /**
     * internal border
     */
    private static final int INTERNAL = 1;

    /**
     * horizontal separator
     */
    private static final int SEPARATOR_H = 2;

    /**
     * vertical separator
     */
    private static final int SEPARATOR_V = 3;

    /**
     * empty border
     */
    private static final int EMPTY = 9;

    /**
     * border present
     */
    private static final int PRESENT = 1;

    private Color[] colors =
            {Color.BLACK, Color.LIGHT_GRAY, Color.GRAY, Color.GRAY};

    private int[] thickness =
            {2, 1, 1, 1};

    /**
     * defines wich border has every cell
     */
    private int[][] pattern = {
            //row 1
            {EXTERNAL, INTERNAL, INTERNAL, EXTERNAL},
            {EXTERNAL, INTERNAL, INTERNAL, EMPTY},
            {EXTERNAL, SEPARATOR_V, INTERNAL, EMPTY},
            {EXTERNAL, INTERNAL, INTERNAL, EMPTY},
            {EXTERNAL, INTERNAL, INTERNAL, EMPTY},
            {EXTERNAL, SEPARATOR_V, INTERNAL, EMPTY},
            {EXTERNAL, INTERNAL, INTERNAL, EMPTY},
            {EXTERNAL, INTERNAL, INTERNAL, EMPTY},
            {EXTERNAL, EXTERNAL, INTERNAL, EMPTY},

            //row 2
            {EMPTY, INTERNAL, INTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, EXTERNAL, INTERNAL, EMPTY},

            //row 3
            {EMPTY, INTERNAL, SEPARATOR_H, EXTERNAL},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, SEPARATOR_V, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, SEPARATOR_V, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, EXTERNAL, SEPARATOR_H, EMPTY},

            //row 4
            {EMPTY, INTERNAL, INTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, EXTERNAL, INTERNAL, EMPTY},

            //row 5
            {EMPTY, INTERNAL, INTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, EXTERNAL, INTERNAL, EMPTY},

            //row 6
            {EMPTY, INTERNAL, SEPARATOR_H, EXTERNAL},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, SEPARATOR_V, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, SEPARATOR_V, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, INTERNAL, SEPARATOR_H, EMPTY},
            {EMPTY, EXTERNAL, SEPARATOR_H, EMPTY},

            //row 7
            {EMPTY, INTERNAL, INTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, EXTERNAL, INTERNAL, EMPTY},

            //row 8
            {EMPTY, INTERNAL, INTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, INTERNAL, INTERNAL, EMPTY},
            {EMPTY, EXTERNAL, INTERNAL, EMPTY},

            //row 9
            {EMPTY, INTERNAL, EXTERNAL, EXTERNAL},
            {EMPTY, INTERNAL, EXTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, EXTERNAL, EMPTY},
            {EMPTY, INTERNAL, EXTERNAL, EMPTY},
            {EMPTY, INTERNAL, EXTERNAL, EMPTY},
            {EMPTY, SEPARATOR_V, EXTERNAL, EMPTY},
            {EMPTY, INTERNAL, EXTERNAL, EMPTY},
            {EMPTY, INTERNAL, EXTERNAL, EMPTY},
            {EMPTY, EXTERNAL, EXTERNAL, EMPTY},
    };

    /**
     * Creates a new composer
     */
    public BorderComposer() {
    }

    /**
     * Set borders for every cell in the board
     *
     * @param labels labels matrix
     */
    public void setBorders(JLabel[][] labels) {
        int cellNumber = 0;

        for (int row = 0; row < GameModel.ROWS; row++) {
            for (int col = 0; col < GameModel.COLS; col++) {
                int[] cellBorders = pattern[cellNumber];

                int[] externalMatte = createMatte(cellBorders, EXTERNAL);
                int[] internalMatte = createMatte(cellBorders, INTERNAL);
                int[] separatorVMatte = createMatte(cellBorders, SEPARATOR_V);
                int[] separatorHMatte = createMatte(cellBorders, SEPARATOR_H);

                Border externalBorder =
                        createBorder(externalMatte, EXTERNAL);
                Border internalBorder =
                        createBorder(internalMatte, INTERNAL);
                Border separatorVBorder =
                        createBorder(separatorVMatte, SEPARATOR_V);
                Border separatorHBorder =
                        createBorder(separatorHMatte, SEPARATOR_H);

                Border b1 =
                        BorderFactory.createCompoundBorder(externalBorder, separatorHBorder);
                Border b2 =
                        BorderFactory.createCompoundBorder(b1, separatorVBorder);
                Border b3 =
                        BorderFactory.createCompoundBorder(b2, internalBorder);

                labels[row][col].setBorder(b3);

                cellNumber++;
            }
        }
    }

    /**
     * @param border border pattern
     * @param type   border type
     * @return matte for border type provided
     */
    private int[] createMatte(int[] border, int type) {
        int[] result = new int[4];

        for (int i = 0; i < border.length; i++) {
            if (border[i] == type) {
                result[i] = PRESENT;
            }
        }

        return result;
    }

    /**
     * Creates a matte border with thickness and color
     * selected based on border type
     *
     * @param matte border matte
     * @param type  border type
     * @return matte border
     */
    private Border createBorder(int[] matte, int type) {
        int size = thickness[type];

        for (int i = 0; i < matte.length; i++) {
            if (matte[i] != 0) {
                matte[i] += size;
            }
        }

        return BorderFactory.createMatteBorder(
                matte[0], matte[3], matte[2], matte[1], colors[type]);
    }


}
