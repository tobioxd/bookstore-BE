package com.project.bookstore.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dtos.BookDTO;
import com.project.bookstore.models.Book;
import com.project.bookstore.responses.BookListResponse;
import com.project.bookstore.responses.BookResponse;
import com.project.bookstore.services.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/books")
@RequiredArgsConstructor

public class BookController {

    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<BookListResponse> getAllBooks(
        @RequestParam("page") int page,
        @RequestParam("limit") int limit
    ) {
        
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        Page<BookResponse> bookResponses = bookService.getAllBooks(pageRequest);
        int totalPage = bookResponses.getTotalPages();
        List<BookResponse> books = bookResponses.getContent();
        return ResponseEntity.ok().body(BookListResponse    
            .builder()
            .totalPage(totalPage)
            .books(books)
            .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(
        @PathVariable("id") Long id
    ) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createBook(
            @Valid @RequestBody BookDTO bookDTO,
            BindingResult result) {
        try {

            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
            }

            bookService.createBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create book success" + bookDTO.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
        @PathVariable("id") Long id,
        @RequestBody BookDTO bookDTO
    ) {
        try{
            bookService.updateBook(id, bookDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Update book success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
        @PathVariable("id") Long id
    ) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete book success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
