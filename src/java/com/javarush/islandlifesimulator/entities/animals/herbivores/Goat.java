package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 140, 3, 10);
    }

    @Override
    public Animal reproduce() {
        return new Goat();
    }
}
