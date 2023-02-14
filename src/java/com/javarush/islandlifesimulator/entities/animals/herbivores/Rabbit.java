package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 15, 2, 0.45);
    }

    @Override
    public Animal reproduce() {
        return new Rabbit();
    }
}
