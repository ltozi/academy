package com.kaleyra.academy.sudoku.model.strategy.generation.impl;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RandomNewGameStrategyTest {

    @Test
    public void shouldGenerateRandomValue() {

        SimpleGenerationStrategy simpleGenerationStrategy = new SimpleGenerationStrategy();
        Set<Integer> immutableSet = simpleGenerationStrategy.allowedValues();

        Set<Integer> numberSet = simpleGenerationStrategy.allowedValues();

        Integer aValue = simpleGenerationStrategy.generateRandomValue(numberSet);

        assertNotNull(aValue);
        assertEquals(8, numberSet.size());
        assertTrue(immutableSet.contains(aValue));

    }

    @Test
    public void shouldReturnNullWhenNoMoreNumbersInsideList() {

        SimpleGenerationStrategy simpleGenerationStrategy = new SimpleGenerationStrategy();
        Set<Integer> numberSet = new HashSet<>();

        Integer aValue = simpleGenerationStrategy.generateRandomValue(numberSet);

        assertNull(aValue);
        assertEquals(0, numberSet.size());

    }

    @Test
    public void shouldAlwaysReturnDifferentItems() {

        SimpleGenerationStrategy simpleGenerationStrategy = new SimpleGenerationStrategy();
        Set<Integer> numberSet = simpleGenerationStrategy.allowedValues();

        Integer aValue = simpleGenerationStrategy.generateRandomValue(numberSet);
        Integer nextValue = simpleGenerationStrategy.generateRandomValue(numberSet);

        assertNotEquals(aValue, nextValue);

    }

}