package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;


public class Duck extends Herbivore {
    private double weight = 1;
    private int maxOnCage = 200;
    private int speed = 4;
    private double enoughAmountFood = 0.15;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Duck();
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
