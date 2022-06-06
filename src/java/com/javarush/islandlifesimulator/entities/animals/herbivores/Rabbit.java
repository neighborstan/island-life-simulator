package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.animals.Animal;

public class Rabbit extends Herbivore {
    private double weight = 2;
    private int maxOnCage = 15;
    private int speed = 2;
    private double enoughAmountFood = 0.45;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    @Override
    public Animal reproduce() {
        return new Rabbit();
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
