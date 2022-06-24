package com.javarush.islandlifesimulator.entities.plants;

import com.javarush.islandlifesimulator.entities.Entity;

public class Plant extends Entity {
    private double weight = 1;
    private int maxOnCage = 200;


    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxOnCage() {
        return maxOnCage;
    }

}
