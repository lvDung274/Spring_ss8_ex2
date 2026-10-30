package com.example.book_upload.models.services;

import com.example.book_upload.models.dto.req.BookCreateDTO;
import com.example.book_upload.models.entities.Book;

public interface BookService {
    Book createBook(BookCreateDTO dto);


}
