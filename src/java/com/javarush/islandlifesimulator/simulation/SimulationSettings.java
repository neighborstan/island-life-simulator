package com.javarush.islandlifesimulator.simulation;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс содержит основные константы и изменяемые параметры приложения
 */
public class SimulationSettings {
    /** Путь к файлу с данными таблицы вероятностей покушать */
    public static final String PATH_TO_EATING_CHANCE_DATA = "resources/eating-chance-data.yaml";

    /** Кол-во используемых потоков */
    public static final int INITIAL_CORE_POOL_SIZE = 3;
    /** Через сколько стартуем первый такт жизненного цикла, мс */
    public static final int INITIAL_DELAY_TACT_MILLIS = 100;
    /** Частота между тактами, мс */
    public static final int TACT_TIME_DELAY_MILLIS = 100;
    /** Через сколько стартуем задание показа статистики, мс */
    public static final int INITIAL_DELAY_SHOW_STATS_MILLIS = 100;
    /** Через сколько стартуем задание роста растений, мс */
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
    /** Минимальное кол-во времени между отображением статистики, мс */
    public static final int MIN_LIMIT_STAT_PERIOD = 100;
    /** Максимальное кол-во времени между отображением статистики, мс */
    public static final int MAX_LIMIT_STAT_PERIOD = 60000;

    /** Настраиваемый параметр ширины карты острова */
    @Getter
    @Setter
    private int widthMap = 100;
    /** Настраиваемый параметр высоты карты острова */
    @Getter
    @Setter
    private int heightMap = 20;
    /** Настраиваемый параметр кол-ва животных в локации при заполнении */
    @Getter
    @Setter
    private int maxAnimalCount = 1;
    /** Настраиваемый параметр уменьшения здоровья ежетактно, % */
    @Getter
    @Setter
    private double reduceHealthPercent = 30;
    /** Настраиваемый параметр увеличения здоровья, % */
    @Getter
    @Setter
    private double increaseHealthPercent = 30;
    /** Настраиваемый параметр частоты роста растений, мс */
    @Getter
    @Setter
    private int plantGrowTime = 100;
    /** Настраиваемый параметр частоты вывода статистики в консоль, мс */
    @Getter
    @Setter
    private int statPeriod = 100;
    /** Настраиваемый параметр максимального номера такта для завершения симуляции */
    @Getter
    @Setter
    private int maxNumberOfTact = 300;
}
