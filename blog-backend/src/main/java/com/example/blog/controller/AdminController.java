package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String getIndex(){
        return "admin/blog/index";
    }


    @GetMapping("/blog-create")
    public String getBlogCreate(){
        return "admin/blog/blog-create";
    }
    @GetMapping("/blog-detail")
    public String getBlogDetail(){
        return "admin/blog/blog-detail";
    }
    @GetMapping("/blog-index")
    public String getBlogIndex(){
        return "admin/blog/blog-index";
    }
    @GetMapping("/blog-yourself")
    public String getBlogYourself(){
        return "admin/blog/blog-yourself";
    }
}
