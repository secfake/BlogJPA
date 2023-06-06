package com.example.blog.dto;


import lombok.*;


@AllArgsConstructor
@Getter
public class CategoryDto {
    private Integer id;
    private String name;
    private Long used; // Số bài blog được áp dụng category này

//    public CategoryDto(Integer id, String name, long used) {
//        this.id = id;
//        this.name = name;
//        this.used = Long.valueOf(used);
//    }
}
