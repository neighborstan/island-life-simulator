package com.javarush.islandlifesimulator.dialog;

import com.javarush.islandlifesimulator.settings.SimulationSettings;

import java.text.MessageFormat;
import java.util.Scanner;

import static com.javarush.islandlifesimulator.dialog.DialogText.*;
import static com.javarush.islandlifesimulator.settings.SimulationSettings.*;

public class UserDialog {

    public UserDialog() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println( ISLAND_IMAGE);

            boolean isDefaultRunFlag = initDefaultRunFlag(scanner);
            if (!isDefaultRunFlag) {

                System.out.println(ISLAND_MAP);

                widthMap = initWidthMap(scanner);
                heightMap = initHeightMap(scanner);
                maxAnimalCount = initMaxAnimalCount(scanner);
                reduceHealthPercent = initReduceHealthPercent(scanner);
                plantGrowTime = initPlantGrowTime(scanner);
                statPeriod = initStatPeriod(scanner);
//                SimulationSettings.printTactTime = initPrintTactTime(scanner);
                maxNumberOfTact = initMaxNumberOfTact(scanner);

            }
            printSettings();
            startSim(scanner);
        }
    }

    private int initMaxNumberOfTact(Scanner scanner) {
        int maxTact = maxNumberOfTact;

        while (true) {
            System.out.print(MessageFormat.format(MAX_TACT_NUMBER_TO_STOP_SIM, maxNumberOfTact, MIN_LIMIT_TACT_NUMBER_TO_STOP_SIM, MAX_LIMIT_TACT_NUMBER_TO_STOP_SIM));

            String maxTactAsString = scanner.nextLine();
            if (maxTactAsString.equals("")) {
                return maxTact;
            }

            if (isInteger(maxTactAsString)) {
                maxTact = Integer.parseInt(maxTactAsString);
                if (isMinMaxRangeValid(maxTact, MIN_LIMIT_TACT_NUMBER_TO_STOP_SIM, MAX_LIMIT_TACT_NUMBER_TO_STOP_SIM)) {
                    return maxTact;
                }
            }
        }
    }

    private int initWidthMap(Scanner scanner) {
        int width = widthMap;

        while (true) {
            System.out.print(MessageFormat.format(ENTER_WIDTH_MAP, widthMap, MIN_LIMIT_WIDTH_MAP, MAX_LIMIT_WIDTH_MAP));

            String widthAsString = scanner.nextLine();
            if (widthAsString.equals("")) {
                return width;
            }

            if (isInteger(widthAsString)) {
                width = Integer.parseInt(widthAsString);
                if (isMinMaxRangeValid(width, MIN_LIMIT_WIDTH_MAP, MAX_LIMIT_WIDTH_MAP)) {
                    return width;
                }
            }
        }
    }

    private int initHeightMap(Scanner scanner) {
        int height = heightMap;

        while (true) {
            System.out.print(MessageFormat.format(ENTER_HEIGHT_MAP, heightMap, MIN_LIMIT_HEIGHT_MAP, MAX_LIMIT_HEIGHT_MAP));

            String heightAsString = scanner.nextLine();
            if (heightAsString.equals("")) {
                return height;
            }

            if (isInteger(heightAsString)) {
                height = Integer.parseInt(heightAsString);
                if (isMinMaxRangeValid(height, MIN_LIMIT_HEIGHT_MAP, MAX_LIMIT_HEIGHT_MAP)) {
                    return height;
                }
            }
        }
    }

    private int initMaxAnimalCount(Scanner scanner) {
        int count = maxAnimalCount;

        while (true) {
            System.out.print(MessageFormat.format(INITIAL_NUMBER_OF_ANIMALS, maxAnimalCount, MIN_LIMIT_INITIAL_NUMBER_OF_ANIMALS, MAX_LIMIT_INITIAL_NUMBER_OF_ANIMALS));

            String countAsString = scanner.nextLine();
            if (countAsString.equals("")) {
                return count;
            }

            if (isInteger(countAsString)) {
                count = Integer.parseInt(countAsString);
                if (isMinMaxRangeValid(count, MIN_LIMIT_INITIAL_NUMBER_OF_ANIMALS, MAX_LIMIT_INITIAL_NUMBER_OF_ANIMALS)) {
                    return count;
                }
            }
        }
    }

    private double initReduceHealthPercent(Scanner scanner) {
        int reduceHealthPercent = (int) SimulationSettings.reduceHealthPercent;

        while (true) {
            System.out.print(MessageFormat.format(REDUCTION_HEALTH_EVERY_TACT, SimulationSettings.reduceHealthPercent, MIN_LIMIT_REDUCTION_HEALTH_EVERY_TACT, MAX_LIMIT_REDUCTION_HEALTH_EVERY_TACT));

            String valueAsString = scanner.nextLine();
            if (valueAsString.equals("")) {
                return reduceHealthPercent;
            }

            if (isInteger(valueAsString)) {
                reduceHealthPercent = Integer.parseInt(valueAsString);
                if (isMinMaxRangeValid(reduceHealthPercent, MIN_LIMIT_REDUCTION_HEALTH_EVERY_TACT, MAX_LIMIT_REDUCTION_HEALTH_EVERY_TACT)) {
                    return reduceHealthPercent;
                }
            }
        }
    }

    private int initPlantGrowTime(Scanner scanner) {
        int time = plantGrowTime;

        while (true) {
            System.out.print(MessageFormat.format(PLANT_GROW_DELAY, plantGrowTime, MIN_LIMIT_PLANT_GROW_DELAY, MAX_LIMIT_PLANT_GROW_DELAY));

            String timeAsString = scanner.nextLine();
            if (timeAsString.equals("")) {
                return time;
            }

            if (isInteger(timeAsString)) {
                time = Integer.parseInt(timeAsString);
                if (isMinMaxRangeValid(time, MIN_LIMIT_PLANT_GROW_DELAY, MAX_LIMIT_PLANT_GROW_DELAY)) {
                    return time;
                }
            }
        }
    }

    private int initStatPeriod(Scanner scanner) {
        int time = statPeriod;

        while (true) {
            System.out.print(MessageFormat.format(ENTER_STAT_PERIOD, statPeriod, MIN_LIMIT_STAT_PERIOD, MAX_LIMIT_STAT_PERIOD));

            String timeAsString = scanner.nextLine();
            if (timeAsString.equals("")) {
                return time;
            }

            if (isInteger(timeAsString)) {
                time = Integer.parseInt(timeAsString);
                if (isMinMaxRangeValid(time, MIN_LIMIT_STAT_PERIOD, MAX_LIMIT_STAT_PERIOD)) {
                    return time;
                }
            }
        }
    }

