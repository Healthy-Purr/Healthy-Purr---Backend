package com.dawmecnagtrt.healthypurr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String works(){return "Working on user endpoints...";}

    //TODO: Add endpoints
}
