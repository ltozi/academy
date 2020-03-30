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
	 * Ritorna l'oggetto che si occupa di generare un nuovo gioco. Per default viene
	 * ritornato un oggetto di tipo <code>RandomNewGameStrategy</code>. Se viene
	 * specificata la proprietà di sistema
	 * <code>com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile</code>
	 * viene invece utilizzata la strategia <code>PlainFileNewGameStrategy</code>
	 *
	 * @return strategia di creazione nuovo gioco
	 */
	public static NewGameStrategy getStrategy() {

		String gameStrategy = "";
		String useFileString = System.getProperty("com.kaleyra.academy.sudoku.model.NewGameStrategyManager.useFile");
        System.out.println(" Enter NewGameStrategy 1a ");
		if ("true".equals(useFileString))
		    gameStrategy = Preferences.getInstance().getProperty(Preferences.GAME_STRATEGY);
		System.out.println(gameStrategy);

		switch (gameStrategy) {
		case "RANDOM":
			return new SimpleGenerationStrategy();
		default:
			return new PlainFileNewGameStrategy();
		}
	}
}
