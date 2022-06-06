package com.javarush.islandlifesimulator.island;

import com.javarush.islandlifesimulator.entities.Entity;
import com.javarush.islandlifesimulator.entities.EntityType;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.islandlifesimulator.dialog.DialogText.EMOJI_KEY_COUNT_VALUE;
import static com.javarush.islandlifesimulator.dialog.DialogText.TACT_STATS;

public class IslandStats {
    private IslandMap islandMap;

    public IslandStats(IslandMap islandMap) {
        this.islandMap = islandMap;
    }

    public Runnable createShowStatsTask() {
        return () -> printStats(collectStats());
    }

    private Map<String, Integer> collectStats() {
        Map<String, Integer> entitiesStats = new ConcurrentHashMap<>();

        for (int y = 0; y < islandMap.getHeight(); y++) {
            for (int x = 0; x < islandMap.getWidth(); x++) {
                Location location = islandMap.getLocations()[y][x];

                List<Entity> entities = location.getEntities();

                for (Entity entity : entities) {
                    String entityAsString = entity.getClass().getSimpleName();
                    String entityAsImage = EntityType.valueOf(entityAsString.toUpperCase()).getUnicodeSymbol();

                    if (!entitiesStats.containsKey(entityAsImage)) {
                        entitiesStats.put(entityAsImage, 1);
                    } else {
                        entitiesStats.put(entityAsImage, entitiesStats.get(entityAsImage) + 1);
                    }
                }
            }
        }
        return entitiesStats;
    }

    private void printStats(Map<String, Integer> entitiesStatistics) {
        clearConsole();
        System.out.println(MessageFormat.format(TACT_STATS, IslandController.TACT_NUMBER.get(), IslandController.duration_tact));
//        System.out.println();
        entitiesStatistics.forEach((key, value) -> {
            System.out.println(MessageFormat.format(EMOJI_KEY_COUNT_VALUE, key, value));
        });
        System.out.println("\n");
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
