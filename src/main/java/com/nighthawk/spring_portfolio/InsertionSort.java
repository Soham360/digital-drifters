public class InsertionSort extends Sort {

    @Override
    public void init(int[] data) {
        this.data = data.clone();
    }

    @Override
    public void sort() {
        int n = data.length;

        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                comparisons++;
                swaps++;

                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
            iterations++;
        }
    }
}
