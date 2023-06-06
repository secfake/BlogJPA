package com.example.blog.controller;

import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    ////3
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());

    }

    //4
    @GetMapping("/category/top5")
    public ResponseEntity<?> getCategoryTop5() {
        return ResponseEntity.ok(categoryService.getCategoryTop5());

    }

    //5
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getBlogByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getBlogByCategoty(categoryName));
    }
}
