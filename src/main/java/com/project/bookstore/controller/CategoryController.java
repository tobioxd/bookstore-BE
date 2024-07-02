package com.project.bookstore.controller;

import java.util.List;

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

import com.project.bookstore.dtos.CategoryDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/categories")
// @Validated

public class CategoryController {

    @GetMapping("")
    public ResponseEntity<String> getCategories(
        @RequestParam("page") int page,
        @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format ("this is get categories! page: %d, limit: %d", page, limit));
    }


    @PostMapping("")
    public ResponseEntity<?> insertCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage) 
                .toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }   
        return ResponseEntity.ok("this is insert categories!" + categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategories(@PathVariable("id") Long id) {
        return ResponseEntity.ok("this is update categories!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable("id") Long id) {
        return ResponseEntity.ok("this is delete categories!");
    }

}
