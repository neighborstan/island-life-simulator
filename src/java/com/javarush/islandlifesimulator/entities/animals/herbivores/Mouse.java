package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;


public class Mouse extends Herbivore {

    public Mouse() {
        super(0.05, 500, 1, 0.01);
    }

    @Override
    public Animal reproduce() {
        return new Mouse();
    }
}
