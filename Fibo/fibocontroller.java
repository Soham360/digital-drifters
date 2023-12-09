import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {

    @GetMapping("/generate")
    public String generateFibonacci() {
        int n = 10; // Adjust the number of Fibonacci numbers to generate
        return calculateFibonacci(n);
    }

    private String calculateFibonacci(int n) {
        StringBuilder result = new StringBuilder();
        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            result.append(a).append(", ");
            int temp = a + b;
            a = b;
            b = temp;
        }

        return result.toString();
    }
}