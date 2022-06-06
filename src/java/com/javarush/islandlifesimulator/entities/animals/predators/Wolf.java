package com.javarush.islandlifesimulator.entities.animals.predators;

import com.javarush.islandlifesimulator.entities.animals.Animal;


public class Wolf extends Predator {
    private double weight = 50;
    private int maxOnCage = 30;
    private int speed = 3;
    private double enoughAmountFood = 8;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Wolf();
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