//    private boolean initPrintTactTime(Scanner scanner) {
//        while (true) {
//            System.out.print(MessageFormat.format(PRINT_TACT_TIME_YES_OR_NO));
//
//            String cycleTimeFlagAsString = scanner.nextLine();
//            if (cycleTimeFlagAsString.equals("")) {
//                return true;
//            }
//
//            if (isInteger(cycleTimeFlagAsString)) {
//                int cycleTimeFlag = Integer.parseInt(cycleTimeFlagAsString);
//                if (cycleTimeFlag == 1) {
//                    return true;
//                }else if (cycleTimeFlag == 0) {
//                    return false;
//                }
//            }
//        }
//    }

    private boolean initDefaultRunFlag(Scanner scanner) {
        while (true) {
            System.out.print(DEFAULT_RUN_YES_OR_NO);

            String defaultRunFlagAsString = scanner.nextLine();
            if (defaultRunFlagAsString.equals("")) {
                return true;
            }

            if (isInteger(defaultRunFlagAsString)) {
                int defaultRunFlag = Integer.parseInt(defaultRunFlagAsString);
                if (defaultRunFlag == 1) {
                    return true;
                }else if (defaultRunFlag == 0) {
                    return false;
                }
            }
        }
    }

    private void printSettings(){
        System.out.println(ALL_READY_TO_GO);

        System.out.println("----------------------------------");
        System.out.println(MessageFormat.format(SIZE_MAP, widthMap, heightMap));
        System.out.println(MessageFormat.format(ANIMAL_COUNT_ON_LOCATION, maxAnimalCount));
        System.out.println(MessageFormat.format(HEALTH_REDUCE_FROM_HUNGER, reduceHealthPercent));
        System.out.println(MessageFormat.format(PLANT_GROW_TIME, plantGrowTime));
        System.out.println(MessageFormat.format(STAT_PERIOD, statPeriod));
//        System.out.println(MessageFormat.format(PRINT_TACT_TIME, SimulationSettings.printTactTime ? ANSWER_YES : ANSWER_NO));
        System.out.println(MessageFormat.format(STOP_SIMULATION, maxNumberOfTact));
        System.out.println("----------------------------------");
    }

    private void startSim(Scanner scanner){
        System.out.println(TYPE_START_AND_PRESS_ENTER);
        while (!scanner.nextLine().equals(START)) {
            System.out.println(TYPE_START_AND_PRESS_ENTER);
        }
        System.out.println(GO);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private boolean isMinMaxRangeValid(int number, int minLimit, int maxLimit) {
        if (number < minLimit) return false;
        return number <= maxLimit;
    }
}
