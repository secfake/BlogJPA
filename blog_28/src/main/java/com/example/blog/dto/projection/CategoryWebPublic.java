package com.example.blog.dto.projection;

import com.example.blog.entity.Category;
import lombok.RequiredArgsConstructor;

public interface CategoryWebPublic {
    Integer getId();

    String getName();

    Integer getUsed();

    @RequiredArgsConstructor
    class CategoryWebPublicImpl implements CategoryWebPublic {
        private final Category category;

        @Override
        public Integer getId() {
            return this.category.getId();
        }

        @Override
        public String getName() {
            return this.category.getName();
        }

        @Override
        public Integer getUsed() {
            return Math.toIntExact(this.category.getBlogs().stream()
                    .filter(blog -> blog.getStatus())
                    .count());
        }
    }

    static CategoryWebPublic of(Category category) {
        return new CategoryWebPublicImpl(category);
    }
}
