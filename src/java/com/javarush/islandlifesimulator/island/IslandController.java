package com.javarush.islandlifesimulator.island;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.islandlifesimulator.entities.EatingMap;
import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Action;
import com.javarush.islandlifesimulator.entities.animals.Animal;
import com.javarush.islandlifesimulator.entities.animals.Direction;
import com.javarush.islandlifesimulator.process.Processor;
import com.javarush.islandlifesimulator.settings.SimulationSettings;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс обеспечивает управление картой острова
 */
public class IslandController {
    /** Поле номер текущего такта жизненного цикла острова */
    public static final AtomicInteger TACT_NUMBER = new AtomicInteger(0);
    /** Индекс максимальной вероятности животного покушать */
    private static final int MAX_EATABLE_INDEX = 100;
    /** Поле карта острова */
    private IslandMap map;
    /** Объект класса с данными вероятностей животных покушать */
    private EatingMap eatingMap;
    /** Поле статистика по острову */
    private IslandStats statistics;
    /** Поле сервис исполнения заданий по каждой локации острова  */
    private ExecutorService locationRunExecutor;

    /**
     * Конструктор класса
     * @param map объект карты острова
     */
    public IslandController(IslandMap map) {
        this.map = map;
        this.statistics = new IslandStats(map);
        this.locationRunExecutor = Executors.newWorkStealingPool();
        this.eatingMap = initEatingChanceData();
    }

    /**
     * Метод создает задание для запуска жизненного цикла острова.
     * Запускает внутри себя задание по каждой локации острова
     * @return возвращает задание для запуска жизненного цикла острова
     */
    public Runnable createLifeCycleTask() {
        return () -> {

            for (int coordY = 0; coordY < map.getHeight(); coordY++) {
                for (int coordX = 0; coordX < map.getWidth(); coordX++) {

                    Location location = map.getLocations()[coordY][coordX];
                    locationRunExecutor.submit(createLocationTask(location));
                }
            }
            int currentTact = TACT_NUMBER.getAndIncrement();

            if (isEndLifeCycle(currentTact)) {
                stopSimulation();
            }
        };
    }

    /**
     * Геттер для поля карта острова
     * @return возвращает объект карты острова
     */
    public IslandMap getMap() {
        return map;
    }

    /**
     * Геттер для поля статистика по острову
     * @return возвращает объект статистики
     */
    public IslandStats getStatistics() {
        return statistics;
    }

    /**
     * Метод создает задание для выполнения действий сущностями в каждой локации
     * @param location отдельная локация карты острова
     * @return возвращает задание для выполнения действий сущностями в каждой локации
     */
    private Runnable createLocationTask(Location location) {
        return () -> {
            List<Animal> animals = new CopyOnWriteArrayList<>(location.getAnimals());
            for (Animal animal : animals) {
                if (isDead(animal)) {
                    location.removeEntity(animal);
                    continue;
                }
                Action action = animal.choiceOfAction();
                doAction(action, animal, location);
            }
        };
    }

    /**
     * Метод останавливает симуляцию жизненного цикла острова
     */
    private void stopSimulation() {
        Processor.executorService.shutdown();
    }

    /**
     * Метод проверяет жизненный цикл на возможность завершения
     * @param currentTact номер текущего такта жизненного цикла
     * @return возвращает true, если текущий такт больше или равен максимальному
     */
    private boolean isEndLifeCycle(int currentTact) {
        return currentTact >= SimulationSettings.maxNumberOfTact;
    }

    /**
     * Метод уменьшает уровень здоровья животного на N процентов
     * @param animal животное, у которого уменьшается уровень здоровья
     */
    private void reduceHealth(Animal animal) {
        double healthScale = animal.getHealthScale() - ((animal.getEnoughAmountFood() * SimulationSettings.reduceHealthPercent) / 100);
        animal.setHealthScale(healthScale);
    }

    /**
     * Метод увеличивает уровень здоровья животного на N процентов
     * @param animal животоное, у которого увеличивается уровень здоровья
     */
    private void increaseHealth(Animal animal) {
        double healthScale = animal.getHealthScale() + ((animal.getEnoughAmountFood() * SimulationSettings.increaseHealthPercent) / 100);
        if (healthScale > animal.getHealthScale()) {
            healthScale = animal.getEnoughAmountFood();
        }
        animal.setHealthScale(healthScale);
    }

    /**
     * Метод выполняет действие Кушать, если есть пища в локации
     * @param animal животное, которое пытается покушать
     * @param location текущая локация
     */
    private void doEat(Animal animal, Location location) {
        List<Entity> entities = location.getEntities();

        List<Entity> foodEntities = entities.stream()
                .filter(foodEntity -> !foodEntity.getClass().getSimpleName().equals(animal.getClass().getSimpleName()))
                .toList();

        if (foodEntities.size() > 0) {
            Entity foodEntity = foodEntities.get(ThreadLocalRandom.current().nextInt(foodEntities.size()));

            if (isEaten(animal, foodEntity)) {
                animal.eat(foodEntity);
                location.removeEntity(foodEntity);
            }
        }
    }

    /**
     * Метод выполняет действие Размножение, если есть пара в локации
     * @param animal животное, которое пытается размножиться
     * @param location текущая локация
     */
    private void doProduce(Animal animal, Location location) {
        List<Animal> animals = location.getAnimals();

        List<Animal> sameAnimalType = animals.stream()
                .filter(animalType -> animalType.getClass().getSimpleName().equals(animal.getClass().getSimpleName()))
                .toList();

        if (sameAnimalType.size() > 1) {
            Animal newAnimal = animal.reproduce();
            location.addEntity(newAnimal);
        }
    }

