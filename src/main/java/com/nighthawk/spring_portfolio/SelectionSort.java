package com.nighthawk.spring_portfolio;
public class SelectionSort extends Sort {

    @Override
    public void init(int[] data) {
        this.data = data.clone();
    }

    @Override
    public void sort() {
        int n = data.length;

        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                comparisons++;
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap data[i] and data[minIndex]
            int temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;

            swaps++;
            iterations++;
        }
    }
}
