package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 1000, 0, 0.0033);
    }

    @Override
    public Animal reproduce() {
        return new Caterpillar();
    }
}
