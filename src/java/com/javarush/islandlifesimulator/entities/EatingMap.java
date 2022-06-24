package com.javarush.islandlifesimulator.entities;

import java.util.Map;

/**
 * Класс управляет питанием животных
 */
public class EatingMap {
    private Map<String, Map<String, Integer>> eatableIndexes;

    public Map<String, Map<String, Integer>> getEatableIndexes() {
        return eatableIndexes;
    }
}
