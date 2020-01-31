package com.kaleyra.academy.sudoku.controller.states.events;

import java.util.ArrayList;
import java.util.List;

public interface SudokuEventPublisher {

    List<GameStateListener> subscribers = new ArrayList<>();

    default void subscribe(GameStateListener subscriber) {
        subscribers.add(subscriber);
    }

    default void unsubscribe(GameStateListener subscriber) {
        subscribers.remove(subscriber);
    }

    default void publishOnWin(SudokuEventPublisher publisher) {
        for (GameStateListener subscriber: subscribers) {
            subscriber.onWin(this);
        }
    }
}
