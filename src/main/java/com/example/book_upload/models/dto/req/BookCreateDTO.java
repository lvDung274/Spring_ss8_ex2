package com.example.book_upload.models.dto.req;

import org.springframework.web.multipart.MultipartFile;

public record BookCreateDTO(
        String title,
        String author,
        Integer stock,
        MultipartFile coverImage
) {
}
