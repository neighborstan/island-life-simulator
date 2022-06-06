package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.EntityFactory;
import com.javarush.islandlifesimulator.entities.EntityType;

import java.util.concurrent.ThreadLocalRandom;

public class IslandMap {
    private int width;
    private int height;

    private Location[][] locations;

    public IslandMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];
    }

    public void initialize() {
        for (int coordY = 0; coordY < getHeight(); coordY++) {
            for (int coordX = 0; coordX < getWidth(); coordX++) {
                locations[coordY][coordX] = new Location(coordX, coordY);
            }
        }
    }

    public void fill(int maxEntityCount) {
        for (int coordY = 0; coordY < height; coordY++) {
            for (int coordX = 0; coordX < width; coordX++) {
                for (int i = 0; i <= maxEntityCount; i++) {
                    Entity entity = getRandomEntity();
                    locations[coordY][coordX].addEntity(entity);
                }
            }
        }
    }

    public Runnable createPlantGrowTask(){
        return () -> {
            int coordX = ThreadLocalRandom.current().nextInt(getWidth());
            int coordY = ThreadLocalRandom.current().nextInt(getHeight());
            Location location = locations[coordY][coordX];
            location.addEntity(EntityFactory.createAnimal(EntityType.PLANT));
        };
    }

    public Location[][] getLocations() {
        return locations;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Entity getRandomEntity() {
        EntityType[] entityTypes = EntityType.values();
        int size = entityTypes.length;
        EntityType entityType = entityTypes[ThreadLocalRandom.current().nextInt(size)];
        return EntityFactory.createAnimal(entityType);
    }
}
