package com.javarush.islandlifesimulator.entities.animals;

import com.javarush.islandlifesimulator.entities.Entity;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity {
    //TODO вытащить в настройки
    private static final int MAX_ACTION_CHANCE_INDEX = 100;

    private double weight;
    private int maxOnCage;
    private int speed;
    private double enoughAmountFood;

    private double healthScale = enoughAmountFood;

    public double getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(double healthScale) {
        this.healthScale = healthScale;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxOnCage() {
        return maxOnCage;
    }

    public int getSpeed() {
        return speed;
    }

    public double getEnoughAmountFood() {
        return enoughAmountFood;
    }

    public abstract void eat(Entity entity);

    public abstract Animal reproduce();

    public Direction choiceOfDirection(){
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    public Action choiceOfAction(){
        Action action = Action.values()[ThreadLocalRandom.current().nextInt(Action.values().length)];

        boolean isActiveAction = ThreadLocalRandom.current().nextInt(MAX_ACTION_CHANCE_INDEX) < action.getActionChanceIndex();

        if(isActiveAction){
            return action;
        }
        return Action.SLEEP;
    }
}
