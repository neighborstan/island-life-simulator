package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Animal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс локации острова
 */
public class Location {
    /** Поле координата х локации */
    private int coordX;
    /** Поле координата у локации */
    private int coordY;
    /** Поле список всех сущностей локации */
    private List<Entity> entities;

    /**
     * Конструктор класса, инициализирует координаты и список сущностей локации
     * @param coordX координата х локации
     * @param coordY координата у локации
     */
    public Location(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.entities = new CopyOnWriteArrayList<>();
    }

    /**
     * Метод добавляет сущность в список сущностей локации
     * @param entity сущность (животное/растение)
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Метод удаляет сущность из списка сущностей локации
     * @param entity сущность (животное/растение)
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Геттер для поля списка сущностей локации
     * @return возвращает список сущностей локации
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Метод оставляет в списке всех сущностей тех, кто является животным
     * @return возвращает список всех животных локации
     */
    public List<Animal> getAnimals() {
        return entities.stream()
                .filter(entity -> entity instanceof Animal)
                .map(entity -> (Animal) entity)
                .toList();
    }

    /**
     * Геттер для поля координата х
     * @return возвращает значение координаты х
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Геттер для поля координата у
     * @return возвращает значение координаты у
     */
    public int getCoordY() {
        return coordY;
    }
}
