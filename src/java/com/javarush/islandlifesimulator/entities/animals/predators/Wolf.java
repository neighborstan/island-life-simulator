package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;


public class Wolf extends Predator {

    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public Animal reproduce() {
        return new Wolf();
    }
}
