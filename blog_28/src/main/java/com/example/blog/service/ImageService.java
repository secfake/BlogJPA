package com.example.blog.service;

import com.example.blog.dto.projection.ImagePublic;
import com.example.blog.entity.Image;
import com.example.blog.entity.User;
import com.example.blog.exception.NotFoundException;
import com.example.blog.repository.ImageRepository;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public Image uploadFile(MultipartFile file) {
        User user = userRepository.findById(1).orElseThrow(() -> new NotFoundException("User not found!"));
        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .user(user)
                    .build();
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    public Image getImageById(Integer id) {
        return imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found"));
    }

    public List<ImagePublic> getAllImageByUser(Integer id) {
        return imageRepository.findByUser_IdIsOrderByCreatedAtDesc(id);
    }

    public boolean deleteImage(Integer id) {
        //TODO: hiện tại fix userid =1
        Image image = imageRepository.findByIdIsAndUser_IdIs(id, 1).orElse(null);
        if (image != null) {
            imageRepository.delete(image);
            return true;

        }
        return false;
    }
}
