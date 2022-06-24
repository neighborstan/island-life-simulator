package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Deer extends Herbivore {

    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public Animal reproduce() {
        return new Deer();
    }
}
