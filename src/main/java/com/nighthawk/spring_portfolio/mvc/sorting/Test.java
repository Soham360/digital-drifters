
package com.nighthawk.spring_portfolio.mvc.sorting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.nighthawk.spring_portfolio.mvc.car.Car;
import com.nighthawk.spring_portfolio.mvc.car.CarDetailsService;
//import com.nighthawk.spring_portfolio.mvc.sorting.Test.BubbleSort;

import java.util.List;

@Component
@Configuration // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class Test {  
    @Autowired CarDetailsService carRepo;

    @Bean
    CommandLineRunner run1() {  // The run() method will be executed after the application starts
        return args -> {

    //          Car[] carArray = Car.init();
    //         for (Car name : carArray) {
    //             List<Car> test = carRepo.list(name.getName());  // lookup
    //             if (test.size() == 0) {
    //                 carRepo.save(name);
    //             };
    //         }
    //         System.out.println("Inside Test.java");
    //         System.out.println(carRepo.get(5));
    //         System.out.println(carRepo.get(5).getPrice());

    //         // Parse the response body into an array of Car objects
    //         //Car[] cars = Car.init();

    //         // Create an instance of the sorting algorithm
    //         SortingAlgorithm sortingAlgorithm = new BubbleSort();

    //         // Sort the array of dog names
    //         sortingAlgorithm.sort(carArray);

    //         // Display sorting statistics
    //         sortingAlgorithm.displayStatistics();


    //         // Print the sorted Car names
    //         Car[] sortedCarArray = carArray;
    //         int i =0;
    //         for (Car name : sortedCarArray) {

    //             //System.out.println(name);
    //             System.out.println(carRepo.getByPrice(name.getPrice() ));
    //             i++;
    //     }

        };
    }
}

@Component
class SortingAlgorithm {

    protected int iterations;
    protected int comparisons;
    protected int mergesOrSwaps;
    protected long executionTime;

    public SortingAlgorithm() {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;
    }

    public void resetStatistics() {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;
    }

    public int getIterations() {
        return iterations;
    }
    public int getComparisons() {
        return comparisons;
    }
    public int getMergesOrSwaps() {
        return mergesOrSwaps;
    }
    public long getExecutionTime() {
        return executionTime;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
    public void setMergesOrSwaps(int mergesOrSwaps) {
        this.mergesOrSwaps = mergesOrSwaps;
    }
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public void displayStatistics() {
        this.setIterations(iterations);
        this.setComparisons(comparisons);
        this.setMergesOrSwaps(mergesOrSwaps);
        this.setExecutionTime(executionTime);
        System.out.println("Iterations: " + iterations);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Merges/Swaps: " + mergesOrSwaps);
        System.out.println("Execution Time: " + executionTime + " nanoseconds");
    }

    public void sort(Car[] array) {
        // This method should be overridden by each sorting algorithm class
    }
}

class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.nanoTime();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (array[j].getPrice() > (array[j + 1].getPrice()) ) {
                    // Swap array[j] and array[j+1]
                    Car temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    mergesOrSwaps++;
                }
                iterations++;
            }
        }

        executionTime = System.nanoTime() - executionTime;
    }
}

class SelectionSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.nanoTime();

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j].getPrice() > (array[minIndex].getPrice()) ) {
                    minIndex = j;
                }
                iterations++;
            }

            // Swap array[i] and array[minIndex]
            Car temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            mergesOrSwaps++;
        }

        executionTime = System.nanoTime() - executionTime;
    }
}

class MergeSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.nanoTime();

        mergeSort(array, 0, array.length - 1);

        executionTime = System.nanoTime() - executionTime;
    }

    private void merge(Car[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Car[] leftArray = new Car[n1];
        Car[] rightArray = new Car[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            comparisons++;
            if (leftArray[i].getPrice() > (rightArray[j].getPrice()) ) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            iterations++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
            mergesOrSwaps++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
            mergesOrSwaps++;
        }
    }

    private void mergeSort(Car[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }
}

class InsertionSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.nanoTime();

        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Car key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].getPrice() > (key.getPrice()) ) {
                comparisons++;
                array[j + 1] = array[j];
                j = j - 1;
                mergesOrSwaps++;
                iterations++;
            }
            array[j + 1] = key;
            iterations++;
        }

        executionTime = System.nanoTime() - executionTime;
    }
}


