package com.example.book_upload.models.services.impl;

import com.example.book_upload.models.dto.req.BookCreateDTO;
import com.example.book_upload.models.dto.req.BookUpdateStockDTO;
import com.example.book_upload.models.entities.Book;
import com.example.book_upload.models.repositories.BookRepository;
import com.example.book_upload.models.services.BookService;
import exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final String UPLOAD_DIR = "uploads/";
    @Override
    public Book createBook(BookCreateDTO dto) {
        String coverUrl = null;
        MultipartFile file = dto.coverImage();

        if (file != null && !file.isEmpty()) {
            try {
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                coverUrl = "/uploads/" + fileName;

            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi lưu file ảnh: " + e.getMessage(), e);
            }
        }

        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setStock(dto.stock());
        book.setCoverUrl(coverUrl);

        return bookRepository.save(book);
    }
    @Override
    public Book updateBook(Long id, BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));

        book.setStock(dto.stock());
        return bookRepository.save(book);
    }


}
