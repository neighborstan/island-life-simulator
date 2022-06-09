package com.javarush.islandlifesimulator.settings;

/**
 * Класс содержит основные константы и изменяемые параметры приложения
 */
public class SimulationSettings {
    /** Кол-во используемых потоков */
    public static final int INITIAL_CORE_POOL_SIZE = 3;
    /** Через сколько мс стартуем первый такт жизненного цикла */
    public static final int INITIAL_DELAY_TACT_MILLIS = 100;
    /** Частота между тактами, мс */
    public static final int TACT_TIME_DELAY_MILLIS = 100;
    /** Через сколько мс стартуем задание показа статистики */
    public static final int INITIAL_DELAY_SHOW_STATS_MILLIS = 100;
    /** Через сколько мс стартуем задание роста растений */
    public static final int INITIAL_DELAY_PLANT_GROW_MILLIS = 1000;

    /** Минимальное кол-во тактов для завершения симуляции */
    public static final int MIN_LIMIT_TACT_NUMBER_TO_STOP_SIM = 5;
    /** Максимальное кол-во тактов для завершения симуляции */
    public static final int MAX_LIMIT_TACT_NUMBER_TO_STOP_SIM = 1000;
    /** Минимальная ширина карты острова */
    public static final int MIN_LIMIT_WIDTH_MAP = 5;
    /** Максимальная ширина карты острова */
    public static final int MAX_LIMIT_WIDTH_MAP = 200;
    /** Минимальная высота карты острова */
    public static final int MIN_LIMIT_HEIGHT_MAP = 5;
    /** Максимальная высота карты острова */
    public static final int MAX_LIMIT_HEIGHT_MAP = 200;
    /** Минимальное кол-во животных в локации при заполнении */
    public static final int MIN_LIMIT_INITIAL_NUMBER_OF_ANIMALS = 1;
    /** Максимальное кол-во животных в локации при заполнении */
    public static final int MAX_LIMIT_INITIAL_NUMBER_OF_ANIMALS = 50;
    /** Минимальное значение уменьшения здоровья каждый такт, % */
    public static final int MIN_LIMIT_REDUCTION_HEALTH_EVERY_TACT = 1;
    /** Максимальное значение уменьшения здоровья каждый такт, % */
    public static final int MAX_LIMIT_REDUCTION_HEALTH_EVERY_TACT = 99;
    /** Минимальное кол-во времени между ростом растений, мс */
    public static final int MIN_LIMIT_PLANT_GROW_DELAY = 1;
    /** Максимальное кол-во времени между ростом растений, мс */
    public static final int MAX_LIMIT_PLANT_GROW_DELAY = 60000;
    /** Минимальное кол-во времени между отображением статистики */
    public static final int MIN_LIMIT_STAT_PERIOD = 100;
    /** Максимальное кол-во времени между отображением статистики */
    public static final int MAX_LIMIT_STAT_PERIOD = 60000;

    /** Настраиваемый параметр ширины карты острова */
    public static int widthMap = 100;
    /** Настраиваемый параметр высоты карты острова */
    public static int heightMap = 20;
    /** Настраиваемый параметр кол-ва животных в локации при заполнении */
    public static int maxAnimalCount = 1;
    /** Настраиваемый параметр уменьшения здоровья ежетактно */
    public static double reduceHealthPercent = 30;
    /** Настраиваемый параметр увеличения здоровья */
    public static double increaseHealthPercent = 30;
    /** Настраиваемый параметр частоты роста растений */
    public static int plantGrowTime = 100;
    /** Настраиваемый параметр частоты вывода статистики в консоль */
    public static int statPeriod = 100;
    /** Настраиваемый параметр максимального номера такта для завершения симуляции */
    public static int maxNumberOfTact = 300;
}
