package com.example.book_upload.controllers;

import com.example.book_upload.models.dto.req.BookCreateDTO;
import com.example.book_upload.models.entities.Book;
import com.example.book_upload.models.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO dto) {
        Book newBook = bookService.createBook(dto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}
