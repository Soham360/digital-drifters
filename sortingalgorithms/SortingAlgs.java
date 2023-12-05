package com.nighthawk.spring_portfolio.mvc.sortingalgorithms;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.nighthawk.spring_portfolio.mvc.car;
import org.springframework.stereotype.Component;

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

    public void displayStatistics() {
        System.out.println("Iterations: " + iterations);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Merges/Swaps: " + mergesOrSwaps);
        System.out.println("Execution Time: " + executionTime + " milliseconds");
    }

    public void sort(Car[] array) {
        // This method should be overridden by each sorting algorithm class
    }
}

class Car {
    private int id;
    private String name;
    private String origin;
    private String url;
    private String img;

    public Car(int id, String name, int topspeed, int price, int range, int capacity) {
        this.id = id;
        this.name = name;
        this.topspeed = topspeed;
        this.price = price;
        this.range = range;
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", topspeed:'" + topspeed + '\'' +
                ", price'" + price + '\'' +
                ", range'" + range + '\'' +
                ", capacity'" + capacity + '\'' +
                '}';
    }
}

class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (array[j].getPrice().compareTo(array[j + 1].getPrice()) > 0) {
                    // Swap array[j] and array[j+1]
                    Car temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    mergesOrSwaps++;
                }
                iterations++;
            }
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

class SelectionSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j].getPrice().compareTo(array[minIndex].getPrice()) < 0) {
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

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

class MergeSort extends SortingAlgorithm {
    @Override
    public void sort(Car[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        mergeSort(array, 0, array.length - 1);

        executionTime = System.currentTimeMillis() - executionTime;
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
            if (leftArray[i].getPrice().compareTo(rightArray[j].getPrice()) <= 0) {
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

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Car key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].getPrice().compareTo(key.getPrice()) > 0) {
                comparisons++;
                array[j + 1] = array[j];
                j = j - 1;
                mergesOrSwaps++;
                iterations++;
            }
            array[j + 1] = key;
            iterations++;
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

public class SortingAlgs {
    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    //.uri(URI.create("https://dog-names2.p.rapidapi.com/dog_names"))
                    //.header("X-RapidAPI-Key", "48e23c6bf3msh9a6baf3e68d9a4ep14546ajsn1a39e98c4ad5")
                    //.header("X-RapidAPI-Host", "dog-names2.p.rapidapi.com")
                    .uri(URI.create("http://localhost:8030/api/car/"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response body into an array of Car objects
            Car[] cars = parseResponse(response.body());

            // Create an instance of the sorting algorithm
            SortingAlgorithm sortingAlgorithm = new BubbleSort();

            // Sort the array of dog names
            sortingAlgorithm.sort(cars);

            // Display sorting statistics
            sortingAlgorithm.displayStatistics();

            // Print the sorted dog names
            for (Car name : cars) {
                System.out.println(name);
            }

        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }
    }

    private static Car[] parseResponse(String responseBody) {
        // Parse the response body into an array of Car objects
        // Example parsing logic: (You may need to adapt this based on the actual response format)
        String[] lines = responseBody.split("\n");
        Car[] cars = new Car[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(":");
            int id = Integer.parseInt(parts[1]);
            String name = parts[3].replace("\"", "");
            String topspeed = parts[5].replace("\"", "");
            String price = parts[7].replace("\"", "");
            String range = parts[9].replace("\"", "");
            String capacity = parts[9].replace("\"", "");
            cars[i] = new Car(id, name, topspeed, price, range, capacity);

        }
        return cars;
    }
}
