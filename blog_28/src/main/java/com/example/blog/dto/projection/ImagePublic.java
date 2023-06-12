package com.example.blog.dto.projection;

import java.time.LocalDateTime;

public interface ImagePublic {
    Integer getId();

    LocalDateTime getCreatedAt();

    byte[] getData();

    String getType();
}
