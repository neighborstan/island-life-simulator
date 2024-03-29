package com.javarush.islandlifesimulator.dialog;

import com.javarush.islandlifesimulator.simulation.SimulationSettings;

import java.text.MessageFormat;
import java.util.Scanner;

import static com.javarush.islandlifesimulator.dialog.DialogText.*;
import static com.javarush.islandlifesimulator.simulation.SimulationSettings.*;

/**
 * Класс отвечающий за диалог с пользователем
 */
public class UserDialog {
    /** Поле настройки симуляции */
    private SimulationSettings settings;

    /**
     * Конструктор класса, в котором пользователю предлагается ввести параметры и запустить симуляцию.
     */
    public UserDialog(SimulationSettings settings) {
        this.settings = settings;

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println(ISLAND_IMAGE);
            System.out.println(APP_DESCRIPTION.toUpperCase());
            System.out.println(PRESS_ENTER_DEFAULT_VALUE);

            boolean isDefaultRunFlag = initDefaultRunFlag(scanner);
            if (!isDefaultRunFlag) {

                System.out.println(ISLAND_MAP);

                settings.setWidthMap(initWidthMap(scanner));
                settings.setHeightMap(initHeightMap(scanner));
                settings.setMaxAnimalCount(initMaxAnimalCountAtStart(scanner));
                settings.setReduceHealthPercent(initReduceHealthPercent(scanner));
                settings.setPlantGrowTime(initPlantGrowTime(scanner));
                settings.setStatPeriod(initStatPeriod(scanner));
                settings.setMaxNumberOfTact(initMaxNumberOfTact(scanner));

            }
            printSettings();
            startSim(scanner);
        }
    }

    /**
     * Метод определяет максимальный номер такта жизненного цикла острова для завершения симуляции
     * @param scanner объект сканера для считывания данных
     * @return возвращает номер максимального такта
     */
    private int initMaxNumberOfTact(Scanner scanner) {
        int maxTact = settings.getMaxNumberOfTact();

        while (true) {
            System.out.print(MessageFormat.format(MAX_TACT_NUMBER_TO_STOP_SIM, settings.getMaxNumberOfTact(), MIN_LIMIT_TACT_NUMBER_TO_STOP_SIM, MAX_LIMIT_TACT_NUMBER_TO_STOP_SIM));

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

    /**
     * Метод определяет ширину карты острова
     * @param scanner объект сканера для считывания данных
     * @return возвращает ширину карты
     */
    private int initWidthMap(Scanner scanner) {
        int width = settings.getWidthMap();

        while (true) {
            System.out.print(MessageFormat.format(ENTER_WIDTH_MAP, settings.getWidthMap(), MIN_LIMIT_WIDTH_MAP, MAX_LIMIT_WIDTH_MAP));

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

    /**
     * Метод определяет высоту карты острова
     * @param scanner объект сканера для считывания данных
     * @return возвращает высоту карты
     */
    private int initHeightMap(Scanner scanner) {
        int height = settings.getHeightMap();

        while (true) {
            System.out.print(MessageFormat.format(ENTER_HEIGHT_MAP, settings.getHeightMap(), MIN_LIMIT_HEIGHT_MAP, MAX_LIMIT_HEIGHT_MAP));

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

    /**
     * Метод определяет максимальное кол-во животных в локации на старте симуляции
     * @param scanner объект сканера для считывания данных
     * @return возвращает максимальное кол-во животных
     */
    private int initMaxAnimalCountAtStart(Scanner scanner) {
        int count = settings.getMaxAnimalCount();

        while (true) {
            System.out.print(MessageFormat.format(INITIAL_NUMBER_OF_ANIMALS, settings.getMaxAnimalCount(), MIN_LIMIT_INITIAL_NUMBER_OF_ANIMALS, MAX_LIMIT_INITIAL_NUMBER_OF_ANIMALS));

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

    /**
     * Метод определяет процент уменьшения здоровья от голода каждый такт
     * @param scanner объект сканера для считывания данных
     * @return возвращает процент уменьшения здоровья
     */
    private double initReduceHealthPercent(Scanner scanner) {
        int reduceHealthPercent = (int) settings.getReduceHealthPercent();

        while (true) {
            System.out.print(MessageFormat.format(REDUCTION_HEALTH_EVERY_TACT, settings.getReduceHealthPercent(), MIN_LIMIT_REDUCTION_HEALTH_EVERY_TACT, MAX_LIMIT_REDUCTION_HEALTH_EVERY_TACT));

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

    /**
     * Метод определяет частоту роста растений в течение жизненного цикла, в мс
     * @param scanner объект сканера для считывания данных
     * @return возвращает частоту роста растений, в мс
     */
    private int initPlantGrowTime(Scanner scanner) {
        int time = settings.getPlantGrowTime();

        while (true) {
            System.out.print(MessageFormat.format(PLANT_GROW_DELAY, settings.getPlantGrowTime(), MIN_LIMIT_PLANT_GROW_DELAY, MAX_LIMIT_PLANT_GROW_DELAY));

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

    /**
     * Метод определяет частоту изменения статистических данных в течение жизненного цикла, в мс
     * @param scanner объект сканера для считывания данных
     * @return возвращает частоту изменения статистических данных, в мс
     */
    private int initStatPeriod(Scanner scanner) {
        int time = settings.getStatPeriod();

        while (true) {
            System.out.print(MessageFormat.format(ENTER_STAT_PERIOD, settings.getStatPeriod(), MIN_LIMIT_STAT_PERIOD, MAX_LIMIT_STAT_PERIOD));

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

    /**
     * Метод определяет желание пользователя запуска симуляции с дефолтными параметрами
     * @param scanner объект сканера для считывания данных
     * @return возвращает true, если пользователь согласен на дефолтные параметры запуска
     */
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

    /**
     * Метод печатает выбранные пользователем параметры
     */
    private void printSettings(){
        System.out.println(ALL_READY_TO_GO);

        System.out.println("----------------------------------");
        System.out.println(MessageFormat.format(SIZE_MAP, settings.getWidthMap(), settings.getHeightMap()));
        System.out.println(MessageFormat.format(ANIMAL_COUNT_ON_LOCATION, settings.getMaxAnimalCount()));
        System.out.println(MessageFormat.format(HEALTH_REDUCE_FROM_HUNGER, settings.getReduceHealthPercent()));
        System.out.println(MessageFormat.format(PLANT_GROW_TIME, settings.getPlantGrowTime()));
        System.out.println(MessageFormat.format(STAT_PERIOD, settings.getStatPeriod()));
        System.out.println(MessageFormat.format(STOP_SIMULATION, settings.getMaxNumberOfTact()));
        System.out.println("----------------------------------");
    }

    /**
     * Метод предлагает пользователя запустить симуляцию
     * @param scanner объект сканера для считывания данных
     */
    private void startSim(Scanner scanner){
        System.out.println(TYPE_START_AND_PRESS_ENTER);
        while (!scanner.nextLine().equals(START)) {
            System.out.println(TYPE_START_AND_PRESS_ENTER);
        }
        System.out.println(GO_MESSAGE);
    }

    /**
     * Метод проверяет переданную строку является ли она числом
     * @param str проверяемая строка
     * @return возвращает true, если строка является числом
     */
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Метод определяет вхождение переданного числа в валидный диапазон
     * @param number проверяемое число
     * @param minLimit минимальная граница диапазона
     * @param maxLimit максимальная граница диапазона
     * @return возвращает true, если число входит в валидный диапазон
     */
    private boolean isMinMaxRangeValid(int number, int minLimit, int maxLimit) {
        if (number < minLimit) {
            return false;
        }
        return number <= maxLimit;
    }
}
