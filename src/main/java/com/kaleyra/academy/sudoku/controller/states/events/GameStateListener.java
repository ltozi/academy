package com.kaleyra.academy.sudoku.controller.states.events;

@FunctionalInterface
public interface GameStateListener extends SudokuEventListener{

    void onWin(SudokuEventPublisher publisher);
}