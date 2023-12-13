package com.recime.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author junel
 */
@RestController
public class RecimeController {
    
    @GetMapping("/helloworld")
    public String HelloWorld() {
        return "Hello World";
    }
    
}
