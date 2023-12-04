package com.nighthawk.spring_portfolio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class Fibo {
    String name;
    int size;
    int hashID;
    ArrayList<Long> list;
    HashMap<Integer, Object> hash;

    public Fibo() {
        this(8);
    }

    public Fibo(int nth) {
        this.size = nth;
        this.list = new ArrayList<>();
        this.hashID = 0;
        this.hash = new HashMap<>();
        this.init();
    }

    protected abstract void init();

    public void setData(long num) {
        list.add(num);
        hash.put(this.hashID++, list.clone());
    }

    public long getNth() {
        return list.get(this.size - 1);
    }

    public Object getNthSeq(int i) {
        return hash.get(i);
    }

    public void print() {
        System.out.println("Init method = " + this.name);
        System.out.println("fibonacci Number " + this.size + " = " + this.getNth());
        System.out.println("fibonacci List = " + this.list);
        System.out.println("fibonacci Hashmap = " + this.hash);
        for (int i = 0; i < this.size; i++) {
            System.out.println("fibonacci Sequence " + (i + 1) + " = " + this.getNthSeq(i));
        }
    }
}

class FiboWithForLoop extends Fibo {
    public FiboWithForLoop() {
        super();
    }

    @Override
    protected void init() {
        this.name = "For Loop";
        for (int i = 0; i < this.size; i++) {
            this.setData(calculateFibonacci(i));
        }
    }

    private long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }
}

class SortingComparison extends Fibo {
    public SortingComparison() {
        super();
    }

    @Override
    protected void init() {
        this.name = "Sorting Comparison";
        Long[] fibArray = new Long[this.size];
        for (int i = 0; i < this.size; i++) {
            fibArray[i] = calculateFibonacci(i);
        }

        // Sort the array using different sorting algorithms
        System.out.println("Original Fibonacci Array: " + Arrays.toString(fibArray));

        // Merge Sort
        System.out.print("Merge Sort: ");
        long mergeSortStartTime = System.nanoTime();
        mergeSort(fibArray.clone());
        long mergeSortEndTime = System.nanoTime();
        System.out.println((mergeSortEndTime - mergeSortStartTime) + " ns");

        // Bubble Sort
        System.out.print("Bubble Sort: ");
        long bubbleSortStartTime = System.nanoTime();
        bubbleSort(fibArray.clone());
        long bubbleSortEndTime = System.nanoTime();
        System.out.println((bubbleSortEndTime - bubbleSortStartTime) + " ns");

        // Insertion Sort
        System.out.print("Insertion Sort: ");
        long insertionSortStartTime = System.nanoTime();
        insertionSort(fibArray.clone());
        long insertionSortEndTime = System.nanoTime();
        System.out.println((insertionSortEndTime - insertionSortStartTime) + " ns");

        // Selection Sort
        System.out.print("Selection Sort: ");
        long selectionSortStartTime = System.nanoTime();
        selectionSort(fibArray.clone());
        long selectionSortEndTime = System.nanoTime();
        System.out.println((selectionSortEndTime - selectionSortStartTime) + " ns");
    }

    private void mergeSort(Long[] arr) {
    mergeSort(arr, 0, arr.length - 1);
}

private void mergeSort(Long[] arr, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;

        // Recursive calls
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // Merge the sorted halves
        merge(arr, left, mid, right);
    }
}

private void merge(Long[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    // Create temporary arrays
    Long[] leftArray = new Long[n1];
    Long[] rightArray = new Long[n2];

    // Copy data to temporary arrays
    System.arraycopy(arr, left, leftArray, 0, n1);
    System.arraycopy(arr, mid + 1, rightArray, 0, n2);

    // Merge the temporary arrays
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (leftArray[i] <= rightArray[j]) {
            arr[k++] = leftArray[i++];
        } else {
            arr[k++] = rightArray[j++];
        }
    }

    // Copy remaining elements of leftArray, if any
    while (i < n1) {
        arr[k++] = leftArray[i++];
    }

    // Copy remaining elements of rightArray, if any
    while (j < n2) {
        arr[k++] = rightArray[j++];
    }
}

private void bubbleSort(Long[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j + 1]
                long temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

private void insertionSort(Long[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        long key = arr[i];
        int j = i - 1;

        // Move elements of arr[0..i-1] that are greater than key to one position ahead of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

private void selectionSort(Long[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }

        // Swap the found minimum element with the first element
        long temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }
}
}

public class Main {
    public static void main(String[] args) {
        FiboWithForLoop forLoopFibo = new FiboWithForLoop();
        forLoopFibo.print();

        SortingComparison sortingComparisonFibo = new SortingComparison();
        sortingComparisonFibo.print();
    }
}