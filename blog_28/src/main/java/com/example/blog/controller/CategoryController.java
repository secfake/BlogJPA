package com.example.blog.controller;

import com.example.blog.dto.projection.CategoryWebPublic;
import com.example.blog.request.UpsertCategoryRequest;
import com.example.blog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //    Lấy ds category (có phân trang, mặc định là pageSize = 10) (Trả về Giao diện)
    @GetMapping("/admin/categories")
    public String getAllCategory(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                 Model model) {
        Page<CategoryWebPublic> categoryWebPublicPage = categoryService.getAllCategory(page, pageSize);
        model.addAttribute("page", categoryWebPublicPage);
        model.addAttribute("currentPage", page);
        return "admin/category/category-list";
    }
//    @GetMapping("/admin/categories")
//    public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
//                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize){
//        return ResponseEntity.ok(categoryService.getAllCategory(page,pageSize));
//    }

    //    Thêm category (Lưu ý tên category không được trùng nhau)
    @PostMapping("/api/v1/admin/categories")
    public ResponseEntity<?> createCatogory(@RequestBody UpsertCategoryRequest upsertCategoryRequest) {
        categoryService.createCategory(upsertCategoryRequest);
        return ResponseEntity.status(201).body("successful create");

    }


    //    Cập nhật category (Lưu ý tên category không được trùng nhau)
    @PutMapping("/api/v1/admin/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody UpsertCategoryRequest upsertCategoryRequest) {
        categoryService.updateCategory(id, upsertCategoryRequest);
        return ResponseEntity.ok("successful update");
    }


    //    Xóa category (xóa blog áp dụng category trong bảng trung gian, không xóa blog trong bảng blog)
    @DeleteMapping("/api/v1/admin/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("successful delete");
    }
}
