package com.nighthawk.spring_portfolio.mvc.fibo;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fibo") 
public class FiboApiController { 
    String last_run = null;  

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFibonacci(@PathVariable Integer id) {
        HashMap<String, Object> returnValue = new HashMap<String, Object>();
        GoldenRatio forLoopFibo = new GoldenRatio(); 

        returnValue.put("result", forLoopFibo.getNthTerm(id));
        returnValue.put("for_Loop", forLoopFibo.getFinalTime());
        return new ResponseEntity<>(returnValue, HttpStatus.OK);   
    }
}