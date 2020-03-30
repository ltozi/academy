package com.kaleyra.academy.sudoku.model.strategy.validation.impl;

import com.kaleyra.academy.sudoku.model.strategy.validation.SudokuValidationStrategy;

public class DefaultValidationStrategy implements SudokuValidationStrategy {

	@Override
	public boolean isValidMove(int[][] model, int row, int col, int value) {
		return isValidForColumn(model, row, col, value) && isValidForRow(model, row, col, value)
				&& isValidForQuadrant(model, row, col, value);
	}

	public boolean isValidForQuadrant(int[][] matrix, int rowForValue, int colForValue, int value) {
		int startI = rowForValue - rowForValue % 3;
		int startJ = colForValue - colForValue % 3;
		String box = "";
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (startI + i == rowForValue && startJ + j == colForValue)
					continue;
				box += matrix[startI + i][startJ + j];
			}
		return !box.replaceAll("0", "").contains("" + value);
	}

	public boolean isValidForRow(int[][] matrix, int rowForValue, int colForValue, int value) {
		String row = "";
		for (int i = 0; i < 9; i++) {
			if (i == colForValue)
				continue;
			row += matrix[rowForValue][i];
		}
		return !row.replaceAll("0", "").contains("" + value);
	}

	public boolean isValidForColumn(int[][] matrix, int rowForValue, int colForValue, int value) {

		String col = "";
		for (int i = 0; i < 9; i++) {
			if (i == rowForValue)
				continue;
			col += matrix[i][colForValue];
		}
		return !col.replaceAll("0", "").contains("" + value);
	}

	public boolean isValidModel(int[][] model) {
		boolean cond = true;
		int sumRow;
		int sumCol;

		for (int i = 0; i < 9; i++) {
			sumRow = 0;
			sumCol = 0;
			for (int j = 0; j < 9; j++) {
				cond &= isValidMove(model, i, j, model[i][j]);
				sumRow += model[i][j];
				sumCol += model[j][i];
			}
			cond = cond && (sumRow == 45) && (sumCol == 45);
		}
		return cond;
	}
}
