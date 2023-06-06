package com.example.blog.service;

import com.example.blog.dto.CategoryDto;
import com.example.blog.entity.Blog;
import com.example.blog.entity.Category;
import com.example.blog.mapper.CategoryMapper;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

//    public List<CategoryDto> getCatagories() {
//        List<Category> categories = categoryRepository.findAll();
//        List<CategoryDto> categoryDtoList = new ArrayList<>();
//        for (Category c : categories) {
//            Long used = Long.valueOf(categoryRepository.countCate(c.getId()));
//            categoryDtoList.add(CategoryMapper.toCategoryDto(c, used));
//        }
//
//        return categoryDtoList;
//    }

    //5
    public List<Blog> getBlogByCategoty(String categoryName) {
        return blogRepository.findByStatusIsTrueAndCategories_NameEqualsIgnoreCase(categoryName);
    }


    public List<CategoryDto> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public List<CategoryDto> getCategoryTop5() {
        return categoryRepository.getCategoryTop5();
    }
}
