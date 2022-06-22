package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 140, 3, 15);
    }

    @Override
    public Animal reproduce() {
        return new Sheep();
    }
}
