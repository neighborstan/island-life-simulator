package com.javarush.islandlifesimulator.entities.animals;

/**
 * Перечесление действий животных
 */
public enum Action {
    MOVE(90),
    EAT(100),
    REPRODUCE(50),
    SLEEP(100);

    /** Поле вероятность выполнения действия */
    private int actionChanceIndex;

    /**
     * Конструктор класса, инициализирует поле вероятности
     * @param chanceIndex вероятность выполнения действия
     */
    Action(int chanceIndex) {
        this.actionChanceIndex = chanceIndex;
    }

    /**
     * Геттер  поля вероятности выполнения действия
     * @return возвращает значение поля вероятности выполнения действия
     */
    public int getActionChanceIndex() {
        return actionChanceIndex;
    }
}
