package com.javarush.islandlifesimulator.dialog;

/**
 * Класс содержит тектовые константы для использования в приложении
 */
public class DialogText {
    public static final String ISLAND_IMAGE = """
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣷⣶⣤⡈⠙⢿⣿⠿⠟⠛⣛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⠿⠄⠀⠀⠀⠴⠿⢿⣿⣿⡿⠟⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⠟⣁⣤⣤⡄⠀⠀⠀⣠⣤⣤⣬⣝⣿⣿⣶⡀⠀⠹⠿⠟⠛⠿⠿⣿⣿⣿
            ⣿⣿⣧⣾⣿⣿⠏⢀⣤⣀⣀⠘⢿⣿⣿⡿⠛⠛⠛⠃⠀⠀⠀⢀⣠⣴⣶⣶⣿⣿
            ⣿⣿⣿⣿⣿⣟⣴⣿⣿⣿⣿⣧⠈⢿⣿⣿⣿⣿⣆⠀⠀⠀⣠⣄⡉⠻⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠘⣿⣿⣿⣿⡟⢀⣾⣿⣿⣿⣿⣷⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⢻⣿⣿⡟⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿⣿⠁⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
            ⣿⠛⠛⠛⠛⠛⠛⢛⣻⣿⣿⠿⢿⡇⢸⡟⠛⠾⠿⣿⣿⣟⡛⠛⠛⠛⠛⠛⠛⣿
            ⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⠀⠈⠛⠛⠁⠀⠀⠀⠀⠈⠉⠛⠿⣿⣿⣿⣿⣿⣿
            ⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿
            ⣿⣿⣃⣀⠀⠀⣀⣤⣶⣶⣶⣦⣤⣀⠀⠀⣀⣤⣤⣤⣤⣀⡀⠀⠀⣀⣀⣸⣿⣿
            ⣿⡉⠛⠿⠿⠟⠋⣉⣤⣴⣦⣄⠙⠻⠿⠿⠟⠛⠋⣉⣉⠙⠻⠿⠿⠟⠛⠛⢉⣿
            ⣿⣿⣷⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣶⣿⣿⣿⣿⣿⣶⣶⣶⣶⣿⣿⣿⣿
            """;
    public static final String APP_DESCRIPTION = "=== Симуляция жизненного цикла острова ===\n";
    public static final String ISLAND_MAP = """
            ----------------------------------
                      Карта острова
            ----------------------------------
            """;
    public static final String PRESS_ENTER_DEFAULT_VALUE = "ENTER - значение по-умолчанию...\n";
    public static final String DEFAULT_RUN_YES_OR_NO = "Запустить симуляцию с настройками по-умолчанию? (Да) (0-Нет 1-Да): ";
    public static final String SIZE_MAP = "Карта {0}x{1}";
    public static final String ANIMAL_COUNT_ON_LOCATION = "Кол-во животных в каждой локации при старте - {0}";
    public static final String HEALTH_REDUCE_FROM_HUNGER = "Уменьшение здоровья от голода каждый такт - {0}%";
    public static final String PLANT_GROW_TIME = "Частота роста растений - {0}мс";
    public static final String STAT_PERIOD = "Период вывода статистики - {0}мс";
    public static final String STOP_SIMULATION = "Остановка симуляции - {0} тактов";
    public static final String ALL_READY_TO_GO = "\nВсе готово для запуска!\n";
    public static final String TYPE_START_AND_PRESS_ENTER = "\nДля старта симуляции введите start и нажмите Enter...";
    public static final String START = "start";
    public static final String GO = "\nПоехали!\n";

    public static final String ENTER_WIDTH_MAP = "Ширина, по-умолчанию {0} ({1}÷{2}): ";
    public static final String ENTER_HEIGHT_MAP = "Высота, по-умолчанию {0} ({1}÷{2}): ";
    public static final String ENTER_STAT_PERIOD = "Период вывода статистики, по-умолчанию {0}мс ({1}÷{2}): ";
    public static final String INITIAL_NUMBER_OF_ANIMALS = "Начальное кол-во животных в каждой локации, по-умолчанию {0} ({1}÷{2}): ";
    public static final String REDUCTION_HEALTH_EVERY_TACT = "Уменьшение здоровья от голода каждый цикл, по-умолчанию {0} ({1}÷{2}): ";
    public static final String PLANT_GROW_DELAY = "Частота роста растений, по-умолчанию каждые {0}мс ({1}÷{2}): ";
    public static final String MAX_TACT_NUMBER_TO_STOP_SIM = "Максимальное кол-во тактов для остановки симуляции, по-умолчанию {0} ({1}÷{2}): ";

    public static final String TACT_STATS = "\n*** Такт {0}-й. Продолжительность {1}мс ***";
    public static final String EMOJI_KEY_COUNT_VALUE = "{0} - {1}";
}
