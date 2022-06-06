package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Snake extends Predator {
    private double weight = 15;
    private int maxOnCage = 30;
    private int speed = 1;
    private double enoughAmountFood = 3;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Snake();
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
