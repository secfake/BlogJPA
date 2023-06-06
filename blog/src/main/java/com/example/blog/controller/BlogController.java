package com.example.blog.controller;

import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.BlogService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
public class BlogController {

    @Autowired
    private BlogService blogService;


    //1
    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogsPublic(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(blogService.getBlogsPublic(page, pageSize).getContent());
    }


    //2
    @GetMapping("/search")
    public ResponseEntity<?> getBlogsByTerm(@RequestParam String term) {
        return ResponseEntity.ok(blogService.getBlogsByTerm(term));
    }


    ///6
    @GetMapping("/blogs/{blogId}/{blogSlug}")
    public ResponseEntity<?> getBlogIdEquaSlug(@PathVariable int blogId, @PathVariable String blogSlug) {
        return ResponseEntity.ok(blogService.getBlogIdEquaSlug(blogId, blogSlug));
    }

}
