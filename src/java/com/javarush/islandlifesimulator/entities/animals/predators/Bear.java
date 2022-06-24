package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Bear extends Predator {

    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public Animal reproduce() {
        return new Bear();
    }
}