    /**
     * Метод выполняет действие Двигаться в рандомном направлении на величину не более чем N шагов
     * @param animal животное, которое пытается двигаться
     * @param location текущая локация
     */
    private void doMove(Animal animal, Location location) {
        int stepsCount = ThreadLocalRandom.current().nextInt(animal.getSpeed() + 1);

        while (stepsCount > 0) {
            Direction direction = animal.choiceOfDirection();
            switch (direction) {
                case DOWN -> location = stepDown(animal, location);
                case UP -> location = stepUp(animal, location);
                case LEFT -> location = stepLeft(animal, location);
                case RIGHT -> location = stepRight(animal, location);
            }
            stepsCount--;
        }
    }

    /**
     * Метод выполняет действие Спать с увеличение уровня здоровья
     * @param animal животное, которое пытается спать
     */
    private void doSleep(Animal animal) {
        increaseHealth(animal);
    }

    /**
     * Метод выполняет одно из действий для животного в локации с последующим уменьшением уровня здоровья
     * @param action действие которое совершает животное
     * @param animal животное, совершающее действие
     * @param location текущая локация
     */
    private void doAction(Action action, Animal animal, Location location) {
        switch (action) {
            case MOVE -> doMove(animal, location);
            case EAT -> doEat(animal, location);
            case REPRODUCE -> doProduce(animal, location);
            case SLEEP -> doSleep(animal);
        }
        reduceHealth(animal);
    }

    /**
     * Метод проверяет достаточный ли уровень здоровья для жизни животного
     * @param animal проверяемое животное
     * @return возвращает true если животное умерло
     */
    private boolean isDead(Animal animal) {
        return animal.getHealthScale() < 0;
    }

    /**
     * Метод определяет возможность животного выполнить действие Покушать в отношении другой сущности
     * @param hungryAnimal животное, которое пытается покушать
     * @param foodEntity сущность (животное/растение), которое является пищей
     * @return возвращает true, если вероятность покушать осуществляется
     */
    private boolean isEaten(Animal hungryAnimal, Entity foodEntity){
        int probabilityOfEating = getEatableChanceIndex(hungryAnimal, foodEntity);

        return ThreadLocalRandom.current().nextInt(MAX_EATABLE_INDEX) < probabilityOfEating;
    }

    /**
     * Метод генерирует объет карты вероятностей животных покушать из файла с данными eating-chance-data.yaml
     * @return возвращает объект карты вероятностей
     */
    private EatingMap initEatingChanceData() {
        ObjectMapper mapper = new YAMLMapper();
        EatingMap eatingMap = null;
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(SimulationSettings.PATH_TO_EATING_CHANCE_DATA)) {
            eatingMap = mapper.readValue(inputStream, EatingMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eatingMap;
    }

    /**
     * Метод определяет значение веротяности животного покушать по данным из файла
     * @param hungryAnimal голодное животное
     * @param foodEntity пища
     * @return возвращает значение вероятности
     */
    private Integer getEatableChanceIndex(Animal hungryAnimal, Entity foodEntity){
        Map<String, Integer> map = eatingMap.getEatableIndexes().get(hungryAnimal.getClass().getSimpleName());
        return map.get(foodEntity.getClass().getSimpleName());
    }

    /**
     * Метод двигает животное по карте на шаг вниз если такая возможность есть
     * @param animal передвигаемое животное
     * @param currentLocation текущая локация
     * @return возвращает новую локацию животного либо прежнюю если нет возможности двигаться вниз
     */
    private Location stepDown(Animal animal, Location currentLocation) {
        int currentX = currentLocation.getCoordX();
        int currentY = currentLocation.getCoordY();
        if (currentY < map.getHeight() - 1) {
            Location newLocation = map.getLocations()[currentY + 1][currentX];
            newLocation.addEntity(animal);
            currentLocation.removeEntity(animal);
            return newLocation;
        }
        return currentLocation;
    }

    /**
     * Метод двигает животное по карте на шаг вверх если такая возможность есть
     * @param animal передвигаемое животное
     * @param currentLocation текущая локация
     * @return возвращает новую локацию животного либо прежнюю если нет возможности двигаться вверх
     */
    private Location stepUp(Animal animal, Location currentLocation) {
        int currentX = currentLocation.getCoordX();
        int currentY = currentLocation.getCoordY();
        if (currentY > 0) {
            Location newLocation = map.getLocations()[currentY - 1][currentX];
            newLocation.addEntity(animal);
            currentLocation.removeEntity(animal);
            return newLocation;
        }
        return currentLocation;
    }

    /**
     * Метод двигает животное по карте на шаг влево если такая возможность есть
     * @param animal передвигаемое животное
     * @param currentLocation текущая локация
     * @return возвращает новую локацию животного либо прежнюю если нет возможности двигаться влево
     */
    private Location stepLeft(Animal animal, Location currentLocation) {
        int currentX = currentLocation.getCoordX();
        int currentY = currentLocation.getCoordY();
        if (currentX > 0) {
            Location newLocation = map.getLocations()[currentY][currentX - 1];
            newLocation.addEntity(animal);
            currentLocation.removeEntity(animal);
            return newLocation;
        }
        return currentLocation;
    }

    /**
     * Метод двигает животное по карте на шаг вправо если такая возможность есть
     * @param animal передвигаемое животное
     * @param currentLocation текущая локация
     * @return возвращает новую локацию животного либо прежнюю если нет возможности двигаться вправо
     */
    private Location stepRight(Animal animal, Location currentLocation) {
        int currentX = currentLocation.getCoordX();
        int currentY = currentLocation.getCoordY();
        if (currentX < map.getWidth() - 1) {
            Location newLocation = map.getLocations()[currentY][currentX + 1];
            newLocation.addEntity(animal);
            currentLocation.removeEntity(animal);
            return newLocation;
        }
        return currentLocation;
    }
}
