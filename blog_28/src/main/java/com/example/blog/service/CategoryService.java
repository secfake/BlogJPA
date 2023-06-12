package com.example.blog.service;

import com.example.blog.dto.projection.CategoryPublic;
import com.example.blog.dto.projection.CategoryWebPublic;
import com.example.blog.entity.Category;
import com.example.blog.exception.BadRequestException;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.request.UpsertCategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    public Page<CategoryWebPublic> getAllCategory(Integer page, Integer pageSize) {
        Page<Category> categoryList = categoryRepository.findAll(PageRequest.of(page - 1, pageSize));

        return categoryList.map(CategoryWebPublic::of);
    }

    public void createCategory(UpsertCategoryRequest upsertCategoryRequest) {
        if (categoryRepository.existsByNameIsIgnoreCase(upsertCategoryRequest.getName())) {
            throw new BadRequestException("Category already exist");
        }
        Category category = Category.builder().name(upsertCategoryRequest.getName()).build();
        categoryRepository.save(category);

    }

    public void updateCategory(Integer id, UpsertCategoryRequest categotyRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category id not found"));
        if (!category.getName().equalsIgnoreCase(categotyRequest.getName())) {
            if (categoryRepository.existsByNameIsIgnoreCase(categotyRequest.getName())) {
                throw new BadRequestException("Category already exist");
            }
        }
        category.setName(categotyRequest.getName());
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }


    public List<CategoryPublic> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryPublic::of).toList();
    }
}
