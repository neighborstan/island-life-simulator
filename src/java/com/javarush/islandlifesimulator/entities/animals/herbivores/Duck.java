package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;


public class Duck extends Herbivore {

    public Duck() {
        super(1, 200, 4, 0.15);
    }

    @Override
    public Animal reproduce() {
        return new Duck();
    }
}
