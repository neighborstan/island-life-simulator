package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Boar extends Herbivore {
    private double weight = 400;
    private int maxOnCage = 50;
    private int speed = 2;
    private double enoughAmountFood = 50;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Boar();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxOnCage() {
        return maxOnCage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public double getEnoughAmountFood() {
        return enoughAmountFood;
    }
}
