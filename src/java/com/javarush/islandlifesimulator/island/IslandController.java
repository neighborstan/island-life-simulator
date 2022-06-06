package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.animals.Action;
import com.javarush.islandlifesimulator.entities.animals.Animal;
import com.javarush.islandlifesimulator.entities.animals.Direction;
import com.javarush.islandlifesimulator.process.Processor;
import com.javarush.islandlifesimulator.settings.SimulationSettings;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class IslandController {
    public static final AtomicInteger TACT_NUMBER = new AtomicInteger(0);

    public static volatile long duration_tact = 0;

    private IslandMap map;
    private IslandStats statistics;
    private ExecutorService locationRunExecutor;

    public IslandController(IslandMap map) {
        this.map = map;
        this.statistics = new IslandStats(map);
        this.locationRunExecutor = Executors.newWorkStealingPool();
    }

    public Runnable createLifeCycleTask() {
        return () -> {
            long time = System.currentTimeMillis();

            for (int coordY = 0; coordY < map.getHeight(); coordY++) {
                for (int coordX = 0; coordX < map.getWidth(); coordX++) {

                    Location location = map.getLocations()[coordY][coordX];
                    locationRunExecutor.submit(createLocationTask(location));
                }
            }
            int currentTact = TACT_NUMBER.getAndIncrement();
            duration_tact = System.currentTimeMillis() - time;

            if (isEndLifeCycle(currentTact)) {
                stopSimulation();
            }
        };
    }

    public IslandMap getMap() {
        return map;
    }

    public IslandStats getStatistics() {
        return statistics;
    }

    private Runnable createLocationTask(Location location) {
        return () -> {
            List<Animal> animals = new CopyOnWriteArrayList<>(location.getAnimals());
            for (Animal animal : animals) {
                if (isDead(animal, location)) {
                    continue;
                }
                Action action = animal.choiceOfAction();
                doAction(action, animal, location);
            }
        };
    }

    private void stopSimulation() {
        Processor.executorService.shutdown();
    }

    private boolean isEndLifeCycle(int currentTact) {
        return currentTact >= SimulationSettings.maxNumberOfTact;

    }

    private void reduceHealth(Animal animal) {
        double healthScale = animal.getHealthScale() - ((animal.getEnoughAmountFood() * SimulationSettings.reduceHealthPercent) / 100);
        animal.setHealthScale(healthScale);
    }

    private void increaseHealth(Animal animal) {
        double healthScale = animal.getHealthScale() + ((animal.getEnoughAmountFood() * SimulationSettings.increaseHealthPercent) / 100);
        if (healthScale > animal.getHealthScale()) {
            healthScale = animal.getEnoughAmountFood();
        }
        animal.setHealthScale(healthScale);
    }

    private void doEat(Animal animal, Location location) {
        List<Entity> entities = location.getEntities();

        List<Entity> foodEntities = entities.stream()
                .filter(foodEntity -> !foodEntity.getClass().getSimpleName().equals(animal.getClass().getSimpleName()))
                .toList();

        if (foodEntities.size() > 0) {
            Entity foodEntity = foodEntities.get(ThreadLocalRandom.current().nextInt(foodEntities.size()));
            if (animal.eat(foodEntity)) {
                location.removeEntity(foodEntity);
            }
        }
    }

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

    private void doSleep(Animal animal) {
        increaseHealth(animal);
    }

    private void doAction(Action action, Animal animal, Location location) {
        switch (action) {
            case MOVE -> doMove(animal, location);
            case EAT -> doEat(animal, location);
            case REPRODUCE -> doProduce(animal, location);
            case SLEEP -> doSleep(animal);
        }
        reduceHealth(animal);
    }

    private boolean isDead(Animal animal, Location location) {
        if (animal.getHealthScale() < 0) {
            location.removeEntity(animal);
            return true;
        }
        return false;
    }

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
