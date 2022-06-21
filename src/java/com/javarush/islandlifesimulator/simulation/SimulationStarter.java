package com.javarush.islandlifesimulator.simulation;

import com.javarush.islandlifesimulator.dialog.UserDialog;
import com.javarush.islandlifesimulator.island.IslandController;
import com.javarush.islandlifesimulator.island.IslandMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Класс обеспечивает запуск симуляции в разных потоках
 */
public class SimulationStarter {
    /** Поле запуск заданий по расписанию */
    public static ScheduledExecutorService executorService;

    /** Поле диалог с пользователем */
    private UserDialog dialog;
    /** Поле карта острова */
    private IslandMap map;
    /** Поле управление островом */
    private IslandController controller;
    /** Поле настройки симуляции */
    private SimulationSettings settings;

    /**
     * Конструктор класса, инициализирует поле настроек симуляции, поле диалога с пользователем,
     * поле карты острова, поле управления островом, поле запуска заданий по расписанию
     */
    public SimulationStarter() {
        this.settings = new SimulationSettings();
        this.dialog = new UserDialog(settings);
        this.map = new IslandMap(settings.getWidthMap(), settings.getHeightMap());
        this.controller = new IslandController(map, settings);

        executorService = Executors.newScheduledThreadPool(SimulationSettings.INITIAL_CORE_POOL_SIZE);
    }

    /**
     * Метод стартует основные процессы для запуска симуляции:
     * инициализация карты, ее заполнение, запуск заданий в потоках
     */
    public void start() {
        controller.getMap().initialize();
        controller.getMap().fill(settings.getMaxAnimalCount());

        executorService.scheduleWithFixedDelay(controller.getStatistics().createShowStatsTask(),
                SimulationSettings.INITIAL_DELAY_SHOW_STATS_MILLIS,
                settings.getStatPeriod(),
                TimeUnit.MILLISECONDS);

        executorService.scheduleWithFixedDelay(controller.createLifeCycleTask(),
                SimulationSettings.INITIAL_DELAY_TACT_MILLIS,
                SimulationSettings.TACT_TIME_DELAY_MILLIS,
                TimeUnit.MILLISECONDS);

        executorService.scheduleWithFixedDelay(controller.getMap().createPlantGrowTask(),
                SimulationSettings.INITIAL_DELAY_PLANT_GROW_MILLIS,
                settings.getPlantGrowTime(),
                TimeUnit.MILLISECONDS);
    }
}
