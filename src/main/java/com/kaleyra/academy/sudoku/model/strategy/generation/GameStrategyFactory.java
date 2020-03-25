/**
 * NewGameStrategyManager
 */
package com.kaleyra.academy.sudoku.model.strategy.generation;

import com.kaleyra.academy.sudoku.model.strategy.generation.impl.PlainFileNewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.SimpleGenerationStrategy;
import com.kaleyra.academy.sudoku.utils.Preferences;

/**
 * Gestisce le strategie di creazione di un nuovo gioco
 */
public class GameStrategyFactory {

    /**
     * Ritorna l'oggetto che si occupa di generare un nuovo
     * gioco. Per default viene ritornato un oggetto di tipo
     * <code>RandomNewGameStrategy</code>. Se viene specificata
     * la proprietà di sistema <code>com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile</code>
     * viene invece utilizzata la strategia
     * <code>PlainFileNewGameStrategy</code>
     *
     * @return strategia di creazione nuovo gioco
     */
    public static NewGameStrategy getStrategy() {
        NewGameStrategy result = null;

//        String sudokuValidationStrategy = Preferences.getInstance().getProperty("SudokuValidationStrategy");


        System.setProperty("com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile", "true");

        String useFileString =
                System.getProperty("com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile");

        if ("true".equals(useFileString)) {
            result = new PlainFileNewGameStrategy();
        } else {
            result = new SimpleGenerationStrategy();
        }

        return result;
    }

//    public static Properties loadProperties() {
//        try {
//            File f = new File("");
//            InputStream out = new FileInputStream(f);
//            Properties props = new Properties();
//            props.load(out);
//            String sudokuValidationStrategy = props.getProperty("SudokuValidationStrategy");
//            System.out.println(sudokuValidationStrategy);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
