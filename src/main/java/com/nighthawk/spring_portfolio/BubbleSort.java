package com.nighthawk.spring_portfolio;
public class BubbleSort extends Sort {

    @Override
    public void init(int[] data) {
        this.data = data.clone();
    }

    @Override
    public void sort() {
        int n = data.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;

                if (data[j] > data[j + 1]) {
                    swaps++;

                    // Swap data[j] and data[j+1]
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
            iterations++;
        }
    }
}
