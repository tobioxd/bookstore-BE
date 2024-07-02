package com.project.bookstore.controller;

import java.util.List;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/books")

public class BookController {

    @GetMapping("")
    public ResponseEntity<String> getBooks(
        @RequestParam("page") int page,
        @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("getBooks here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok("getBook with id: " + id);
    }

    @PostMapping("")
    public ResponseEntity<?> createBooks(
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

            return ResponseEntity.status(HttpStatus.CREATED).body("createBook success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("updateBook with id: " + id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body("deleteBook with id: " + id);
    }

}
