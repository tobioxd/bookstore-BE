package com.project.bookstore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.bookstore.dtos.BookDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Book;
import com.project.bookstore.responses.BookResponse;

public interface IBookService {

    public Book createBook(BookDTO bookDTO) throws DataNotFoundException;

    Book getBookById(Long id) throws DataNotFoundException;

    Page<BookResponse> getAllBooks(PageRequest pageRequest);

    Book updateBook(Long id, BookDTO bookDTO) throws DataNotFoundException;

    void deleteBook(Long id) throws DataNotFoundException;

    boolean existsByName(String name);  

}
