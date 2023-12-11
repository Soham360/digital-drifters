package com.nighthawk.spring_portfolio.mvc.sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.car.Car;
import com.nighthawk.spring_portfolio.mvc.car.CarJpaRepository;

import java.util.*;
@CrossOrigin(origins = "*", allowedHeaders = "*") // added this line
@RestController
@RequestMapping("/api/sortedids")
public class SortedCarApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */
    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private CarJpaRepository repository;

    //  /*
    // GET List of  Cars
    //  */
    // @GetMapping("/{index}")
    // public ResponseEntity<Car> getSortedCarByIndex(@PathVariable int index) {
    //     List<Car> sortedCars = repository.findAllByOrderByPriceAsc();
    //     if (index >= 0 && index < sortedCars.size()) {
    //         Car car = sortedCars.get(index);
    //         return new ResponseEntity<>(car, HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }

    /*
    GET List of  Cars
     */
    @GetMapping("/{index}")
    //public ResponseEntity<Car> getSortedCarByIndex(@PathVariable int index) {
    public ResponseEntity<Map<String, Object>> getSortedCarByIndex(@PathVariable int index) {        
        // Get the list of cars from the repository (already sorted by name)
        List<Car> carList = repository.findAllByOrderByNameAsc();

        // If you want to use a sorting algorithm (BubbleSort), uncomment the following lines:
        Car carArray[] = carList.toArray(new Car[0]);
        
        // BUBBLE SORT

        SortingAlgorithm bSort = new BubbleSort();
        bSort.sort(carArray);

        // Update the original carList based on the sorted order
        carList = Arrays.asList(carArray);

        // Display sorting statistics (if needed)
        bSort.displayStatistics();

        // Fetch the sorting statistics from the sortingAlgorithm instance
        SortingAlgorithm bsortingStatistics = bSort;


        // SELECTION SORT

        SortingAlgorithm sSort = new SelectionSort();
        sSort.sort(carArray);

        // Update the original carList based on the sorted order
        carList = Arrays.asList(carArray);

        // Display sorting statistics (if needed)
        sSort.displayStatistics();

        // Fetch the sorting statistics from the sortingAlgorithm instance
        SortingAlgorithm ssortingStatistics = sSort;


        // MERGE SORT

        SortingAlgorithm mSort = new MergeSort();
        mSort.sort(carArray);

        // Update the original carList based on the sorted order
        carList = Arrays.asList(carArray);

        // Display sorting statistics (if needed)
        mSort.displayStatistics();

        // Fetch the sorting statistics from the sortingAlgorithm instance
        SortingAlgorithm msortingStatistics = mSort;


        // INSERTION SORT

        SortingAlgorithm iSort = new InsertionSort();
        iSort.sort(carArray);

        // Update the original carList based on the sorted order
        carList = Arrays.asList(carArray);

        // Display sorting statistics (if needed)
        iSort.displayStatistics();

        // Fetch the sorting statistics from the sortingAlgorithm instance
        SortingAlgorithm isortingStatistics = iSort;



        // Print the sorted Car names
        System.out.println("from /id api");
        Car[] sortedCarArray = carArray;
        // //int i =0;
        // for (Car name : sortedCarArray) {

        //      //System.out.println(name);
        //     System.out.println(sortedCarArray[i].toString());
        //     i++;
        // }

        if (index >= 0 && index < carList.size()) {
            Car car = carList.get(index);
            //return new ResponseEntity<>(car, HttpStatus.OK);
            Map<String, Object> response = new HashMap<>();
            response.put("car", car);
            response.put("bstatistics", bsortingStatistics);
            response.put("sstatistics", ssortingStatistics);
            response.put("mstatistics", msortingStatistics);
            response.put("istatistics", isortingStatistics);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<List<Car>> getSortedCar() {
        // Get the list of cars from the repository (already sorted by name)
        List<Car> carList = repository.findAllByOrderByNameAsc();

        // If you want to use a sorting algorithm (BubbleSort), uncomment the following lines:
        Car carArray[] = carList.toArray(new Car[0]);
        SortingAlgorithm sortingAlgorithm = new BubbleSort();
        sortingAlgorithm.sort(carArray);

        // Update the original carList based on the sorted order
        carList = Arrays.asList(carArray);

        // Display sorting statistics (if needed)
        sortingAlgorithm.displayStatistics();

        // Print the sorted Car names
        System.out.println("from / api");
        Car[] sortedCarArray = carArray;
        int i =0;
        for (Car name : sortedCarArray) {

            //System.out.println(name);
            System.out.println(sortedCarArray[i].toString());
            i++;
        }

        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

}