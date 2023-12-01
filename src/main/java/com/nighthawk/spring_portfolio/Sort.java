package com.nighthawk.spring_portfolio;
public abstract class Sort {
    int[] data;
    int iterations;
    int comparisons;
    int swaps;

    public abstract void init(int[] data);

    public abstract void sort();

    public void analyze() {
        // Analyze and print statistics
        System.out.println("Iterations: " + iterations);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
    }
}
