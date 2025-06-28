package dev.saeedkhanloo;

import dev.saeedkhanloo.simulation.Simulator;

/**
 * Entry point for the Capitaly University simulation program.
 * Runs simulations with small, medium, and large input files.
 */
public class Main {
    public static void main(String[] args) {
        String small = "input/SInputs.txt";
        String medium = "input/MInputs.txt";
        String large = "input/LInputs.txt";

        System.out.println("Running simulation with small input file:");
        Simulator simulator = new Simulator(small);
        simulator.simulation();

        System.out.println("\nRunning simulation with medium input file:");
        simulator = new Simulator(medium);
        simulator.simulation();

        System.out.println("\nRunning simulation with large input file:");
        simulator = new Simulator(large);
        simulator.simulation();
    }
}