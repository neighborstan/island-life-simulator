package com.javarush.islandlifesimulator.entities.animals.herbivores;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Animal;


public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxOnCage, int speed, double enoughAmountFood) {
        super(weight, maxOnCage, speed, enoughAmountFood);
    }

    @Override
    public void eat(Entity foodEntity) {
        if (foodEntity.getWeight() >= this.getEnoughAmountFood()){
            this.setHealthScale(this.getEnoughAmountFood());
        }else {
            double hungerAfterEating = this.getHealthScale() + foodEntity.getWeight();
            this.setHealthScale(hungerAfterEating);
        }
    }
}
