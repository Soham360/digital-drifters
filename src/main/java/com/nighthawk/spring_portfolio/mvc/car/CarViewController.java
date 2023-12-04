package com.nighthawk.spring_portfolio.mvc.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
@RequestMapping("/mvc/car")
public class CarViewController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private CarDetailsService repository;

    @GetMapping("/read")
    public String car(Model model) {
        List<Car> list = repository.listAll();
        model.addAttribute("list", list);
        return "car/read";
    }

    /*  The HTML template Forms and CarForm attributes are bound
        @return - template for car form
        @param - Car Class
    */
    @GetMapping("/create")
    public String carAdd(Car car) {
        return "car/create";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Car object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/create")
    public String carSave(@Valid Car car, BindingResult bindingResult) {
        // Validation of Decorated CarForm attributes
        if (bindingResult.hasErrors()) {
            return "car/create";
        }
        repository.save(car);
        // Redirect to next step
        return "redirect:/mvc/car/read";
    }

    @GetMapping("/search")
    public String car() {
        return "car/search";
    }

}