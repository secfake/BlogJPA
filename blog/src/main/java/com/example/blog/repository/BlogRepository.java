package com.example.blog.repository;

import com.example.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatusIsTrueOrderByPublishedAtDesc(Pageable pageable);

    List<Blog> findByStatusIsTrueAndTitleContainsIgnoreCase(String title);


    Blog findByStatusIsTrueAndIdIsAndSlugEqualsIgnoreCase(Integer id, String slug);

    List<Blog> findByStatusIsTrueAndCategories_NameEqualsIgnoreCase(String name);



//    @Query("select b from Blog b where b.status = true order by b.publishedAt DESC")
//    Page<Blog> findAllBlog(Pageable pageable);




}