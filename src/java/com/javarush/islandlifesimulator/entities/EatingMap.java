package com.javarush.islandlifesimulator.entities;

import com.javarush.islandlifesimulator.entities.animals.Animal;

import java.util.concurrent.ThreadLocalRandom;

public class EatingMap {
    //TODO вытащить в настройки
    private static final int MAX_EATABLE_INDEX = 100;

    /**
     * В таблице приведено, с какой вероятностью животное съедает "пищу", если они находятся на одной клетке.
     * Строки - типы животных в порядке, котором они перечислены в enum EntityType
     * Столбцы - отсчет слева, типы животных в порядке, котором они перечислены в enum EntityType
     */
    private static final int[][] EATABLE_INDEXES = {
            {0,  0,   0,  0, 0, 10,  15,  60,  80,  60,  70,  15,  10,  40,   0,    0},
            {0,  0,  15,  0, 0,  0,   0,  20,  40,   0,   0,   0,   0,  10,   0,    0},
            {0,  0,   0,  0, 0,  0,   0,  70,  90,   0,   0,   0,   0,  60,  40,    0},
            {0, 80,   0,  0, 0, 40,  80,  80,  90,  70,  70,  50,  20,  10,   0,    0},
            {0,  0,  10,  0, 0,  0,   0,  90,  90,   0,   0,   0,   0,  80,   0,    0},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,  90,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,  50,   0,   0,   0,   0,   0,  90,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,  90,  100},
            {0,  0,   0,  0, 0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,  100}};


    public static boolean isEaten(Animal hungryAnimal, Entity foodEntity){
        int hungryAnimalIndex = EntityType.valueOf(hungryAnimal.getClass().getSimpleName().toUpperCase()).ordinal();
        int foodEntityIndex = EntityType.valueOf(foodEntity.getClass().getSimpleName().toUpperCase()).ordinal();

        int probabilityOfEating = EATABLE_INDEXES[hungryAnimalIndex][foodEntityIndex];

        return ThreadLocalRandom.current().nextInt(MAX_EATABLE_INDEX) < probabilityOfEating;
    }
}
