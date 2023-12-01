package com.nighthawk.spring_portfolio;
import org.springframework.web.bind.annotation.*;

@RestController
public class SortingController {

    @PostMapping("/sort/bubble")
    public SortResult bubbleSort(@RequestBody int[] data) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.init(data);
        bubbleSort.sort();
        bubbleSort.analyze();
        return new SortResult(bubbleSort.iterations, bubbleSort.comparisons, bubbleSort.swaps);
    }

    @PostMapping("/sort/insertion")
    public SortResult insertionSort(@RequestBody int[] data) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.init(data);
        insertionSort.sort();
        insertionSort.analyze();
        return new SortResult(insertionSort.iterations, insertionSort.comparisons, insertionSort.swaps);
    }

    @PostMapping("/sort/selection")
    public SortResult selectionSort(@RequestBody int[] data) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.init(data);
        selectionSort.sort();
        selectionSort.analyze();
        return new SortResult(selectionSort.iterations, selectionSort.comparisons, selectionSort.swaps);
    }

    @PostMapping("/sort/merge")
    public SortResult mergeSort(@RequestBody int[] data) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.init(data);
        mergeSort.sort();
        mergeSort.analyze();
        return new SortResult(mergeSort.iterations, mergeSort.comparisons, mergeSort.swaps);
    }

    static class SortResult {
        int iterations;
        int comparisons;
        int swaps;

        SortResult(int iterations, int comparisons, int swaps) {
            this.iterations = iterations;
            this.comparisons = comparisons;
            this.swaps = swaps;
        }
    }
}
