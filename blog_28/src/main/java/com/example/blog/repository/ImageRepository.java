package com.example.blog.repository;

import com.example.blog.dto.projection.ImagePublic;
import com.example.blog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<ImagePublic> findByUser_IdIsOrderByCreatedAtDesc(Integer id);

    Optional<Image> findByIdIsAndUser_IdIs(Integer id, Integer id1);
    
    

}