package com.fpt.swp391.group6.DigitalTome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @GetMapping("/admin")
    public String admin(){
        return "index_admin";
    }
}
