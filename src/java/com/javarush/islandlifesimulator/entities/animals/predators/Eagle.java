package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Eagle extends Predator {

    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public Animal reproduce() {
        return new Eagle();
    }
}
