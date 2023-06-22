package com.example.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WebController {

    @GetMapping("/dashboard")
    public String getDashboard(){
        return "Dashboard";
    }


    @PostMapping("/admin/users")
    public String createUser(){
        return "POST User";
    }

    @GetMapping("/products")
    public String getProducts(){
        return "Product";
    }

    @GetMapping("/blogs")
    public String getProducts(){
        return "Blog";
    }


    @GetMapping("/users")
    public String getusers(){
        return "GET User";
    }
}
