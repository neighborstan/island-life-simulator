package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 10, 3, 100);
    }

    @Override
    public Animal reproduce() {
        return new Buffalo();
    }
}
