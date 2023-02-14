package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Boar extends Herbivore {

    public Boar() {
        super(400, 50, 2, 50);
    }

    @Override
    public Animal reproduce() {
        return new Boar();
    }
}
