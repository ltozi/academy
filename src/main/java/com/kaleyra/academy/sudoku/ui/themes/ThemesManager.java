/**
 * ThemesManager
 */
package com.kaleyra.academy.sudoku.ui.themes;

/**
 * Manages themes
 */
public class ThemesManager {

    /**
     * singleton instance
     */
    private static ThemesManager instance = new ThemesManager();
    /**
     * default Theme
     */
    Theme defaultTheme;
    /**
     * selected Theme
     */
    Theme selectedTheme;
    /**
     * online themes list
     */
    Theme[] themesList;

    /**
     * Create a new manager
     */
    private ThemesManager() {
        defaultTheme = new DefaultTheme();

        themesList = new Theme[1];
        themesList[0] = defaultTheme;
    }

    /**
     * @return ThemesManager unique instance
     */
    public static ThemesManager getInstance() {
        return instance;
    }

    /**
     * @return default theme
     */
    public Theme getDefaultTheme() {
        return defaultTheme;
    }

    /**
     * @return selected theme
     */
    public Theme getSelectedTheme() {
        return selectedTheme;
    }

    /**
     * Set selected theme
     *
     * @param theme
     */
    public void setSelectedTheme(Theme theme) {
        selectedTheme = theme;
    }

    /**
     * @return themes list
     */
    public Theme[] getThemes() {
        return themesList;
    }

    /**
     * updates the themes list accessing the program
     * web site and downloading the themes briefs
     */
    public void update() {
        //TODO
    }
}
