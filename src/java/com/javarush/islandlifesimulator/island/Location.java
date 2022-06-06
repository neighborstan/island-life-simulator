package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Animal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Location {
    private int coordX;
    private int coordY;
    private List<Entity> entities;

    public Location(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.entities = new CopyOnWriteArrayList<>();
    }

    public synchronized void addEntity(Entity entity) {
        entities.add(entity);
    }

    public synchronized void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Animal> getAnimals() {
        return entities.stream()
                .filter(entity -> entity instanceof Animal)
                .map(entity -> (Animal) entity)
                .toList();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}
