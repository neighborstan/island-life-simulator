package com.javarush.islandlifesimulator.process;

import com.javarush.islandlifesimulator.dialog.UserDialog;
import com.javarush.islandlifesimulator.island.IslandController;
import com.javarush.islandlifesimulator.island.IslandMap;
import com.javarush.islandlifesimulator.settings.SimulationSettings;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Processor {
    public static ScheduledExecutorService executorService;

    private UserDialog dialog;
    private IslandMap map;
    private IslandController controller;


    public Processor() {
        this.dialog = new UserDialog();
        this.map = new IslandMap(SimulationSettings.widthMap, SimulationSettings.heightMap);
        this.controller = new IslandController(map);

        executorService = Executors.newScheduledThreadPool(SimulationSettings.INITIAL_CORE_POOL_SIZE);
    }

    public void start() {
        controller.getMap().initialize();
        controller.getMap().fill(SimulationSettings.maxAnimalCount);

        executorService.scheduleWithFixedDelay(controller.getStatistics().createShowStatsTask(),
                SimulationSettings.INITIAL_DELAY_SHOW_STATS_MILLIS,
                SimulationSettings.statPeriod,
                TimeUnit.MILLISECONDS);

        executorService.scheduleWithFixedDelay(controller.createLifeCycleTask(),
                SimulationSettings.INITIAL_DELAY_TACT_MILLIS,
                SimulationSettings.TACT_TIME_DELAY_MILLIS,
                TimeUnit.MILLISECONDS);

        executorService.scheduleWithFixedDelay(controller.getMap().createPlantGrowTask(),
                SimulationSettings.INITIAL_DELAY_PLANT_GROW_MILLIS,
                SimulationSettings.plantGrowTime,
                TimeUnit.MILLISECONDS);
    }
}
