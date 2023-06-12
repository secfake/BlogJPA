package com.example.blog.repository;

import com.example.blog.dto.projection.CategoryWebPublic;
import com.example.blog.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIdIn(List<Integer> ids);

    @Query("select c.id, c.name, COUNT(blogs) from Category c left join c.blogs blogs GROUP BY c.id")
    Page<CategoryWebPublic> findAllCategories(Pageable pageable);

    boolean existsByNameIsIgnoreCase(String name);



}