package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.EntityFactory;
import com.javarush.islandlifesimulator.entities.EntityType;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  Класс карты острова
 */
public class IslandMap {
    /** Поле ширина карты острова */
    private int width;
    /** Поле высота карты острова */
    private int height;
    /** Поле матрица локаций карты острова */
    private Location[][] locations;

    /**
     * Конструктор класса, инициализирует ширину, высоту карты и массив локаций карты
     * @param width - ширина карты острова
     * @param height - высота карты острова
     */
    public IslandMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[height][width];
    }

    /**
     * Метод инициализации карты пустыми локациями
     */
    public void initialize() {
        for (int coordY = 0; coordY < getHeight(); coordY++) {
            for (int coordX = 0; coordX < getWidth(); coordX++) {
                locations[coordY][coordX] = new Location(coordX, coordY);
            }
        }
    }

    /**
     * Метод заполняет карту рандомными животными
     * @param maxEntityCount - максимальное кол-во животных в локации.
     */
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

    /**
     * Метод создает задание роста растения в радномной локации
     * @return возвращает задание роста растения в радномной локации
     */
    public Runnable createPlantGrowTask(){
        return () -> {
            int coordX = ThreadLocalRandom.current().nextInt(getWidth());
            int coordY = ThreadLocalRandom.current().nextInt(getHeight());
            Location location = locations[coordY][coordX];
            location.addEntity(EntityFactory.createAnimal(EntityType.PLANT));
        };
    }

    /**
     * Геттер для массива локаций на карте
     * @return возвращает матрицу локаций
     */
    public Location[][] getLocations() {
        return locations;
    }

    /**
     * Геттер для ширины карты острова
     * @return возвращает значение ширины карты
     */
    public int getWidth() {
        return width;
    }

    /**
     * Геттер для высоты карты острова
     * @return возвращает значение высоты карты
     */
    public int getHeight() {
        return height;
    }

    /**
     * Метод создает рандомное животное/растение
     * @return возвращает рандомную сущность
     */
    private Entity getRandomEntity() {
        EntityType[] entityTypes = EntityType.values();
        int size = entityTypes.length;
        EntityType entityType = entityTypes[ThreadLocalRandom.current().nextInt(size)];
        return EntityFactory.createAnimal(entityType);
    }
}
