package com.javarush.islandlifesimulator.settings;

public class SimulationSettings {
    public static final int INITIAL_CORE_POOL_SIZE = 3;
    public static final int INITIAL_DELAY_TACT_MILLIS = 100;
    public static final int TACT_TIME_DELAY_MILLIS = 100;
    public static final int INITIAL_DELAY_SHOW_STATS_MILLIS = 100;
    public static final int INITIAL_DELAY_PLANT_GROW_MILLIS = 1000;

    public static final int MIN_LIMIT_TACT_NUMBER_TO_STOP_SIM = 5;
    public static final int MAX_LIMIT_TACT_NUMBER_TO_STOP_SIM = 1000;
    public static final int MIN_LIMIT_WIDTH_MAP = 5;
    public static final int MAX_LIMIT_WIDTH_MAP = 200;
    public static final int MIN_LIMIT_HEIGHT_MAP = 5;
    public static final int MAX_LIMIT_HEIGHT_MAP = 200;
    public static final int MIN_LIMIT_INITIAL_NUMBER_OF_ANIMALS = 1;
    public static final int MAX_LIMIT_INITIAL_NUMBER_OF_ANIMALS = 50;
    public static final int MIN_LIMIT_REDUCTION_HEALTH_EVERY_TACT = 1;
    public static final int MAX_LIMIT_REDUCTION_HEALTH_EVERY_TACT = 99;
    public static final int MIN_LIMIT_PLANT_GROW_DELAY = 1;
    public static final int MAX_LIMIT_PLANT_GROW_DELAY = 60000;
    public static final int MIN_LIMIT_STAT_PERIOD = 100;
    public static final int MAX_LIMIT_STAT_PERIOD = 60000;

    public static int widthMap = 100;
    public static int heightMap = 20;
    public static int maxAnimalCount = 1;
    public static double reduceHealthPercent = 30;
    public static double increaseHealthPercent = 30;
    public static int plantGrowTime = 100;
    public static int statPeriod = 100;
    public static int maxNumberOfTact = 300;
}
