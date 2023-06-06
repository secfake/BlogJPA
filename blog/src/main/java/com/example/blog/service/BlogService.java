package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BlogService {


    @Autowired
    private BlogRepository blogRepository;

    public Page<Blog> getBlogsPublic(int page, int pageSize) {
        return blogRepository.findByStatusIsTrueOrderByPublishedAtDesc(PageRequest.of(page,pageSize));
    }

    public List<Blog> getBlogsByTerm(String term) {
        return blogRepository.findByStatusIsTrueAndTitleContainsIgnoreCase(term);
    }

    public Blog getBlogIdEquaSlug(int blogId, String blogSlug) {
        return blogRepository.findByStatusIsTrueAndIdIsAndSlugEqualsIgnoreCase(blogId,blogSlug);
    }
}
