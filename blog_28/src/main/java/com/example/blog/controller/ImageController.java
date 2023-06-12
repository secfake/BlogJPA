package com.example.blog.controller;

import com.example.blog.entity.Image;
import com.example.blog.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    //    Upload image theo user (người thực hiện upload chính là user đang login)
    @PostMapping("/api/v1/files")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadFile(file));
    }


    //    Xem ảnh
    @GetMapping("/api/v1/files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
//        return ResponseEntity.ok(image);
    }


    //    Lấy danh sách ảnh của user đang login
    @GetMapping("/api/v1/users/{id}/files")
    public ResponseEntity<?> getAllImageByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(imageService.getAllImageByUser(id));
    }


//    Xóa ảnh (nếu không phải ảnh của user upload -> không cho xóa)
    @DeleteMapping("/api/v1/files/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id){
        if (imageService.deleteImage(id)){
            return ResponseEntity.ok("successful delete");
        }else {
            return ResponseEntity.status(400).body("delete failed");
        }

    }

}
