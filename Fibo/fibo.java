import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
public class FibonacciCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FibonacciCalculatorApplication.class, args);
    }

}

@RestController
@RequestMapping("/api")
class FibonacciController {

    @GetMapping("/fibonacci/{number}")
    public FibonacciResponse getFibonacciInfo(@PathVariable int number) {
        int position = findFibonacciPosition(number);

        if (position != -1) {
            long bubbleSortTime = measureSortTime(SortAlgorithm.BUBBLE);
            long selectionSortTime = measureSortTime(SortAlgorithm.SELECTION);

            return new FibonacciResponse(number, position, bubbleSortTime, selectionSortTime);
        } else {
            return new FibonacciResponse(number, "Number not found in the Fibonacci sequence.");
        }
    }

    private int findFibonacciPosition(int number) {
        int[] fibSequence = new int[10];
        for (int i = 0; i < 10; i++) {
            fibSequence[i] = fibonacci(i);
        }

        for (int i = 0; i < fibSequence.length; i++) {
            if (fibSequence[i] == number) {
                return i;
            }
        }
        return -1;
    }

    private long measureSortTime(SortAlgorithm algorithm) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = fibonacci(i);
        }

        long startTime = System.currentTimeMillis();
        if (algorithm == SortAlgorithm.BUBBLE) {
            bubbleSort(arr);
        } else if (algorithm == SortAlgorithm.SELECTION) {
            selectionSort(arr);
        }
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap arr[i] and arr[minIndex]
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

enum SortAlgorithm {
    BUBBLE,
    SELECTION
}

class FibonacciResponse {
    private int number;
    private int position;
    private long bubbleSortTime;
    private long selectionSortTime;
    private String message;

    public FibonacciResponse(int number, int position, long bubbleSortTime, long selectionSortTime) {
        this.number = number;
        this.position = position;
        this.bubbleSortTime = bubbleSortTime;
        this.selectionSortTime = selectionSortTime;
    }

    public FibonacciResponse(int number, String message) {
        this.number = number;
        this.message = message;
    }

    // getters and setters
}
