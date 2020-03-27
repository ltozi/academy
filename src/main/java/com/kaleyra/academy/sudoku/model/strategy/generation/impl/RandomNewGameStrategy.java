/**
 * RandomNewGameStrategy
 */
package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.NewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;
import com.kaleyra.academy.sudoku.model.strategy.validation.impl.DefaultValidationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Crea un gioco utilizzando numeri casuali
 */
public abstract class RandomNewGameStrategy implements NewGameStrategy {

	private SudokuValidationStrategy validationStrategy = new DefaultValidationStrategy();
	private static Random r = new Random();

	/**
	 * Template method
	 *
	 * @return un gioco generato casualmente
	 */
	public GameModel createModel() throws SudokuException {
		GameModel gameModel = new GameModel();

		int[][] matrix = gameModel.getData();

		for (int row = 0; row < matrix.length; row++) {
			Set<Integer> alreadyUsedValues = new HashSet<>(); // Empty
			for (Integer col = 0; col < matrix[row].length; col++) {
				CellValueFinder valueFinder = new CellValueFinder(matrix, row, col, alreadyUsedValues).build();
				row = valueFinder.row;
				col = valueFinder.col;
				matrix[row][col] = valueFinder.random;
			}
		}

		if (!validationStrategy.isValidModel(matrix)) {
			throw new SudokuException("Wrong random model generation: " + "\n" + gameModel.toString());
		}

		return setGameDifficulty(gameModel);
	}

	/**
	 * Subclass must define a way to hide cells handling game difficulty
	 *
	 * @return
	 */
	public abstract GameModel setGameDifficulty(final GameModel model);

	/**
	 *
	 * @return a Set with all the number available
	 */
	public Set<Integer> allowedValues() {
		List<Integer> aList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		return new HashSet<Integer>(aList);
	}

	/**
	 * @param allowedNumbers is decreased in size on each extraction
	 *
	 * @return a Set with all the number available
	 */
	public Integer generateRandomValue(Set<Integer> allowedNumbers) {
		int selectedIndex = r.nextInt(allowedNumbers.size());

		int i = 0;
		for (Integer iter : allowedNumbers) {
			if (i == selectedIndex) {
				allowedNumbers.remove(iter);
				return iter;
			}
			i++;
		}
		return 0;
	}

	/**
	 * A helper class that encapsulate logic to find a dynamic value for each cell
	 */
	public class CellValueFinder {
		private int[][] matrix;
		private Integer row;
		private Set<Integer> allowedNumbers = allowedValues();
		private Set<Integer> alreadyUsedValues;
		private Integer col;
		private Integer random;

		CellValueFinder(int[][] matrix, Integer row, Integer col, Set<Integer> alreadyUsedValues) {
			this.matrix = matrix;
			this.row = row;
			this.alreadyUsedValues = alreadyUsedValues;
			this.col = col;
		}

		CellValueFinder build() {
			do {
				random = generateRandomValue(allowedNumbers);

				if (random == null) {// This happens because elements are escaped
					allowedNumbers = allowedValues();

					goBackOneCell();

					alreadyUsedValues.add(matrix[row][col]);
					allowedNumbers.removeAll(alreadyUsedValues); // Exclude already used numbers
					matrix[row][col] = 0;//
					random = generateRandomValue(allowedNumbers); // Generate

					if (random == null) {
						allowedNumbers = allowedValues();
						alreadyUsedValues.removeAll(allowedNumbers); // Try again every number
						random = generateRandomValue(allowedNumbers);
					}
				}
			} while (!validationStrategy.isValidMove(matrix, row, col, random));

			return this;
		}

		private void goBackOneCell() {
			if (col > 0)
				col--;
			else {
				row--;
				col = matrix.length; // Backtracking till correct solutions exists
			}
		}
	}
}
