package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Bear extends Predator {
    private double weight = 500;
    private int maxOnCage = 5;
    private int speed = 2;
    private double enoughAmountFood = 80;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Bear();
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
