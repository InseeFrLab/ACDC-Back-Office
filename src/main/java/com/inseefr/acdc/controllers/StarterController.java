package com.inseefr.acdc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StarterController {
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok(
                "La tête à toto"
        );
    }

}

