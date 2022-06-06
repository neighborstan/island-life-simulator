package com.javarush.islandlifesimulator.entities.animals.predators;


import com.javarush.islandlifesimulator.entities.EatingMap;
import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Animal;

public abstract class Predator extends Animal {

    @Override
    public boolean eat(Entity foodEntity) {
        boolean isEating =  EatingMap.isEaten(this, foodEntity);

        if(isEating){
            if (foodEntity.getWeight() >= this.getEnoughAmountFood()){
                this.setHealthScale(this.getEnoughAmountFood());
            }else {
                double hungerAfterEating = this.getHealthScale() + foodEntity.getWeight();
                this.setHealthScale(hungerAfterEating);
            }
            return true;
        }
        return false;
    }
}
