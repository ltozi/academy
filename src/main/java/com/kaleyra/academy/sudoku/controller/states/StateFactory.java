/**
 * StateFactory
 */
package com.kaleyra.academy.sudoku.controller.states;

/**
 * Factory di creazione di oggetti stato, utilizza
 * flyweight per riutilizzare gli oggetti stato senza
 * crearli ogni volta
 *
 * @pattern Abstract Factory, Flyweight
 */
public class StateFactory {

    /**
     * stato "nessun gioco in corso"
     */
    private static NoGameState noGameState = new NoGameState();

    /**
     * stato "gioco in pausa"
     */
    private static PausedGameState pausedGameState = new PausedGameState();

    /**
     * stato "gioco in corso
     */
    private static PlayingGameState playingGameState = new PlayingGameState();

    /**
     * @return lo stato "nessun gioco in corso"
     */
    public static GameState createNoGameState() {
        return noGameState;
    }

    /**
     * @return lo stato "in pausa"
     */
    public static GameState createPausedGameState() {
        return pausedGameState;
    }

    /**
     * @return lo stato "gioco in corso"
     */
    public static GameState createPlayingGameState() {
        return playingGameState;
    }

}
