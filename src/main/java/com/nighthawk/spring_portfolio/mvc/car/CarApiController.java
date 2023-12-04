package com.nighthawk.spring_portfolio.mvc.car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping( "/post")
    public ResponseEntity<Object> postCar(@RequestParam("name") String name,
                                          @RequestParam("topspeed") int topspeed,
                                          @RequestParam("price") int price,
                                          @RequestParam("range") int range,
                                          @RequestParam("capacity") int capacity) {
        // A person object WITHOUT ID will create a new record with default roles as student
        Car car = new Car(name, topspeed, price, range, capacity);
        repository.save(car);
        return new ResponseEntity<>(name +" is created successfully", HttpStatus.CREATED);
    }

    /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> carSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Car> list = repository.findByNameIgnoreCase(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}