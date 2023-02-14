package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Snake extends Predator {

    public Snake() {
        super(15, 30, 1, 3);
    }

    @Override
    public Animal reproduce() {
        return new Snake();
    }
}
