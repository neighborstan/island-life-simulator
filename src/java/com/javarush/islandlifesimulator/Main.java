package com.javarush.islandlifesimulator;

import com.javarush.islandlifesimulator.process.Processor;

public class Main {
    public static void main(String[] args) {
        Processor simulation = new Processor();
        simulation.start();
    }
}
