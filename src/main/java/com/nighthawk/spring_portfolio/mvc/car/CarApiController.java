package com.nighthawk.spring_portfolio.mvc.car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*", allowedHeaders = "*") // added this line
@RestController
@RequestMapping("/api/car/")
public class CarApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */
    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private CarJpaRepository repository;
    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Car>> getCar() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }
    /*
    GET individual Car using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable long id) {
        Optional<Car> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Car activity = optional.get();  // value from findByID
            return new ResponseEntity<>(activity, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}