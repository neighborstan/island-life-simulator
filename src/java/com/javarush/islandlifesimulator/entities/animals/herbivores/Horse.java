package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 20, 4, 60);
    }

    @Override
    public Animal reproduce() {
        return new Horse();
    }
}
