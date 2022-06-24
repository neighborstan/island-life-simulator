package com.javarush.islandlifesimulator.entities;

public abstract class Entity {
    private double weight;
    private int maxOnCage;

    public abstract double getWeight();

    public abstract int getMaxOnCage();
}
