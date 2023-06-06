package com.example.blog.repository;

import com.example.blog.dto.CategoryDto;
import com.example.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


////
//    @Query(value = "select count(c.category_id) from blog_category c where c.category_id = :id", nativeQuery = true)
//    long countCate(@Param("id") Integer id);
////
//    @Query(value = "select c.id, c.name from category c where c.id = ?1", nativeQuery = true)
//    Object getCate(Integer id);
//
//    @Query(value = "select new com.example.blog.dto.CategoryDto(c.id, c.name, count(b.category_id)) from category c JOIN blog_category b  where c.id = b.category_id group by c.id", nativeQuery = true)
//    List<CategoryDto> getAllCategory();

    @Query("SELECT new com.example.blog.dto.CategoryDto(c.id, c.name, COUNT(b.id)) " +
            "FROM Category c JOIN c.blogs b " +
            "GROUP BY c.id")
    List<CategoryDto> getAllCategories();

    @Query("SELECT new com.example.blog.dto.CategoryDto(c.id, c.name, COUNT(b.id) as used) " +
            "FROM Category c JOIN c.blogs b " +
            "GROUP BY c.id " +
            "ORDER BY used DESC " +
            "LIMIT 5")
    List<CategoryDto> getCategoryTop5();


}
