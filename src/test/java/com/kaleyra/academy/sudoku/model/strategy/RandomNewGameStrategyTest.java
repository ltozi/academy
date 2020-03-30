package com.kaleyra.academy.sudoku.model.strategy;

import com.kaleyra.academy.sudoku.model.GameModel;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RandomNewGameStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.RecursiveStrategy;
import com.kaleyra.academy.sudoku.model.strategy.generation.impl.SimpleGenerationStrategy;
import com.kaleyra.academy.sudoku.utils.SudokuException;
import org.junit.Test;

import java.util.*;

public class RandomNewGameStrategyTest {

    @Test(timeout = 2000)
    public void shouldGenerateRandomPuzzleOK() throws SudokuException {

        RandomNewGameStrategy strategy = new SimpleGenerationStrategy();
        GameModel model = strategy.createModel();

        System.out.println(model.toString());
    }

    @Test
    public void /*Set<Integer>*/allowedValues() {
        HashSet<Integer> allowedValues = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            allowedValues.add(i);
        }
        System.out.println(allowedValues);
        //return allowedValues;
    }

    @Test
    public void /*Integer*/ generateRandomValue() {
        //ALLOWED VALUES METHOD STARTS
        HashSet<Integer> allowedNumbers = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            allowedNumbers.add(i);
        }
        //ALLOWED VALUES METHOD ENDS
        Iterator<Integer> iter = allowedNumbers.iterator();
        ArrayList<Integer> allowedNumSet = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            allowedNumSet.add(iter.next());
        }

        while (!allowedNumSet.isEmpty()) {
            Integer num = allowedNumSet.get((int) (Math.random() * allowedNumSet.size()));
            allowedNumSet.remove(num);
            System.out.print(num);
        }
        System.out.println(0);

        //return null;
    }


}