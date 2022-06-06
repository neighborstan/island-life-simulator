package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Sheep extends Herbivore {
    private double weight = 70;
    private int maxOnCage = 140;
    private int speed = 3;
    private double enoughAmountFood = 15;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Sheep();
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
